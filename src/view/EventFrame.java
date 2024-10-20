package view;

import java.util.Objects;

import javax.swing.JFrame;

import controller.Features;
import model.ReadOnlyPlannerModel;
import schedule.Event;

/**
 * Class that deals with the frame of the EventFrame.
 */
public class EventFrame extends JFrame implements EventView {
  private final ReadOnlyPlannerModel model;
  private final Features features;
  private EventPanel eventPanel;

  /**
   * Public constructor to initialize the event frame which sets up the properties of the actual
   * frame itself, including title, size, operations, and the actual panel itself (delegated to
   * other class).
   * @param model is the read only model so that the event panel can have access to read the data
   * @param features is the controller to pass down to other view elements
   */
  public EventFrame(ReadOnlyPlannerModel model, Features features) {
    this.model = Objects.requireNonNull(model);
    this.features = Objects.requireNonNull(features);
    this.initializeFrame();
  }

  /**
   * Public constructor to initialize the event frame which sets up the properties of the actual
   * frame itself, including title, size, operations, and the actual panel itself (delegated to
   * other class).
   * @param model is the read only model so that the event panel can have access to read the data
   * @param features is the controller to pass down to other view elements
   * @param selectedEvent is the event to prepopulate the frame with
   */
  public EventFrame(ReadOnlyPlannerModel model, Features features, Event selectedEvent) {
    this.model = Objects.requireNonNull(model);
    this.features = Objects.requireNonNull(features);
    setTitle("Event Frame");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    eventPanel = new EventPanel(this.model, features, selectedEvent);
    getContentPane().add(this.eventPanel);
  }

  /**
   * Private helper method to set up the frame.
   */
  private void initializeFrame() {
    setTitle("Event Frame");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    eventPanel = new EventPanel(this.model, this.features);
    getContentPane().add(this.eventPanel);
  }

  @Override
  public void makeVisible(boolean visible) {
    setVisible(visible);
    if (!visible) {
      this.dispose();
    }
  }

  @Override
  public void refresh() {
    this.eventPanel.repaint();
  }
}
