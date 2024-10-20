package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import schedule.Days;
import schedule.Event;
import schedule.User;
import view.PlannerTextualView;

/**
 * Implementation of a read only planner model.
 */
public class ReadOnlyPlannerSystem implements ReadOnlyPlannerModel {
  private final List<User> userList;

  private final List<Event> eventList;

  private final User selectedUser;

  /**
   * Default constructor for a read only planner system that has no users in the database.
   */
  public ReadOnlyPlannerSystem() {
    this.userList = new ArrayList<>();
    this.eventList = new ArrayList<>();
    this.selectedUser = null;
  }

  /**
   * Constructor to use a specific user list that has already been created.
   */
  public ReadOnlyPlannerSystem(List<User> userList) {
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
  public void displayUser(User user) {
    if (this.userList.contains(user)) {
      //Since no controller yet, use of view here (would be GUI implementation to display)
      PlannerTextualView view = new PlannerTextualView(user);
      System.out.println(view);
    } else {
      throw new IllegalArgumentException("User does not exist.");
    }
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

}
