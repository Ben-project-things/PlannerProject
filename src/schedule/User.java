package schedule;

/**
 * Represents a user who has a name and a schedule.
 */
public class User {

  private final String name;
  private final Schedule schedule;

  /**
   * Constructor of a basic user to identify them by their name and an empty schedule.
   * @param name is the name of the user
   */
  public User(String name) {
    this.name = name;
    this.schedule = new Schedule();
  }
}
