package schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A representation of an event which includes the name and location of an event, if its online,
 * what day it starts and ends, the start and end time, and the users hosting and that are involved.
 * INVARIANT:
 *  0 <= startTime <= 2359 && 0 <= endTime <= 2359
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
   * @param name is the name to call the event
   */
  public void setName(String name) {
    this.name = Objects.requireNonNull(name);
  }

  /**
   * Setter method to set the location of this event.
   * @param location is the location of the event
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * Setter method to set whether this event is online or not.
   * @param isOnline is whether this event is online
   */
  public void setIsOnline(boolean isOnline) {
    this.isOnline = isOnline;
  }

  /**
   * Setter method to set when this event starts.
   * @param startDay is the day the event will start on.
   */
  public void setStartDay(Days startDay) {
    this.startDay = startDay;
  }

  /**
   * Setter method to set start time of the event. Throws an exception if time given is invalid.
   * @param startTime is the start time this event will be
   */
  public void setStartTime(int startTime) {
    if (endTime >= 0 && endTime <= 2359) {
      this.startTime = startTime;
    }
    else {
      throw new IllegalArgumentException("Time must be between 00:00 and 23:59");
    }
  }

  /**
   * Setter method to set when this event ends.
   * @param endDay is the day the event will end on.
   */
  public void setEndDay(Days endDay) {
    this.endDay = endDay;
  }

  /**
   * Setter method to set end time of the event. Throws an exception if time given is invalid.
   * @param endTime is the end time this event will be
   */
  public void setEndTime(int endTime) {
    if (endTime >= 0 && endTime <= 2359) {
      this.endTime = endTime;
    }
    else {
      throw new IllegalArgumentException("Time must be between 00:00 and 23:59");
    }
  }

  /**
   * Setter method to set the host of this event.
   * @param host is the host user of this event
   */
  public void setHost(User host) {
    this.host = Objects.requireNonNull(host);
  }

  /**
   * Setter method to set the invitees to this event.
   * @param invitees is the list of users invited to this event
   */
  public void setInvitees(List<User> invitees) {
    this.invitees = invitees;
  }

  /**
   * Builder to construct events.
   */
  public static class Builder {
    private Event event;

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
     * @param name is the name of the event
     * @return the builder to continue constructing the event object
     */
    public Builder name(String name) {
      this.event.setName(name);
      return this;
    }

    /**
     * Builder method to set the location of this event.
     * @param location is the location of the event
     * @return the builder to continue constructing the event object
     */
    public Builder location(String location) {
      this.event.setLocation(location);
      return this;
    }

    /**
     * Builder method to set whether the event is online.
     * @param isOnline is if the event is online
     * @return the builder to continue constructing the event object
     */
    public Builder isOnline(boolean isOnline) {
      this.event.setIsOnline(isOnline);
      return this;
    }

    /**
     * Builder method to set the start day of this event.
     * @param startDay is the start day of the event
     * @return the builder to continue constructing the event object
     */
    public Builder startDay(Days startDay) {
      this.event.setStartDay(startDay);
      return this;
    }

    /**
     * Builder method to set the start time of this event.
     * @param startTime is the start time of the event
     * @return the builder to continue constructing the event object
     */
    public Builder startTime(int startTime) {
      this.event.setStartTime(startTime);
      return this;
    }

    /**
     * Builder method to set the end day of this event.
     * @param endDay is the end day of the event
     * @return the builder to continue constructing the event object
     */
    public Builder endDay(Days endDay) {
      this.event.setEndDay(endDay);
      return this;
    }

    /**
     * Builder method to set the end time of this event.
     * @param endTime is the end time of the event
     * @return the builder to continue constructing the event object
     */
    public Builder endTime(int endTime) {
      this.event.setEndTime(endTime);
      return this;
    }

    /**
     * Builder method to set the host of this event.
     * @param host is the host of the event
     * @return the builder to continue constructing the event object
     */
    public Builder host(User host) {
      this.event.setHost(host);
      return this;
    }

    /**
     * Builder method to set the invitees of this event.
     * @param invitees is the invitees of the event
     * @return the builder to continue constructing the event object
     */
    public Builder invitees(List<User> invitees) {
      this.event.setInvitees(invitees);
      return this;
    }

    /**
     * Build method to construct and return the new event.
     * @return the constructed event
     */
    public Event build() {
      return event;
    }
  }
}
