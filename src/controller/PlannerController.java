package controller;

import schedule.User;

/**
 * Represents a controller for the client in the overall PlannerSystem.
 */
public interface PlannerController {
  /**
   * Action to select and display a user to the view.
   * @param user is the user to display
   */
  void displayUser(User user);

}
