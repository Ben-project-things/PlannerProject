package controller;

import java.util.List;

import model.PlannerModel;
import schedule.Event;
import schedule.User;

/**
 * Interface which represents the actions that the controller will be able to make.
 */
public interface Features {
  /**
   * Loads a specific XML file that the user has selected to load.
   */
  void loadSchedule();

  /**
   * Saves all the schedules in this planner system to the given directory.
   */
  void saveSchedule();

  /**
   * The action of opening the EventFrame for the schedule planner system.
   */
  void createEventMainFrame();

  /**
   * The action of opening the ScheduleFrame for the schedule planner system.
   */
  void createScheduleMainFrame();

  /**
   * The action of toggling to enable or disable the host display mode.
   * @param toggle is whether the mode should be toggled on or off.
   */
  void toggleHostMode(boolean toggle);

  /**
   * The action of creating a new event for the schedule planner system.
   *
   * @param event is the event to create and add to the planner system
   */
  void createEvent(Event event);

  /**
   * The action of modifying a new event for the schedule planner system.
   *
   * @param event1 is the original event to modify to the planner system
   * @param event2 is the new updated event to update the old event with
   */
  void modifyEvent(Event event1, Event event2);

  /**
   * The action of removing an event for the schedule planner system.
   */
  void removeEvent(Event event);

  /**
   * The action of switching the current user to be displayed on the main frame.
   *
   * @param user is the user to now display
   */
  void displayUser(User user);

  /**
   * Automatically schedules the event with the given info.
   *
   * @param eventName is the event name of the new event
   * @param duration  is the duration of the new event
   * @param location  is the location of the new event
   * @param isOnline  is whether this new event is online
   * @param attendees are the attendees to this new event
   * @param host      is the host of the event (whatever user is selected aka displayed currently)
   */
  void scheduleAutomaticEvent(String eventName, int duration, String location,
                              boolean isOnline, List<User> attendees, User host);

  /**
   * Method that initiates the startup of the program through the controller.
   *
   * @param model is the model to be used.
   */
  void launch(PlannerModel model);
}
