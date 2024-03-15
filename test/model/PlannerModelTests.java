package model;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

import schedule.Days;
import schedule.Event;
import schedule.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A class that represents tests for the methods in PlannerModel.
 */
public class PlannerModelTests {
  private User user1;

  private PlannerSystem plannerSystem;
  private Event event1;

  private Event event2;

  private Event event3;

  private Event event4;


  /**
   * Initialization of variables used in testing.
   */
  private void setUp() {
    user1 = new User("Ben");
    User user2 = new User("Alejandro");
    plannerSystem = new PlannerSystem(List.of(user1, user2));
    event1 = new Event.Builder().name("Homework")
            .location("Churchill").isOnline(true).startDay(Days.MONDAY).startTime(1200).
            endDay(Days.THURSDAY).endTime(2100).host(user1).
            invitees(List.of(user2)).build();
    event2 = new Event.Builder().name("Class").location("Churchill").isOnline(true).
            startDay(Days.FRIDAY).startTime(1500).endDay(Days.FRIDAY).endTime(1630).host(user2).
            invitees(List.of(user1)).build();
    event3 = new Event.Builder().name("TimeConflict").location("Churchill").isOnline(true).
            startDay(Days.TUESDAY).startTime(1500).endDay(Days.THURSDAY).endTime(1630).host(user2).
            invitees(List.of(user1)).build();
    event4 = new Event.Builder().name("EdgeCase").location("Churchill").isOnline(true).
            startDay(Days.FRIDAY).startTime(1630).endDay(Days.MONDAY).endTime(1200).host(user2).
            invitees(List.of(user1)).build();
  }

  @Test
  public void testAddEvent() {
    this.setUp();
    plannerSystem.addEvent(user1, event1);
    assertEquals(1, user1.getSchedule().getEventsInSchedule().size());
    plannerSystem.addEvent(user1, event2);
    Assert.assertThrows(IllegalArgumentException.class
            , () -> plannerSystem.addEvent(user1, event1));
    Assert.assertThrows(IllegalArgumentException.class
            , () -> plannerSystem.addEvent(user1, event3));
    plannerSystem.addEvent(user1, event4);
    assertEquals(List.of(event1, event2, event4), user1.getSchedule().getEventsInSchedule());
  }

  @Test
  public void testRemoveEvent() {
    this.setUp();
    plannerSystem.addEvent(user1, event1);
    plannerSystem.removeEvent(user1, event1);
    assertTrue(user1.getSchedule().getEventsInSchedule().isEmpty());
    Assert.assertThrows(IllegalArgumentException.class
            , () -> plannerSystem.removeEvent(user1, event2));
  }

  @Test
  public void testXMLUpload() {
    this.setUp();
    assertEquals(2, plannerSystem.getAllUsers().size());
    User user = new User("TestUser");
    Event event1 = new Event.Builder().name("Homework")
            .location("Churchill").isOnline(true).startDay(Days.MONDAY).startTime(1200).
            endDay(Days.THURSDAY).endTime(2100).host(new User("Ben")).
            build();
    user.getSchedule().addEvent(event1);
    XMLUtil.convertUserToXML(user);
    File file = new File("TestUser.xml");
    try {
      plannerSystem.uploadSchedule(file);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    assertEquals(3, plannerSystem.getAllUsers().size());
  }
}
