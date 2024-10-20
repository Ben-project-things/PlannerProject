package schedule;

import java.util.List;
import java.util.Optional;

/**
 * Interface that represents the strategy of auto scheduling an event.
 */
public interface SchedulingStrategy {

  /**
   * Schedules an event based on the strategy logic.
   *
   * @param eventName       The name of the event.
   * @param durationMinutes The duration of the event in minutes.
   * @param location        The location of the event.
   * @param isOnline        Whether the event is online.
   * @param users           All users involved in the event including the host.
   * @param host            is the host of the event (whoever is currently selected)
   * @return Optional containing the scheduled event if successful, empty if not.
   */
  Optional<Event> autoSchedule(String eventName, int durationMinutes, String location,
                               boolean isOnline, List<User> users, User host);
}
