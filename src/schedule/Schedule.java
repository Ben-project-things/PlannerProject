package schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Implementation of a schedule allowing for the addition, and removal of events from this schedule
 * as well as modification of events.
 */
public class Schedule implements ScheduleModel {
  private final List<Event> eventList;

  public Schedule() {
    this.eventList = new ArrayList<>();
  }

  @Override
  public void addEvent(Event e) {
    if (!this.eventList.contains(Objects.requireNonNull(e)) && this.noTimeDiscrepancy(e)) {
      this.eventList.add(e);
    } else {
      throw new IllegalArgumentException("Event is already on the schedule.");
    }
  }

  /**
   * Helper method to check whether any events currently in this schedule collide with the given
   * event.
   *
   * @param e is the event to check
   * @return true if there is no time discrepancy and the event can be added
   */
  private boolean noTimeDiscrepancy(Event e) {
    for (Event eventInSchedule : eventList) {
      if (eventInSchedule.checkOverlap(e)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void modifyEvent(Event e) {
    if (this.eventList.contains(Objects.requireNonNull(e))) {
      Scanner scanner = new Scanner(System.in);
      boolean keepModifying = true;

      while (keepModifying) {
        System.out.println("1: Name");
        System.out.println("2: Start Day");
        System.out.println("3: Start Time");
        System.out.println("4: End Day");
        System.out.println("5: End Time");
        System.out.println("6: Location");
        System.out.println("7: Done Modifying");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1:
            System.out.println("Enter new name:");
            e.setName(scanner.nextLine());
            break;
          case 2:
            System.out.println("Enter new start day (Monday, Tuesday, etc.):");
            String startDay = scanner.nextLine().trim().toUpperCase();
            try {
              Days newStartDay = Days.valueOf(startDay);
              e.setStartDay(newStartDay);
            } catch (IllegalArgumentException ex) {
              System.out.println("Invalid day. Please enter a valid day of the week.");
            }
            break;
          case 3:
            System.out.println("Enter new start time (HHMM):");
            e.setStartTime(scanner.nextInt());
            scanner.nextLine();
            break;
          case 4:
            System.out.println("Enter new end day (Monday, Tuesday, etc.):");
            String endDay = scanner.nextLine().trim().toUpperCase();
            try {
              Days newEndDay = Days.valueOf(endDay);
              e.setEndDay(newEndDay);
            } catch (IllegalArgumentException ex) {
              System.out.println("Invalid day. Please enter a valid day of the week.");
            }
            break;
          case 5:
            System.out.println("Enter new end time (HHMM):");
            e.setEndTime(scanner.nextInt());
            scanner.nextLine();
            break;
          case 6:
            System.out.println("Enter new location:");
            e.setLocation(scanner.nextLine());
            break;
          case 7:
            keepModifying = false;
            break;
          default:
            System.out.println("Invalid selection. Please try again.");
        }
      }
    } else {
      throw new IllegalArgumentException("Cannot modify an event the user is not in.");
    }
  }

  @Override
  public void removeEvent(Event e) {
    if (this.eventList.contains(Objects.requireNonNull(e))) {
      this.eventList.remove(e);
    } else {
      throw new IllegalArgumentException("Event is not present in the schedule.");
    }
  }

  /**
   * Observation of a schedules events.
   *
   * @return the events in this schedule
   */
  public List<Event> getEventsInSchedule() {
    return this.eventList;
  }
}
