package view;

import schedule.Days;
import schedule.Event;
import schedule.User;

/**
 * Represents a textual view of a Schedule
 */
public class PlannerTextualView {

  private final User user;

  /**
   * Constructs a view of the given model.
   */
  public PlannerTextualView(User user) {
    this.user = user;
  }
  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append("User:").append(user.getName()).append("\n");
    for (Days d: Days.values()) {
      out.append(d).append(":\n");
      for (Event e: user.getSchedule().getEventsInSchedule()) {
        if (e.occursOnStartDay(d)) {
          out.append("\tname: ").append(e.getName()).append("\n");
          out.append("\ttime: ").append(e.getStartDay()).append(": ").append(e.getStartTime())
                  .append(" -> ").append(e.getEndDay()).append(": ").append(e.getEndTime())
                  .append("\n");
          out.append("\tlocation: ").append(e.getLocation()).append("\n");
          out.append("\tonline: ").append(e.getIsOnline()).append("\n");
          out.append("\tinvitees: ");
          if (e.getInvitees().isEmpty()) {
            out.append("\n");
          }
          for (User user : e.getInvitees()) {
            out.append("\t").append(user.getName()).append("\n");
          }
        }
      }
    }
    return out.toString();
  }
}
