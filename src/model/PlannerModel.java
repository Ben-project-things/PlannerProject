package model;

import schedule.Days;
import schedule.Event;
import schedule.User;

/**
 * Represents the functions that the overall Planner system is able to perform.
 */
public interface PlannerModel {

  //TODO: find out what parameters and return values are

  /**
   * Uploads a given XML file to the planner database.
   */
  void uploadSchedule();

  /**
   * Saves the current schedule of the user as an XML file.
   */
  void saveSchedule();

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
   * @param e represents the event to add to this schedule
   */
  void addEvent(Event e);

  /**
   * Allows the modification of a given event of this schedule, updating the event for
   * the invitees. Throws an IllegalArgumentException if the event doesn't exist or isn't in
   * this schedule.
   *
   * @param e represents the event to modify to this schedule
   */
  void modifyEvent(Event e);

  /**
   * Removes the given event from this schedule. Throws an IllegalArgumentException if the event
   * isn't in this schedule.
   *
   * @param e represents the event to remove to this schedule
   */
  void removeEvent(Event e);

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
   * @return the event if there is one during that day or time, null if there is no event during
   * that time
   */
  Event eventAtTime(User user, Days day, int time);

}
