package model;

import java.io.File;
import java.util.List;

import schedule.Days;
import schedule.Event;
import schedule.User;

/**
 * Represents the functions that the overall Planner system is able to perform.
 */
public interface PlannerModel {
  /**
   * Method to convert from an XML file to the User object used for the planner system, adding that
   * user to the database.
   *
   * @param file is the XML file to be converted
   * @throws Exception if the file does not exist
   */
  void uploadSchedule(File file) throws Exception;

  /**
   * Saves the current schedule of the user as an XML file.
   */
  void saveSchedule(User user);

  /**
   * Displays the current user to the client.
   *
   * @param user is the user to display
   */
  void displayUser(User user);

  /**
   * Adds the given event to this schedule. Throws an IllegalArgumentException if the event isn't
   * in this schedule.
   *
   * @param user represents the user who you add the event to
   * @param e    represents the event to add to this schedule
   */
  void addEvent(User user, Event e);

  /**
   * Allows the modification of a given event of this schedule, updating the event for
   * the invitees. Throws an IllegalArgumentException if the event doesn't exist or isn't in
   * this schedule.
   *
   * @param user represents the user who you want to modify from
   * @param e    represents the event to modify to this schedule
   */
  void modifyEvent(User user, Event e);

  /**
   * Removes the given event from this schedule. Throws an IllegalArgumentException if the event
   * isn't in this schedule.
   *
   * @param user represents the user who you want to remove the event from
   * @param e    represents the event to remove to this schedule
   */
  void removeEvent(User user, Event e);

  /**
   * Method to automatically schedule an event for a given user.
   *
   * @param user is the user to schedule the event for
   */
  void autoSchedule(User user);

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

}
