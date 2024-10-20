package schedule;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Method of autoScheduling which finds the first possible time (starting Sunday) that all users
 * can attend.
 */
public class AnyTimeSchedulingStrategy implements SchedulingStrategy {

  @Override
  public Optional<Event> autoSchedule(String eventName, int durationMinutes,
                                      String location, boolean isOnline, List<User> users,
                                      User host) {
    List<Days> allDays = Arrays.asList(Days.values());
    int startOfDay = 0;
    int endOfDay = 2359;

    for (Days day : allDays) {
      for (int hour = startOfDay; hour <= endOfDay; hour += 100) {
        if (hour % 100 > 59) {
          continue;
        }
        if (canScheduleEvent(day, hour, durationMinutes, users)) {
          int endTime = hour + durationMinutes / 60 * 100 + durationMinutes % 60;
          Days endDay = day;
          while (endTime >= 2400) {
            endTime -= 2400;
            endDay = endDay.next();
          }

          Event event = new Event.Builder()
                  .name(eventName)
                  .location(location)
                  .isOnline(isOnline)
                  .startDay(day)
                  .startTime(hour)
                  .endDay(endDay)
                  .endTime(endTime)
                  .host(host)
                  .invitees(users)
                  .build();
          return Optional.of(event);
        }
      }
    }
    return Optional.empty();
  }

  /**
   * Private helper which determines if you are able to schedule an event during the given time.
   *
   * @param startDay        is the startDay in which to check
   * @param startTime       is the startTime in which to check
   * @param durationMinutes is the duration of the proposed event
   * @param users           are the users to check for
   * @return true if you are able to make this event for every user
   */
  private boolean canScheduleEvent(Days startDay, int startTime,
                                   int durationMinutes, List<User> users) {
    int endTime = startTime + durationMinutes / 60 * 100 + durationMinutes % 60;
    Days endDay = startDay;

    if (endTime >= 2400) {
      endTime -= 2400;
      endDay = startDay.next();
    }

    Event tempEvent = new Event.Builder()
            .startDay(startDay)
            .startTime(startTime)
            .endDay(endDay)
            .endTime(endTime)
            .build();

    for (User user : users) {
      for (Event existingEvent : user.getSchedule().getEventsInSchedule()) {
        if (existingEvent.checkOverlap(tempEvent)) {
          return false;
        }
      }
    }
    return true;
  }
}
