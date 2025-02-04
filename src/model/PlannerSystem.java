package model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import xml.XMLUtil;
import schedule.Days;
import schedule.Event;
import schedule.User;
import view.PlannerTextualView;

/**
 * Implementation of the planner model.
 */
public class PlannerSystem implements PlannerModel {
  private final List<User> userList;

  private final List<Event> eventList;

  private User selectedUser;

  /**
   * Default constructor for a planner system that has no users in the database.
   */
  public PlannerSystem() {
    this.userList = new ArrayList<>();
    this.eventList = new ArrayList<>();
    this.selectedUser = null;
  }

  /**
   * Constructor to use a specific user list that has already been created.
   */
  public PlannerSystem(List<User> userList) {
    this.userList = new ArrayList<>(userList);
    Set<Event> uniqueEvents = new HashSet<>();
    for (User user : userList) {
      List<Event> userEvents = user.getSchedule().getEventsInSchedule();
      uniqueEvents.addAll(userEvents);
    }
    this.eventList = new ArrayList<>(uniqueEvents);
    this.selectedUser = userList.getFirst();
  }


  @Override
  public void uploadSchedule(File file) throws Exception {
    User xmlToUser = XMLUtil.parseUserFromXML(file);
    this.userList.add(xmlToUser);
  }

  @Override
  public void saveSchedule(User user) {
    XMLUtil.convertUserToXML(user);
  }

  @Override
  public void displayUser(User user) {
    if (this.userList.contains(user)) {
      PlannerTextualView view = new PlannerTextualView(user);
      System.out.println(view);
    } else {
      throw new IllegalArgumentException("User does not exist.");
    }
  }

  @Override
  public void addEvent(Event e) {
    if (!eventList.contains(e)) {
      eventList.add(e);
    }
    for (User u : this.userList) {
      if (e.getInvitees().contains(u)) {
        u.getSchedule().addEvent(e);
      }
    }
  }

  @Override
  public void modifyEvent(User user, Event event, Event event2) {
    user.getSchedule().modifyEvent(event, event2);
  }

  @Override
  public void removeEvent(User user, Event e) {
    if (e.getHost().equals(user)) {
      for (User invitee : e.getInvitees()) {
        if (!invitee.equals(user)) {
          invitee.getSchedule().removeEvent(e);
        }
      }
      user.getSchedule().removeEvent(e);
    } else if (e.getInvitees().contains(user)) {
      user.getSchedule().removeEvent(e);
      e.getInvitees().remove(user);
    }
  }

  @Override
  public void autoSchedule(User user) {
    //Implementation later as mentioned in section 4
  }

  @Override
  public Event eventAtTime(User user, Days day, int time) {
    if (this.userList.contains(user)) {
      for (Event e : user.getSchedule().getEventsInSchedule()) {
        if (e.checkDuringTime(day, time)) {
          return e;
        }
      }
    } else {
      throw new IllegalArgumentException("User does not exist.");
    }
    return null;
  }

  @Override
  public List<User> getAllUsers() {
    return new ArrayList<>(this.userList);
  }

  @Override
  public List<Event> getAllUserEvents(User user) {
    if (this.userList.contains(user)) {
      return new ArrayList<>(user.getSchedule().getEventsInSchedule());
    } else {
      throw new IllegalArgumentException("User is not in the system.");
    }
  }

  @Override
  public List<Event> getAllEvents() {
    return new ArrayList<>(this.eventList);
  }

  @Override
  public User getSelectedUser() {
    return this.selectedUser;
  }

  @Override
  public void setSelectedUser(User user) {
    this.selectedUser = user;
  }

}
