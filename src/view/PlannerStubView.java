package view;

import java.io.File;

import controller.Features;
import schedule.User;

/**
 * Stub class to test controller functionality with a stub view.
 */
public class PlannerStubView implements PlannerView {

  public boolean loadXMLFileCalled;
  public boolean saveXMLFileCalled;
  public boolean refreshUICalled;
  public boolean makeVisibleCalled;
  public boolean displayUsersCalled;
  public boolean displayUserCalled;
  public boolean showErrorCalled;
  public boolean setFeaturesListenerCalled;
  public boolean createEventFrameCalled;
  public boolean scheduleEventFrameCalled;
  public boolean toggleHostColorMode;

  /**
   * Constructor to set up boolean flags.
   */
  public PlannerStubView() {
    loadXMLFileCalled = false;
    saveXMLFileCalled = false;
    refreshUICalled = false;
    makeVisibleCalled = false;
    displayUsersCalled = false;
    displayUserCalled = false;
    showErrorCalled = false;
    setFeaturesListenerCalled = false;
    createEventFrameCalled = false;
    scheduleEventFrameCalled = false;
    toggleHostColorMode = false;
  }

  @Override
  public File loadXMLFile() {
    loadXMLFileCalled = true;
    System.out.println("loadXMLFile called");
    return null;
  }

  @Override
  public void saveXMLFile() {
    saveXMLFileCalled = true;
    System.out.println("saveXMLFile called");
  }

  @Override
  public void refreshUI() {
    refreshUICalled = true;
    System.out.println("refreshUI called");
  }

  @Override
  public void makeVisible(boolean visible) {
    makeVisibleCalled = true;
    System.out.println("makeVisible called");
  }

  @Override
  public void displayUsers() {
    displayUsersCalled = true;
    System.out.println("displayUsers called");
  }

  @Override
  public void displayUser(User user) {
    displayUserCalled = true;
    System.out.println("displayUser called");
  }

  @Override
  public void showError(String message) {
    showErrorCalled = true;
    System.out.println("showError called");
  }

  @Override
  public void setFeaturesListener(Features features) {
    setFeaturesListenerCalled = true;
    System.out.println("setFeaturesListener called");
  }

  @Override
  public void createEventFrame() {
    createEventFrameCalled = true;
    System.out.println("createEventFrame called");
  }

  @Override
  public void scheduleEventFrame() {
    scheduleEventFrameCalled = true;
    System.out.println("scheduleEventFrame called");
  }

  @Override
  public void toggleHostColorMode(boolean enabled) {
    toggleHostColorMode = true;
    System.out.println("toggleHostColorMode called");
  }

}
