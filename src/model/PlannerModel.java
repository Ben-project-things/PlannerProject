package model;

/**
 * Represents the functions that the overall Planner system is able to perform.
 */
public interface PlannerModel {

  //TODO: find out what parameters and return values are
  /**
   * Uploads a given XML file to the planner database.
   */
  void uploadSchedule();

  /**
   * Saves the current schedule of the user as an XML file.
   */
  void saveSchedule();

}
