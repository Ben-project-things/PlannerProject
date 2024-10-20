package view;

/**
 * Interface for the event view dealing with setting up the event frame.
 */
public interface EventView {
  /**
   * Makes the view visible or not.
   *
   * @param visible is whether the view should be visible or not
   */
  void makeVisible(boolean visible);

  /**
   * Signal the view to draw itself.
   */
  void refresh();
}
