package model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  /**
   * Default constructor for a planner system that has no users in the database.
   */
  public PlannerSystem() {
    this.userList = new ArrayList<>();
    this.eventList = new ArrayList<>();
  }

  /**
   * Constructor to use a specific user list that has already been created.
   */
  public PlannerSystem(List<User> userList) {
    this.userList = userList;
    Set<Event> uniqueEvents = new HashSet<>();
    for (User user : userList) {
      List<Event> userEvents = user.getSchedule().getEventsInSchedule();
      uniqueEvents.addAll(userEvents);
    }
    this.eventList = new ArrayList<>(uniqueEvents);
  }


  @Override
  public void uploadSchedule(File file) throws Exception {
    User XMLtoUSER = XMLUtil.parseUserFromXML(file);
    this.userList.add(XMLtoUSER);
  }

  @Override
  public void saveSchedule(User user) {
    XMLUtil.convertUserToXML(user);
  }

  @Override
  public void displayUser(User user) {
    if (this.userList.contains(user)) {
      //Since no controller yet, use of view here
      PlannerTextualView view = new PlannerTextualView(user);
      System.out.println(view);
    } else {
      throw new IllegalArgumentException("User does not exist.");
    }
  }

  @Override
  public void addEvent(User user, Event e) {
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
  public void modifyEvent(User user, Event e) {
    user.getSchedule().modifyEvent(e);
  }

  @Override
  public void removeEvent(User user, Event e) {
    if (e.getInvitees().get(0).equals(user)) {
      eventList.remove(e);
    }
    for (User u : this.userList) {
      if (e.getInvitees().contains(u)) {
        u.getSchedule().removeEvent(e);
      }
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
}
