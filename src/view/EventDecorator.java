package view;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Map;

import schedule.Event;

/**
 * Class that represents how events can be drawn.
 */
public interface EventDecorator {
  /**
   * Method which will draw and decorate the event in a certain manner.
   *
   * @param g2d             is the graphics component
   * @param event           is the event to render
   * @param cellWidth       is the cell width the render will need to fill
   * @param cellHeight      is the cell height the render will need to fill
   * @param eventRectangles is the map which represents where the events are located for on the
   *                        schedule panel
   */
  void drawEvent(Graphics2D g2d, Event event, int cellWidth, int cellHeight,
                 Map<Rectangle, Event> eventRectangles);
}
