package schedule;

import java.util.Objects;

/**
 * Represents a user who has a name and a schedule.
 */
public class User {

  private final String name;
  private final ScheduleModel schedule;

  /**
   * Constructor of a basic user to identify them by their name and an empty schedule.
   *
   * @param name is the name of the user
   */
  public User(String name) {
    this.name = name;
    this.schedule = new Schedule();
  }

  /**
   * Constructor of a user with an existing schedule.
   *
   * @param name is the name of the user
   */
  public User(String name, ScheduleModel schedule) {
    this.name = name;
    this.schedule = Objects.requireNonNull(schedule);
  }

  /**
   * Observation of a user's name.
   *
   * @return the user's name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Observation of a user's schedule.
   *
   * @return the user's schedule
   */
  public ScheduleModel getSchedule() {
    return this.schedule;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
