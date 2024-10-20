package view;

/**
 * Interface for the event view panel dealing with functionality.
 */
public interface EventPanelView {

  /**
   * Displays an error message with the given error.
   *
   * @param message is the message to respond with the error
   */
  void displayErrorMessage(String message);

  /**
   * Method that clears the form of the JPanel (a reset).
   */
  void clearForm();
}
