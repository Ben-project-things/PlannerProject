package schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of an event which includes the name and location of an event, if its online,
 * what day it starts and ends, the start and end time, and the users hosting and that are involved.
 */
public class Event {
  private String name;
  private String location;
  private boolean isOnline;
  private Days startDay;
  private int startTime;
  private Days endDay;
  private int endTime;
  private User host;
  private List<User> invitees;



  //TODO: builder method for events
  public Event() {

  }

}
