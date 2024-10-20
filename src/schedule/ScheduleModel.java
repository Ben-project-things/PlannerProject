package schedule;


import java.util.List;

/**
 * Represents the functionality of a schedule.
 */
public interface ScheduleModel {

  /**
   * Adds the given event to this schedule. Throws an IllegalArgumentException if the event isn't
   * in this schedule.
   *
   * @param e represents the event to add to this schedule
   */
  void addEvent(Event e);

  /**
   * Allows the modification of a given event of this schedule, updating the event for
   * the invitees. Throws an IllegalArgumentException if the event doesn't exist or isn't in
   * this schedule.
   *
   * @param e represents the event to modify to this schedule
   * @param e2 represents the modified event
   */
  void modifyEvent(Event e, Event e2);

  /**
   * Removes the given event from this schedule. Throws an IllegalArgumentException if the event
   * isn't in this schedule.
   *
   * @param e represents the event to remove to this schedule
   */
  void removeEvent(Event e);

  /**
   * Observation of a schedules events.
   *
   * @return the events in this schedule
   */
  List<Event> getEventsInSchedule();

}
