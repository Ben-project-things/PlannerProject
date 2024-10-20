package view;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.JPanel;

import controller.Features;
import model.ReadOnlyPlannerModel;
import schedule.Event;
import schedule.ScheduleModel;

/**
 * Class that deals with the view of a specific Schedule.
 */
public class SchedulePanel extends JPanel {
  private Features features;
  private final ReadOnlyPlannerModel model;
  private final Map<Rectangle, Event> eventRectangles = new HashMap<>();
  private ScheduleModel selectedSchedule;
  private EventDecorator eventDecorator;

  /**
   * Constructor that constructs the view of the schedule panel with a given model, getting the
   * selected user as well as dealing with mouse clicks.
   *
   * @param model is the read only model of the whole planner system
   */
  public SchedulePanel(ReadOnlyPlannerModel model) {
    this.model = Objects.requireNonNull(model);
    this.selectedSchedule = this.model.getSelectedUser().getSchedule();
    this.eventDecorator = new DefaultEventMode();
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        handleMouseClick(e);
      }
    });
  }

  /**
   * Method which changes the user's schedule to display.
   *
   * @param newSchedule is the user to display.
   */
  public void setUser(ScheduleModel newSchedule) {
    this.selectedSchedule = newSchedule;
    repaint();
  }

  /**
   * Method which will set the eventRender to a certain event decorator pattern, repainting it.
   *
   * @param decorator is the to change to.
   */
  public void setEventDecorator(EventDecorator decorator) {
    this.eventDecorator = decorator;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    int width = getWidth();
    int height = getHeight();
    int cellWidth = width / 7;
    int cellHeight = height / 24;

    drawGrid(g2d, width, height, cellWidth, cellHeight);
    eventRectangles.clear();
    for (Event event : selectedSchedule.getEventsInSchedule()) {
      eventDecorator.drawEvent(g2d, event, cellWidth, cellHeight, eventRectangles);
    }
  }

  /**
   * Helper which draws the basic grid of 7 columns and 24 rows.
   *
   * @param g2d        is the graphics component
   * @param width      is the width of the panel
   * @param height     is the height of the panel
   * @param cellWidth  is the cell width for each
   * @param cellHeight is the cell height for each
   */
  private void drawGrid(Graphics2D g2d, int width, int height, int cellWidth, int cellHeight) {
    g2d.setColor(Color.BLACK);
    for (int i = 0; i <= 7; i++) {
      g2d.drawLine(i * cellWidth, 0, i * cellWidth, height);
    }
    for (int j = 0; j <= 24; j++) {
      g2d.drawLine(0, j * cellHeight, width, j * cellHeight);
    }
  }

  /**
   * Setter method to set the features for this schedule panel.
   *
   * @param features is the controller to react to this panel
   */
  public void setFeatures(Features features) {
    this.features = Objects.requireNonNull(features);
  }

  /**
   * Private helper that deals with the action of a user clicking on the mouse.
   *
   * @param e is the mouseEvent that occurs like a click event
   */
  private void handleMouseClick(MouseEvent e) {
    for (Map.Entry<Rectangle, Event> entry : eventRectangles.entrySet()) {
      if (entry.getKey().contains(e.getPoint())) {
        Event clickedEvent = entry.getValue();
        EventFrame eventFrame = new EventFrame(this.model, features, clickedEvent);
        eventFrame.setVisible(true);
        break;
      }
    }
  }
}
