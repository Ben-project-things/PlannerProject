package schedule;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Method of autoScheduling which finds the first possible time between Monday and Friday between
 * the working hours of 0900 and 1700 that each user is able to attend.
 */
public class WorkHoursSchedulingStrategy implements SchedulingStrategy {

  @Override
  public Optional<Event> autoSchedule(String eventName, int durationMinutes,
                                      String location, boolean isOnline, List<User> users,
                                      User host) {
    List<Days> workDays = Arrays.asList(Days.MONDAY, Days.TUESDAY,
            Days.WEDNESDAY, Days.THURSDAY, Days.FRIDAY);
    int startOfWorkDay = 900;
    int endOfWorkDay = 1700;

    for (Days day : workDays) {
      for (int hour = startOfWorkDay; hour <= endOfWorkDay - durationMinutes; hour += 100) {
        if (canScheduleEvent(day, hour, durationMinutes, users)) {
          int endTime = hour + durationMinutes / 60 * 100 + durationMinutes % 60;
          Days endDay = day;
          if (endTime >= 2400) {
            endTime -= 2400;
            endDay = day.next();
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
   * @param startDay is the startDay in which to check
   * @param startTime is the startTime in which to check
   * @param durationMinutes is the duration of the proposed event
   * @param users are the users to check for
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
