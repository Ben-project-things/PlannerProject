package schedule;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * A class that represents tests for the methods in Event.
 */
public class EventTests {

  private Event event1;

  private Event event2;

  private Event event3;

  private Event event4;
  private Event event5;

  /**
   * Initialization of variables used in testing.
   */
  private void setUp() {
    User user1 = new User("Ben");
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
    event5 = new Event.Builder().name("Homework")
            .location("Churchill").isOnline(true).startDay(Days.MONDAY).startTime(1200).
            endDay(Days.THURSDAY).endTime(2100).host(user1).
            invitees(List.of(user2)).build();
  }

  @Test
  public void testTimeValidity() {
    this.setUp();
    Assert.assertThrows(IllegalArgumentException.class
            , () -> event1.setStartTime(2459));
    Assert.assertThrows(IllegalArgumentException.class
            , () -> event1.setStartTime(2360));
  }

  @Test
  public void testCheckOverlap() {
    this.setUp();
    assertTrue(event1.checkOverlap(event3));
    assertFalse(event1.checkOverlap(event2));
    assertFalse(event1.checkOverlap(event4));
  }

  @Test
  public void testCheckDuringTime() {
    this.setUp();
    assertTrue(event1.checkDuringTime(Days.WEDNESDAY, 1300));
    assertTrue(event1.checkDuringTime(Days.MONDAY, 1200));
    assertFalse(event1.checkDuringTime(Days.MONDAY, 1159));
    assertFalse(event1.checkDuringTime(Days.FRIDAY, 1300));
  }

  @Test
  public void testEquality() {
    this.setUp();
    assertEquals(event1, event5);
    assertNotEquals(event1, event2);
  }
}
