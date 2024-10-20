package view;

import java.util.Map;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

import schedule.Event;

/**
 * Represents the implementation of displaying events in the normal manner without the host color.
 */
public class DefaultEventMode implements EventDecorator {

  @Override
  public void drawEvent(Graphics2D g2d, Event event, int cellWidth, int cellHeight,
                        Map<Rectangle, Event> eventRectangles) {
    int startDayIndex = event.getStartDay().ordinal();
    int endDayIndex = event.getEndDay().ordinal();
    int startHour = event.getStartTime() / 100;

    for (int day = startDayIndex; day <= endDayIndex; day++) {
      int dayStartHour = (day == startDayIndex) ? startHour : 0;
      int dayEndHour = (day == endDayIndex) ? (event.getEndTime() / 100) : 24;
      drawDayEvent(g2d, cellWidth, cellHeight, event, dayStartHour, dayEndHour, day, Color.RED,
              eventRectangles);
    }
  }

  /**
   * Private helper to draw the individual day event.
   *
   * @param g2d             the graphics component
   * @param cellWidth       the width of the cells
   * @param cellHeight      the height of the cells
   * @param event           is the event to draw
   * @param startHour       is the starting hour
   * @param endHour         is the ending hour
   * @param dayIndex        is the actual index for the day itself
   * @param color           is the color to draw the event depending on the decorator
   * @param eventRectangles is the map dealing with where the events will be located on
   */
  protected void drawDayEvent(Graphics2D g2d, int cellWidth, int cellHeight,
                              Event event, int startHour, int endHour, int dayIndex, Color color,
                              Map<Rectangle, Event> eventRectangles) {
    Rectangle rect = new Rectangle(dayIndex * cellWidth,
            startHour * cellHeight, cellWidth, (endHour - startHour) * cellHeight);
    g2d.setColor(color);
    g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
    g2d.setColor(Color.BLACK);

    eventRectangles.put(rect, event);
  }
}
