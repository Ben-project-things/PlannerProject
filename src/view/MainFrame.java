package view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.File;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


import controller.Features;
import model.ReadOnlyPlannerModel;
import schedule.User;

/**
 * Class that deals with the main frame of the planner system view (the frame displaying the
 * planner and events).
 */
public class MainFrame extends JFrame implements PlannerView {
  private Features features;
  private final ReadOnlyPlannerModel model;
  private JComboBox<User> userDropdown;
  private SchedulePanel schedulePanel;
  private JButton createEventButton;
  private JButton scheduleEventButton;
  private JButton toggleHostColor;
  private boolean isToggled;
  private JMenuItem loadItem;
  private JMenuItem saveItem;

  /**
   * Public constructor that takes in the readOnlyModel and constructs the view.
   * @param model is the actual read only model to pass through
   */
  public MainFrame(ReadOnlyPlannerModel model) {
    this.model = Objects.requireNonNull(model);
    this.initializeUI();
    this.setUpListeners();
  }

  /**
   * Helper method which initializes the UI.
   */
  private void initializeUI() {
    setTitle("Planner System");
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    loadItem = new JMenuItem("Add calendar");
    saveItem = new JMenuItem("Save calendars");

    saveItem.addActionListener(e -> saveXMLFile());
    fileMenu.add(loadItem);
    fileMenu.add(saveItem);
    menuBar.add(fileMenu);
    setJMenuBar(menuBar);

    JPanel southPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    this.userDropdown = new JComboBox<>();
    this.displayUsers();

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    southPanel.add(userDropdown, gbc);
    JPanel buttonPanel = new JPanel();
    createEventButton = new JButton("Create Event");
    scheduleEventButton = new JButton("Schedule Event");
    toggleHostColor = new JButton("Toggle host color");
    isToggled = false;
    buttonPanel.add(createEventButton);
    buttonPanel.add(scheduleEventButton);
    buttonPanel.add(toggleHostColor);
    gbc.gridx = 1;
    gbc.weightx = 1;
    southPanel.add(buttonPanel, gbc);
    add(southPanel, BorderLayout.SOUTH);

    schedulePanel = new SchedulePanel(this.model);

    add(schedulePanel, BorderLayout.CENTER);

    setVisible(true);
  }

  /**
   * Private helper to set up the listeners to dynamically update the schedule displayed.
   */
  private void setUpListeners() {
    userDropdown.addActionListener(e ->
            features.displayUser((User) userDropdown.getSelectedItem()));
    createEventButton.addActionListener(e ->
            features.createEventMainFrame());
    scheduleEventButton.addActionListener(e ->
            features.createScheduleMainFrame());
    toggleHostColor.addActionListener(e ->
            features.toggleHostMode(isToggled));
    loadItem.addActionListener(e ->
            features.loadSchedule());
    saveItem.addActionListener(e ->
            features.saveSchedule());
  }

  @Override
  public void createEventFrame() {
    EventFrame eventFrame = new EventFrame(this.model, this.features);
    eventFrame.setVisible(true);
  }

  @Override
  public void scheduleEventFrame() {
    ScheduleFrame scheduleFrame = new ScheduleFrame(this.model, this.features);
    scheduleFrame.setVisible(true);
  }

  @Override
  public void toggleHostColorMode(boolean enabled) {
    if (enabled) {
      this.schedulePanel.setEventDecorator(new DefaultEventMode());
      this.isToggled = false;
    } else {
      this.schedulePanel.setEventDecorator(new HostEventMode(model.getSelectedUser()));
      this.isToggled = true;
    }
  }

  @Override
  public File loadXMLFile() {
    try {
      JFileChooser chooser = new JFileChooser();
      int choice = chooser.showOpenDialog(this);
      if (choice == JFileChooser.APPROVE_OPTION) {
        File file = chooser.getSelectedFile();
        System.out.println("Load XML File: " + file.getAbsolutePath());
        return file;
      }
    } catch (Exception e) {
      this.showError("Failed to load XML: " + e.getMessage());
    }
    return null;
  }

  @Override
  public void saveXMLFile() {
    try {
      JFileChooser chooser = new JFileChooser();
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int choice = chooser.showSaveDialog(this);
      if (choice == JFileChooser.APPROVE_OPTION) {
        File directory = chooser.getSelectedFile();
        System.out.println("Save XML File to: " + directory.getAbsolutePath());
      }
    } catch (Exception e) {
      this.showError("Failed to save XML: " + e.getMessage());
    }
  }


  @Override
  public void refreshUI() {
    schedulePanel.revalidate();
    schedulePanel.repaint();
  }

  @Override
  public void makeVisible(boolean visible) {
    this.setVisible(visible);
  }

  @Override
  public void displayUsers() {
    this.userDropdown.removeAllItems();
    for (User user : model.getAllUsers()) {
      this.userDropdown.addItem(user);
    }
  }

  @Override
  public void displayUser(User user) {
    schedulePanel.setUser(user.getSchedule());
  }

  @Override
  public void showError(String message) {
    JOptionPane.showMessageDialog(
            this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setFeaturesListener(Features features) {
    this.features = Objects.requireNonNull(features);
    this.schedulePanel.setFeatures(features);
  }

}
