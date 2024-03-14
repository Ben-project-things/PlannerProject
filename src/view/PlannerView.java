package view;

/**
 * Interface for the view for GUI. All methods that the controller will need to call on the view
 * are listed here.
 */
public interface PlannerView {
  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void makeVisible();

  /**
   * signal the view to draw itself.
   */
  void refresh();
}
