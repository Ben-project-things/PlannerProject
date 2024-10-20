package schedule;

/**
 * Representation of the days of the week as a concrete class.
 */
public enum Days {
  SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

  /**
   * Method to get the next day from an existing day.
   * @return the next day
   */
  public Days next() {
    return values()[(this.ordinal() + 1) % values().length];
  }

}
