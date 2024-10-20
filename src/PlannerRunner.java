import java.util.List;

import controller.Features;
import controller.PlannerController;
import model.PlannerModel;
import model.PlannerSystem;
import schedule.Days;
import schedule.Event;
import schedule.SchedulingStrategy;
import schedule.User;
import schedule.WorkHoursSchedulingStrategy;
import schedule.AnyTimeSchedulingStrategy;
import view.MainFrame;
import view.PlannerView;

/**
 * Class that holds the main method to run the planner system.
 */
public final class PlannerRunner {

  /**
   * Main method which runs the planner system.
   */
  public static void main(String[] args) {
    String strategyType = args.length > 0 ? args[0] : "anytime";
    SchedulingStrategy strategy;

    switch (strategyType.toLowerCase()) {
      case "workhours":
        strategy = new WorkHoursSchedulingStrategy();
        break;
      case "anytime":
      default:
        strategy = new AnyTimeSchedulingStrategy();
        break;
    }

    User user1 = new User("Ben");
    User user2 = new User("Brooke");

    Event event1 = new Event.Builder().name("Homework")
            .location("Churchill").isOnline(true).startDay(Days.MONDAY).startTime(1200).
            endDay(Days.THURSDAY).endTime(2100).host(user1).build();
    Event event2 = new Event.Builder().name("Class").location("Churchill").isOnline(true).
            startDay(Days.FRIDAY).startTime(1500).endDay(Days.FRIDAY).endTime(1630).host(user2).
            invitees(List.of(user1)).build();

    List<Event> events = List.of(event1, event2);

    for (Event event : events) {
      event.getHost().getSchedule().addEvent(event);
      for (User user : event.getInvitees()) {
        if (user != event.getHost()) {
          user.getSchedule().addEvent(event);
        }
      }
    }

    PlannerModel model = new PlannerSystem(List.of(user1, user2));
    PlannerView view = new MainFrame(model);
    Features features = new PlannerController(view, strategy);
    features.launch(model);
  }
}
