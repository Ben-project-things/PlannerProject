package schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a schedule allowing for the addition, and removal of events from this schedule
 * as well as modification of events.
 */
public class Schedule implements ScheduleModel {
  private List<Event> scheduleList;

  public Schedule() {
    this.scheduleList = new ArrayList<>();
  }
  @Override
  public void addEvent(Event e) throws IllegalArgumentException {

  }

  @Override
  public void modifyEvent(Event e) throws IllegalArgumentException {

  }

  @Override
  public void removeEvent(Event e) throws IllegalArgumentException {

  }
}
