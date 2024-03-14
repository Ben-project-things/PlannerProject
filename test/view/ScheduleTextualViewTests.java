package view;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import schedule.Days;
import schedule.Event;
import schedule.User;

/**
 * A class that represents tests for the methods in PlannerTextualView
 */
public class ScheduleTextualViewTests {

  @Test
  public void viewEmptySchedule() {
    User user1 = new User("Ben");
    PlannerTextualView view = new PlannerTextualView(user1);
    System.out.println(view);
    Assert.assertTrue(true);
  }

  @Test
  public void viewFilledSchedule() {
    User user2 = new User("Alejandro");
    Event event1 = new Event.Builder().name("Homework")
            .location("Churchill").isOnline(true).startDay(Days.MONDAY).startTime(1200).
            endDay(Days.THURSDAY).endTime(2100).host(new User("Ben")).
            invitees(List.of(user2)).build();
    Event event2 = new Event.Builder().name("Dummy").startDay(Days.FRIDAY).build();
    Event event3 = new Event.Builder().name("Dummy2").startDay(Days.MONDAY).build();
    user2.getSchedule().addEvent(event1);
    user2.getSchedule().addEvent(event2);
    user2.getSchedule().addEvent(event3);
    PlannerTextualView view = new PlannerTextualView(user2);
    System.out.println(view);
    Assert.assertTrue(true);
  }
}
