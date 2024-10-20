package controller;

import org.junit.Test;

import model.PlannerStubModel;
import schedule.AnyTimeSchedulingStrategy;
import schedule.Event;
import schedule.SchedulingStrategy;
import schedule.User;
import view.PlannerStubView;

import static org.junit.Assert.assertTrue;

/**
 * Test class for testing the mock of the controller.
 */
public class ControllerTests {

  private PlannerStubView mockView;

  private PlannerStubModel mockModel;

  private Features controller;

  /**
   * Initialization of variables used in testing.
   */
  private void setup() {
    mockModel = new PlannerStubModel();
    mockView = new PlannerStubView();
    SchedulingStrategy mockStrategy = new AnyTimeSchedulingStrategy();
    controller = new PlannerController(mockView, mockStrategy);
    controller.launch(mockModel);
  }

  @Test
  public void testLoadXML() {
    this.setup();
    controller.loadSchedule();
    assertTrue(mockView.loadXMLFileCalled);
    assertTrue(mockModel.uploadSchedule);
  }

  @Test
  public void testSaveSchedule() {
    this.setup();
    controller.saveSchedule();
    assertTrue(mockModel.saveSchedule);
  }

  @Test
  public void testCreateEventMainFrame() {
    this.setup();
    controller.createEventMainFrame();
    assertTrue(mockView.createEventFrameCalled);
  }

  @Test
  public void testCreateEvent() {
    this.setup();
    controller.createEvent(new Event());
    assertTrue(mockModel.addEvent);
  }

  @Test
  public void testModifyEvent() {
    this.setup();
    controller.modifyEvent(new Event(), new Event());
    assertTrue(mockView.refreshUICalled);
  }

  @Test
  public void testRemoveEvent() {
    this.setup();
    controller.removeEvent(new Event());
    assertTrue(mockView.refreshUICalled);
    assertTrue(mockModel.removeEvent);
  }

  @Test
  public void testDisplayUser() {
    this.setup();
    controller.displayUser(new User("Ben"));
    assertTrue(mockView.displayUserCalled);
  }

  @Test
  public void scheduleAutomaticEvent() {
    this.setup();

    controller.createScheduleMainFrame();

    assertTrue(mockView.scheduleEventFrameCalled);
  }

  @Test
  public void launch() {
    this.setup();
    controller.launch(mockModel);
    assertTrue(mockView.makeVisibleCalled);
  }

}
