package schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of a schedule allowing for the addition, and removal of events from this schedule
 * as well as modification of events.
 */
public class Schedule implements ScheduleModel {
  private final List<Event> eventList;

  /**
   * Basic constructor to construct an empty schedule.
   */
  public Schedule() {
    this.eventList = new ArrayList<>();
  }

  /**
   * Constructor to construct a schedule with given events.
   *
   * @param eventList is the list of events to add
   */
  public Schedule(List<Event> eventList) {
    this.eventList = eventList;
  }

  @Override
  public void addEvent(Event e) {
    if (!this.eventList.contains(Objects.requireNonNull(e)) && this.noTimeDiscrepancy(e)) {
      this.eventList.add(e);
    } else {
      throw new IllegalArgumentException("Event is already on the schedule or there is a time" +
              "discrepancy.");
    }
  }

  /**
   * Helper method to check whether any events currently in this schedule collide with the given
   * event.
   *
   * @param e is the event to check
   * @return true if there is no time discrepancy and the event can be added
   */
  private boolean noTimeDiscrepancy(Event e) {
    for (Event eventInSchedule : eventList) {
      if (eventInSchedule.checkOverlap(e)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void modifyEvent(Event event, Event event2) {
    event = event2;
  }

  @Override
  public void removeEvent(Event e) {
    if (this.eventList.contains(Objects.requireNonNull(e))) {
      this.eventList.remove(e);
    } else {
      throw new IllegalArgumentException("Event is not present in the schedule.");
    }
  }

  @Override
  public List<Event> getEventsInSchedule() {
    return this.eventList;
  }

}
