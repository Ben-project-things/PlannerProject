package model;

import java.io.File;
import java.util.List;

import schedule.Days;
import schedule.Event;
import schedule.User;

/**
 * Stub class to test controller functionality with a stub model.
 */
public class PlannerStubModel implements PlannerModel {

  public boolean uploadSchedule;
  public boolean saveSchedule;
  public boolean addEvent;
  public boolean modifyEvent;
  public boolean removeEvent;
  public boolean autoSchedule;
  public boolean setSelectedUser;
  public boolean displayUser;
  public boolean eventAtTime;
  public boolean getAllUsers;
  public boolean getAllUserEvents;
  public boolean getAllEvents;
  public boolean getSelectedUser;

  /**
   * Constructor to set up boolean flags.
   */
  public PlannerStubModel() {
    uploadSchedule = false;
    saveSchedule = false;
    addEvent = false;
    modifyEvent = false;
    removeEvent = false;
    autoSchedule = false;
    setSelectedUser = false;
    displayUser = false;
    eventAtTime = false;
    getAllUsers = false;
    getAllUserEvents = false;
    getAllEvents = false;
    getSelectedUser = false;
  }

  @Override
  public void uploadSchedule(File file) throws Exception {
    uploadSchedule = true;
    System.out.println("uploadSchedule called");
  }

  @Override
  public void saveSchedule(User user) {
    saveSchedule = true;
    System.out.println("uploadSchedule called");
  }

  @Override
  public void addEvent(Event e) {
    addEvent = true;
    System.out.println("addEvent called");
  }

  @Override
  public void modifyEvent(User user, Event e, Event e2) {
    modifyEvent = true;
    System.out.println("modifyEvent called");
  }

  @Override
  public void removeEvent(User user, Event e) {
    removeEvent = true;
    System.out.println("removeEvent called");
  }

  @Override
  public void autoSchedule(User user) {
    autoSchedule = true;
    System.out.println("autoSchedule called");
  }

  @Override
  public void setSelectedUser(User user) {
    setSelectedUser = true;
    System.out.println("setSelectedUser called");
  }

  @Override
  public void displayUser(User user) {
    displayUser = true;
    System.out.println("displayUser called");
  }

  @Override
  public Event eventAtTime(User user, Days day, int time) {
    eventAtTime = true;
    System.out.println("eventAtTime called");
    return null;
  }

  @Override
  public List<User> getAllUsers() {
    getAllUsers = true;
    System.out.println("getAllUsers called");
    return List.of();
  }

  @Override
  public List<Event> getAllUserEvents(User user) {
    getAllUserEvents = true;
    System.out.println("getAllUserEvents called");
    return List.of();
  }

  @Override
  public List<Event> getAllEvents() {
    getAllEvents = true;
    System.out.println("getAllEvents called");
    return List.of();
  }

  @Override
  public User getSelectedUser() {
    getSelectedUser = true;
    System.out.println("getSelectedUser called");
    return null;
  }
}
