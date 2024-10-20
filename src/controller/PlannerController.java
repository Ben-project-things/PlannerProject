package controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import model.PlannerModel;
import schedule.Event;
import schedule.SchedulingStrategy;
import schedule.User;
import view.PlannerView;

/**
 * Class which represents the client implementation of a planner controller.
 */
public class PlannerController implements Features {
  private final PlannerView view;
  private PlannerModel model;
  private final SchedulingStrategy schedulingStrategy;

  /**
   * Constructs the controller and links it with the model and view, setting up the listeners.
   *
   * @param view is the view of the system being used
   */
  public PlannerController(PlannerView view, SchedulingStrategy schedulingStrategy) {
    this.view = Objects.requireNonNull(view);
    this.schedulingStrategy = Objects.requireNonNull(schedulingStrategy);
    this.view.setFeaturesListener(this);
  }

  @Override
  public void loadSchedule() {
    try {
      model.uploadSchedule(view.loadXMLFile());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void saveSchedule() {
    model.saveSchedule(this.model.getSelectedUser());
  }

  @Override
  public void createEventMainFrame() {
    view.createEventFrame();
  }

  @Override
  public void createScheduleMainFrame() {
    view.scheduleEventFrame();
  }

  @Override
  public void toggleHostMode(boolean toggled) {
    view.toggleHostColorMode(toggled);
  }

  @Override
  public void createEvent(Event event) {
    model.addEvent(event);
    view.refreshUI();
  }

  @Override
  public void modifyEvent(Event event, Event event2) {
    event.setName(event2.getName());
    event.setStartDay(event2.getStartDay());
    event.setStartTime(event2.getStartTime());
    event.setEndDay(event2.getEndDay());
    event.setEndTime(event2.getEndTime());
    event.setIsOnline(event2.getIsOnline());
    event.setLocation(event2.getLocation());
    view.refreshUI();
  }

  @Override
  public void removeEvent(Event event) {
    model.removeEvent(model.getSelectedUser(), event);
    view.refreshUI();
  }

  @Override
  public void displayUser(User user) {
    view.displayUser(user);
    model.setSelectedUser(user);
  }

  @Override
  public void scheduleAutomaticEvent(String eventName, int duration, String location,
                                     boolean isOnline, List<User> attendees, User host) {
    Optional<Event> scheduledEvent = schedulingStrategy.autoSchedule(
            eventName, duration, location, isOnline, attendees, host);
    if (scheduledEvent.isPresent()) {

      model.addEvent(scheduledEvent.get());

      view.refreshUI();
    } else {
      view.showError(
              "Failed to schedule event: No suitable time slot available for selected " +
                      "users.");
    }
  }

  @Override
  public void launch(PlannerModel model) {
    this.model = Objects.requireNonNull(model);
    view.makeVisible(true);
  }


}
