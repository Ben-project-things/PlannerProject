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
    if (!this.scheduleList.contains(Objects.requireNonNull(e)) && this.noTimeDiscrepancy(e)) {
      this.scheduleList.add(e);
    }
    else {
      throw new IllegalArgumentException("Event is already on the schedule.");
    }
  }

  /**
   * Helper method to check whether any events currently in this schedule collide with the given
   * event.
   * @param e is the event to check
   * @return true if there is no time discrepancy and the event can be added
   */
  private boolean noTimeDiscrepancy(Event e) {
    //TODO: checks for if this is a valid event to add (overlapping times)
    return false;
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

  /**
   * Observation of a schedules events.
   * @return the events in this schedule
   */
  public List<Event> getEventsInSchedule() {
    return this.scheduleList;
  }
}
