package schedule;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A class that represents tests for the methods in ScheduleModel.
 */
public class ScheduleModelTests {
  private User user1;
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
    user1.getSchedule().addEvent(event1);
    assertEquals(1, user1.getSchedule().getEventsInSchedule().size());
    user1.getSchedule().addEvent(event2);
    Assert.assertThrows(IllegalArgumentException.class
            , () -> user1.getSchedule().addEvent(event1));
    Assert.assertThrows(IllegalArgumentException.class
            , () -> user1.getSchedule().addEvent(event3));
    user1.getSchedule().addEvent(event4);
  }

  @Test
  public void testRemoveEvent() {
    this.setUp();
    user1.getSchedule().addEvent(event1);
    user1.getSchedule().removeEvent(event1);
    assertTrue(user1.getSchedule().getEventsInSchedule().isEmpty());
    Assert.assertThrows(IllegalArgumentException.class
            , () -> user1.getSchedule().removeEvent(event2));
  }

  @Test
  public void testGetEventsInSchedule() {
    this.setUp();
    user1.getSchedule().addEvent(event1);
    user1.getSchedule().addEvent(event2);
    assertEquals(2, user1.getSchedule().getEventsInSchedule().size());
  }
}
