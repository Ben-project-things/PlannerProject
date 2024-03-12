package schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of a schedule allowing for the addition, and removal of events from this schedule
 * as well as modification of events.
 */
public class Schedule implements ScheduleModel {
  private final List<Event> scheduleList;

  public Schedule() {
    this.scheduleList = new ArrayList<>();
  }

  @Override
  public void addEvent(Event e) {
    if (!this.scheduleList.contains(Objects.requireNonNull(e))) {
      this.scheduleList.add(e);
    }
    else {
      throw new IllegalArgumentException("Event is already on the schedule.");
    }
  }

  @Override
  public void modifyEvent(Event e) {
    if (this.scheduleList.contains(Objects.requireNonNull(e))) {
      //TODO: implement modification of events
    }
    else {
      throw new IllegalArgumentException("Cannot modify an event the user is not in.");
    }
  }

  @Override
  public void removeEvent(Event e) {
    if (this.scheduleList.contains(Objects.requireNonNull(e))) {
      this.scheduleList.remove(e);
    }
    else {
      throw new IllegalArgumentException("Event is not present in the schedule.");
    }
  }
}
