package model;

import java.util.ArrayList;
import java.util.List;

import schedule.Days;
import schedule.Event;
import schedule.User;
import view.PlannerTextualView;

/**
 * Implementation of the planner model.
 */
public class PlannerSystem implements PlannerModel {
  private List<User> userList;

  /**
   * Default constructor for a planner system that has no users in the database.
   */
  public PlannerSystem() {
    this.userList = new ArrayList<>();
  }

  /**
   * Constructor to use a specific user list that has already been created.
   */
  public PlannerSystem(List<User> userList) {
    this.userList = userList;
  }


  @Override
  public void uploadSchedule() {
    //TODO: functionality with converting XML files
  }

  @Override
  public void saveSchedule() {
    //TODO: functionality with saving as an XML file
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
    for (User u : this.userList) {
      if (e.getInvitees().contains(u)) {
        u.getSchedule().addEvent(e);
      }
    }
  }

  @Override
  public void modifyEvent(Event e) {

  }

  @Override
  public void removeEvent(Event e) {
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
