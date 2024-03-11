package schedule;

/**
 * Represents functionality of
 */
public interface ScheduleModel {

  /**
   * Adds the given event to this schedule.
   * @param e represents the event to add to this schedule
   * @throws IllegalArgumentException if the event does not exist or there are overlapping times
   */
  public void addEvent(Event e) throws IllegalArgumentException;

  /**
   * Allows the modification of a given event of this schedule by the host, updating the event for
   * the invitees.
   * @param e represents the event to modify to this schedule
   * @throws IllegalArgumentException if the user is not the host or the event doesn't exist
   */
  public void modifyEvent(Event e) throws IllegalArgumentException;

  /**
   *
   * @param e represents the event to remove to this schedule
   * @throws IllegalArgumentException if the event doesn't exist or isn't in this schedule
   */
  public void removeEvent(Event e) throws IllegalArgumentException;

}
