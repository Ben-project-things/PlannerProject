package schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of an event which includes the name and location of an event, if its online,
 * what day it starts and ends, the start and end time, and the users hosting and that are involved.
 */
public class Event {
  private final String name;
  private final String location;
  private final boolean isOnline;
  private final Days startDay;
  private final int startTime;
  private final Days endDay;
  private final int endTime;
  private final User host;
  private final List<User> invitees;



  //TODO: builder method for events
  public Event() {

  }

}
