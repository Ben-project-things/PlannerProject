package schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A representation of an event which includes the name and location of an event, if its online,
 * what day it starts and ends, the start and end time, and the users hosting and that are involved.
 * INVARIANT:
 * First two digits of start and end time are >= 0 and < 24.
 * Last two digits of start and end time are >= 0 and < 60
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

  /**
   * Default constructor to an event.
   */
  public Event() {
    this.name = "";
    this.location = "";
    this.isOnline = false;
    this.startDay = Days.SUNDAY;
    this.startTime = 0;
    this.endDay = Days.SUNDAY;
    this.endTime = 0;
    this.host = null;
    this.invitees = new ArrayList<>();
  }

  /**
   * Setter method to set the name of this event.
   *
   * @param name is the name to call the event
   */
  public void setName(String name) {
    this.name = Objects.requireNonNull(name);
  }

  /**
   * Setter method to set the location of this event.
   *
   * @param location is the location of the event
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * Setter method to set whether this event is online or not.
   *
   * @param isOnline is whether this event is online
   */
  public void setIsOnline(boolean isOnline) {
    this.isOnline = isOnline;
  }

  /**
   * Setter method to set when this event starts.
   *
   * @param startDay is the day the event will start on.
   */
  public void setStartDay(Days startDay) {
    this.startDay = startDay;
  }

  /**
   * Setter method to set start time of the event. Throws an exception if time given is invalid.
   *
   * @param startTime is the start time this event will be
   */
  public void setStartTime(int startTime) {
    if (isValidTime(startTime)) {
      this.startTime = startTime;
    } else {
      throw new IllegalArgumentException("Time must be between 00:00 and 23:59");
    }
  }

  /**
   * Setter method to set when this event ends.
   *
   * @param endDay is the day the event will end on.
   */
  public void setEndDay(Days endDay) {
    this.endDay = endDay;
  }

  /**
   * Setter method to set end time of the event. Throws an exception if time given is invalid.
   *
   * @param endTime is the end time this event will be
   */
  public void setEndTime(int endTime) {
    if (isValidTime(endTime)) {
      this.endTime = endTime;
    } else {
      throw new IllegalArgumentException("Time must be between 00:00 and 23:59");
    }
  }

  /**
   * Setter method to set the host of this event.
   *
   * @param host is the host user of this event
   */
  public void setHost(User host) {
    this.host = Objects.requireNonNull(host);
  }

  /**
   * Setter method to set the invitees to this event, adds the host to the list of invitees.
   *
   * @param invitees is the list of users invited to this event
   */
  public void setInvitees(List<User> invitees) {
    if (this.host == null) {
      throw new IllegalStateException("Host must be set before setting invitees.");
    }
    List<User> hostIncludedInvitees = new ArrayList<>(invitees);
    if (!hostIncludedInvitees.contains(this.host)) {
      hostIncludedInvitees.add(0, this.host);
    }
    this.invitees = hostIncludedInvitees;
  }

  /**
   * Observation of the name of the event.
   *
   * @return the name of the event
   */
  public String getName() {
    return this.name;
  }

  /**
   * Observation of the start day of the event.
   *
   * @return the start day of the event
   */
  public Days getStartDay() {
    return this.startDay;
  }

  /**
   * Observation of the start time of the event.
   *
   * @return the start time of the event
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Observation of the end day of the event.
   *
   * @return the end day of the event
   */
  public Days getEndDay() {
    return this.endDay;
  }

  /**
   * Observation of the end time of the event.
   *
   * @return the end time of the event
   */
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * Observation of the location of the event.
   *
   * @return the location of the event
   */
  public String getLocation() {
    return this.location;
  }

  /**
   * Observation if the event is online.
   *
   * @return if the event is online
   */
  public boolean getIsOnline() {
    return this.isOnline;
  }

  /**
   * Observation of the host of the event.
   *
   * @return the host of the event
   */
  public User getHost() {
    return this.host;
  }

  /**
   * Observation of the invitees of the event.
   *
   * @return the invitees of the event
   */
  public List<User> getInvitees() {
    return this.invitees;
  }

  /**
   * Method which checks if two events are overlapping, meaning does one event start or occur
   * during the duration of the other.
   *
   * @param e is the event to check with
   * @return true if there are any overlaps
   */
  public boolean checkOverlap(Event e) {
    boolean thisSpansWeekBoundary = this.startDay.ordinal() > this.endDay.ordinal();
    boolean otherEventSpansWeekBoundary = e.startDay.ordinal() > e.endDay.ordinal();
    boolean potentialDayOverlap;

    if (thisSpansWeekBoundary || otherEventSpansWeekBoundary) {
      //Case to check on wrap around
      potentialDayOverlap = !(this.endDay.ordinal() < e.startDay.ordinal() &&
              this.startDay.ordinal() > e.endDay.ordinal());
    } else {
      //Case to check standard format
      potentialDayOverlap = this.startDay.ordinal() <= e.endDay.ordinal() &&
              this.endDay.ordinal() >= e.startDay.ordinal();
    }

    //Check for specific time overlap
    if (potentialDayOverlap) {
      boolean startsOnSameDay = this.startDay.equals(e.startDay);
      boolean endsOnSameDay = this.endDay.equals(e.endDay);

      if (startsOnSameDay && ((this.startTime > e.startTime && this.startTime < e.endTime) ||
              (e.startTime > this.startTime && e.startTime < this.endTime))) {
        return true;
      } else if (endsOnSameDay && ((this.endTime > e.startTime && this.endTime < e.endTime) ||
              (e.endTime > this.startTime && e.endTime < this.endTime))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Public helper method to check whether this event is during the given day and time.
   *
   * @param d    is the day to check
   * @param time is the time to check
   * @return true if this event elapses over the given day and time
   */
  public boolean checkDuringTime(Days d, int time) {
    boolean isDuringDay = (d.ordinal() >= this.startDay.ordinal() &&
            d.ordinal() <= this.endDay.ordinal());
    //If wrap around event, recalculate isDuringDay
    if (this.endDay.ordinal() < this.startDay.ordinal()) {
      isDuringDay = d.ordinal() <= this.endDay.ordinal() || d.ordinal() >= this.startDay.ordinal();
    }
    //If it doesn't fall between, can't be during given time and day
    if (!isDuringDay) {
      return false;
    }
    //Check time directly for single day events
    if (this.startDay == this.endDay) {
      return (time >= this.startTime && time <= this.endTime);
    }
    //Check time for startTime
    else if (d == this.startDay) {
      //Time must be greater than startTime to be within boundary
      return time >= this.startTime;
    } else if (d == this.endDay) {
      //Time must be less than endTime to be within boundary
      return time <= this.endTime;
    }
    return true;
  }

  /**
   * Public method used to check if the day requested is the start day.
   *
   * @param day is the start day to check
   * @return if the start day metches this day
   */
  public boolean occursOnStartDay(Days day) {
    return this.startDay.equals(day);
  }

  /**
   * Private helper used to check validity of start and end times.
   *
   * @param time is the time inputted
   * @return true if the time is valid
   */
  private boolean isValidTime(int time) {
    int hours = time / 100;
    int minutes = time % 100;

    return hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59;
  }

  /**
   * Builder to construct events.
   */
  public static class Builder {
    private final Event event;

    /**
     * Constructor to initialize the event.
     */
    public Builder() {
      this.event = new Event();
    }

    /**
     * Constructor to build from an existing event.
     */
    public Builder(Event e) {
      this.event = e;
    }

    /**
     * Builder method to set the name of this event.
     *
     * @param name is the name of the event
     * @return the builder to continue constructing the event object
     */
    public Builder name(String name) {
      this.event.setName(name);
      return this;
    }

    /**
     * Builder method to set the location of this event.
     *
     * @param location is the location of the event
     * @return the builder to continue constructing the event object
     */
    public Builder location(String location) {
      this.event.setLocation(location);
      return this;
    }

    /**
     * Builder method to set whether the event is online.
     *
     * @param isOnline is if the event is online
     * @return the builder to continue constructing the event object
     */
    public Builder isOnline(boolean isOnline) {
      this.event.setIsOnline(isOnline);
      return this;
    }

    /**
     * Builder method to set the start day of this event.
     *
     * @param startDay is the start day of the event
     * @return the builder to continue constructing the event object
     */
    public Builder startDay(Days startDay) {
      this.event.setStartDay(startDay);
      return this;
    }

    /**
     * Builder method to set the start time of this event.
     *
     * @param startTime is the start time of the event
     * @return the builder to continue constructing the event object
     */
    public Builder startTime(int startTime) {
      this.event.setStartTime(startTime);
      return this;
    }

    /**
     * Builder method to set the end day of this event.
     *
     * @param endDay is the end day of the event
     * @return the builder to continue constructing the event object
     */
    public Builder endDay(Days endDay) {
      this.event.setEndDay(endDay);
      return this;
    }

    /**
     * Builder method to set the end time of this event.
     *
     * @param endTime is the end time of the event
     * @return the builder to continue constructing the event object
     */
    public Builder endTime(int endTime) {
      this.event.setEndTime(endTime);
      return this;
    }

    /**
     * Builder method to set the host of this event.
     *
     * @param host is the host of the event
     * @return the builder to continue constructing the event object
     */
    public Builder host(User host) {
      this.event.setHost(host);
      return this;
    }

    /**
     * Builder method to set the invitees of this event.
     *
     * @param invitees is the invitees of the event
     * @return the builder to continue constructing the event object
     */
    public Builder invitees(List<User> invitees) {
      this.event.setInvitees(invitees);
      return this;
    }

    /**
     * Build method to construct and return the new event.
     *
     * @return the constructed event
     */
    public Event build() {
      return event;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Event)) {
      return false;
    }
    Event event = (Event) o;
    return isOnline == event.isOnline &&
            startTime == event.startTime &&
            endTime == event.endTime &&
            Objects.equals(name, event.name) &&
            Objects.equals(location, event.location) &&
            startDay == event.startDay &&
            endDay == event.endDay &&
            Objects.equals(host, event.host);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, location, isOnline, startDay, startTime, endDay, endTime, host);
  }

  @Override
  public String toString() {
    return this.name;
  }

  /**
   * Public method which helps print out the details about a center event in an ordered way.
   *
   * @return the string containing the event details
   */
  public String printDetails() {
    StringBuilder details = new StringBuilder();
    details.append("Name: ").append(this.name).append("\n");
    details.append("Location: ").append(this.location).append("\n");
    details.append("Is Online: ").append(this.isOnline ? "true" : "false").append("\n");
    details.append("Start Day: ").append(this.startDay).append("\n");
    details.append("Start Time: ").append(this.startTime).append("\n");
    details.append("End Day: ").append(this.endDay).append("\n");
    details.append("End Time: ").append(this.endTime).append("\n");
    details.append("Host: ").append(this.host).append("\n");
    details.append("Invitees: ").append("\n");
    for (User u : this.invitees) {
      details.append(u.toString()).append("\n");
    }
    return details.toString();
  }
}
