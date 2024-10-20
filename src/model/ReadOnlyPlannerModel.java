package model;

import java.util.List;

import schedule.Days;
import schedule.Event;
import schedule.User;

/**
 * Represents the functions that the overall Planner system is able to perform.
 */
public interface ReadOnlyPlannerModel {
  /**
   * Displays the current user to the client.
   *
   * @param user is the user to display
   */
  void displayUser(User user);

  /**
   * Method to check if there is event occurring during the given day and time.
   *
   * @param user is the user's schedule to check
   * @param day  is the day to check
   * @param time is the time to check
   * @return the event if there is one during that day or time, null if not
   */
  Event eventAtTime(User user, Days day, int time);

  /**
   * Observation to see all the users contained in this planner system.
   *
   * @return the users in this planner system
   */
  List<User> getAllUsers();

  /**
   * Observation to see all the events in a specific user's schedule.
   *
   * @return the user to check events for
   */
  List<Event> getAllUserEvents(User user);

  /**
   * Observation to see all the events in this system.
   *
   * @return the users in this planner system
   */
  List<Event> getAllEvents();

  /**
   * Observation to see which user's is currently selected (displayed).
   *
   * @return the selected user
   */
  User getSelectedUser();
}
