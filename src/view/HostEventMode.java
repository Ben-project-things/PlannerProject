package view;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Map;

import schedule.Event;
import schedule.User;

/**
 * Represents the implementation of displaying events with the host events colored differently.
 */
public class HostEventMode extends DefaultEventMode {
  private User host;

  /**
   * Constructor to initialize a field to check for who the host of the event is.
   *
   * @param host is the host to be checking for
   */
  public HostEventMode(User host) {
    this.host = host;
  }

  @Override
  public void drawEvent(Graphics2D g2d, Event event, int cellWidth, int cellHeight,
                        Map<Rectangle, Event> eventRectangles) {
    int startDayIndex = event.getStartDay().ordinal();
    int endDayIndex = event.getEndDay().ordinal();
    int startHour = event.getStartTime() / 100;

    for (int day = startDayIndex; day <= endDayIndex; day++) {
      int dayStartHour = (day == startDayIndex) ? startHour : 0;
      int dayEndHour = (day == endDayIndex) ? (event.getEndTime() / 100) : 24;
      Color eventColor = (event.getHost().equals(host)) ? Color.CYAN : Color.RED;
      drawDayEvent(g2d, cellWidth, cellHeight, event, dayStartHour, dayEndHour, day, eventColor,
              eventRectangles);
    }
  }
}
