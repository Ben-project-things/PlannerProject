package view;

import java.io.File;

import controller.Features;
import schedule.User;

/**
 * Interface for the main schedule planner view.
 */
public interface PlannerView {

  /**
   * Gets the XML file to be loaded.
   * @return the XML file selected
   */
  File loadXMLFile();

  /**
   * Saves the selectedUser as an XML file.
   */
  void saveXMLFile();


  /**
   * Refreshes the UI to redraw itself.
   */
  void refreshUI();

  /**
   * Makes the view visible or not.
   *
   * @param visible is whether the view should be visible or not
   */
  void makeVisible(boolean visible);

  /**
   * Displays all the users from the model.
   */
  void displayUsers();

  /**
   * Displays the given user.
   */
  void displayUser(User user);

  /**
   * Displays a given error message.
   * @param message is the message to be displayed.
   */
  void showError(String message);

  /**
   * Sets up the view to listen for certain features.
   * @param features is the features to be listening for
   */
  void setFeaturesListener(Features features);

  /**
   * Creates a new instance of the event frame to display.
   */
  void createEventFrame();

  /**
   * Creates a new instance of the schedule event frame to display.
   */
  void scheduleEventFrame();

  /**
   * Method which allows the changing of display for the Schedule Panel to the host color mode.
   * @param enabled is whether the host color mode should be enabled or not
   */
  void toggleHostColorMode(boolean enabled);

}
