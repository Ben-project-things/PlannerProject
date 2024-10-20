package model;

import java.io.File;
import schedule.Event;
import schedule.User;

/**
 * Represents the functions that the overall mutable Planner system is able to perform.
 */
public interface PlannerModel extends ReadOnlyPlannerModel {
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
   * Adds the given event to this schedule. Throws an IllegalArgumentException if the event isn't
   * in this schedule.
   *
   * @param e    represents the event to add to this schedule
   */
  void addEvent(Event e);

  /**
   * Allows the modification of a given event of this schedule, updating the event for
   * the invitees. Throws an IllegalArgumentException if the event doesn't exist or isn't in
   * this schedule.
   *
   * @param user represents the user who you want to modify from
   * @param e    represents the event to modify to this schedule
   * @param e2    represents the event to update the old event to for this schedule
   */
  void modifyEvent(User user, Event e, Event e2);

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
   * Updates the selected user to the new one.
   * @param user is the user to change to
   */
  void setSelectedUser(User user);

}
