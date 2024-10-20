package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

import java.util.Set;

import controller.Features;
import model.ReadOnlyPlannerModel;
import schedule.Days;
import schedule.User;
import schedule.Event;

/**
 * Class that deals with the individual elements of the panel that will be on the frame.
 */
public class EventPanel extends JPanel implements EventPanelView {
  private final ReadOnlyPlannerModel model;
  private Features features;
  private JTextField nameField;
  private JTextField startTimeField;
  private JTextField endTimeField;
  private JTextField locationField;
  private JComboBox<Days> startDayDropdown;
  private JComboBox<Days> endDayDropdown;
  private JComboBox<Boolean> onlineStatusDropdown;
  private JList<User> userList;
  private JButton createButton;
  private JButton modifyButton;
  private JButton removeButton;
  private boolean isEditMode;
  private Event selectedEvent;

  /**
   * Public constructor to initialize the event panel, setting up its properties, buttons, and
   * other JPanel features.
   *
   * @param model    is the read only model so that the event panel can have access to read the data
   * @param features is the controller with the features connected to the view
   */
  public EventPanel(ReadOnlyPlannerModel model, Features features) {
    this.model = Objects.requireNonNull(model);
    this.features = Objects.requireNonNull(features);
    setLayout(new BorderLayout());
    this.initializeEventDetailsPanel();
    this.initializeUserList();
    this.initializeButtons();
    this.initializeEventHandlers();
    this.isEditMode = false;
  }

  /**
   * Constructor to set up an EventPanel with preloaded JElements of the selectedEvent.
   *
   * @param model         is the read only model so that the event panel can pass it on and use its
   *                      data
   * @param features      is the controller with the features connected to the view
   * @param selectedEvent is the selected event to prepopulate
   */
  public EventPanel(ReadOnlyPlannerModel model, Features features, Event selectedEvent) {
    this.model = Objects.requireNonNull(model);
    this.features = Objects.requireNonNull(features);
    setLayout(new BorderLayout());
    this.initializeEventDetailsPanel();
    this.initializeUserList();
    this.initializeButtons();
    this.initializeEventHandlers();
    loadEventForModification(selectedEvent);
  }

  /**
   * Private helper to set up the event details.
   */
  private void initializeEventDetailsPanel() {
    JPanel detailsPanel = new JPanel(new GridLayout(0, 2));

    detailsPanel.add(new JLabel("Event Name:"));
    nameField = new JTextField(10);
    detailsPanel.add(nameField);

    startDayDropdown = new JComboBox<>(Days.values());
    endDayDropdown = new JComboBox<>(Days.values());
    startTimeField = new JTextField(5);
    endTimeField = new JTextField(5);
    detailsPanel.add(new JLabel("Start Day:"));
    detailsPanel.add(startDayDropdown);
    detailsPanel.add(new JLabel("Start Time:"));
    detailsPanel.add(startTimeField);
    detailsPanel.add(new JLabel("End Day:"));
    detailsPanel.add(endDayDropdown);
    detailsPanel.add(new JLabel("End Time:"));
    detailsPanel.add(endTimeField);

    detailsPanel.add(new JLabel("Is Online:"));
    onlineStatusDropdown = new JComboBox<>(new Boolean[]{Boolean.TRUE, Boolean.FALSE});
    detailsPanel.add(onlineStatusDropdown);

    detailsPanel.add(new JLabel("Location:"));
    locationField = new JTextField(10);
    detailsPanel.add(locationField);

    add(detailsPanel, BorderLayout.NORTH);
  }

  @Override
  public void displayErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message,
            "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Private helper that sets up the userList displayed at the bottom.
   */
  private void initializeUserList() {
    List<User> users = this.model.getAllUsers();
    User[] userArray = users.toArray(new User[0]);
    DefaultListModel<User> userListModel = new DefaultListModel<>();
    for (User user : users) {
      userListModel.addElement(user);
    }
    this.userList = new JList<>(userListModel);

    add(new JScrollPane(userList), BorderLayout.CENTER);
  }

  /**
   * Private helper that sets up the initial buttons on the EventPanel.
   */
  private void initializeButtons() {
    JPanel buttonPanel = new JPanel(new FlowLayout());
    createButton = new JButton("Create");
    modifyButton = new JButton("Modify");
    removeButton = new JButton("Remove");
    buttonPanel.add(createButton);
    buttonPanel.add(modifyButton);
    buttonPanel.add(removeButton);
    add(buttonPanel, BorderLayout.SOUTH);
  }

  /**
   * Deals with setting up action listener temporarily.
   */
  private void initializeEventHandlers() {
    createButton.addActionListener(e -> {
      if (validateInputs()) {
        Event newEvent = new Event.Builder().name(nameField.getText())
                .location(locationField.getText()).
                isOnline((Boolean) onlineStatusDropdown.getSelectedItem()).
                startDay((Days) startDayDropdown.getSelectedItem()).
                startTime(Integer.parseInt(startTimeField.getText())).
                endDay((Days) endDayDropdown.getSelectedItem()).
                endTime(Integer.parseInt(endTimeField.getText())).
                host(model.getSelectedUser()).
                invitees(userList.getSelectedValuesList()).build();
        features.createEvent(newEvent);
        closeParentFrame();
      } else {
        this.displayErrorMessage("Invalid inputs: fill in all areas.");
      }
    });

    modifyButton.addActionListener(e -> {
      if (isEditMode) {
        if (validateInputs()) {
          Event newEvent = new Event.Builder().name(nameField.getText())
                  .location(locationField.getText()).
                  isOnline((Boolean) onlineStatusDropdown.getSelectedItem()).
                  startDay((Days) startDayDropdown.getSelectedItem()).
                  startTime(Integer.parseInt(startTimeField.getText())).
                  endDay((Days) endDayDropdown.getSelectedItem()).
                  endTime(Integer.parseInt(endTimeField.getText())).
                  host(model.getSelectedUser()).
                  invitees(userList.getSelectedValuesList()).build();

          features.modifyEvent(selectedEvent, newEvent);

          clearForm();
          closeParentFrame();
        }
      } else {
        this.displayErrorMessage("Must select an event on main frame to modify.");
      }
    });

    removeButton.addActionListener(e -> {
      if (Objects.nonNull(selectedEvent)) {
        features.removeEvent(selectedEvent);
        closeParentFrame();
      } else {
        this.displayErrorMessage("Must select an event on main frame to remove.");
      }
    });
  }

  /**
   * Private helper which checks if there are valid inputs for each JFrame element.
   *
   * @return true if the inputs are valid
   */
  private boolean validateInputs() {
    return !nameField.getText().trim().isEmpty() &&
            startDayDropdown.getSelectedItem() != null &&
            !startTimeField.getText().trim().isEmpty() &&
            endDayDropdown.getSelectedItem() != null &&
            !endTimeField.getText().trim().isEmpty() &&
            onlineStatusDropdown.getSelectedItem() != null &&
            (!onlineStatusDropdown.getSelectedItem().equals(true) ||
                    !locationField.getText().trim().isEmpty());
  }

  /**
   * Private helper which loads the Panel with the info of the event.
   */
  private void loadEventForModification(Event event) {
    this.nameField.setText(event.getName());
    this.startDayDropdown.setSelectedItem(event.getStartDay());
    this.startTimeField.setText(String.valueOf(event.getStartTime()));
    this.endDayDropdown.setSelectedItem(event.getEndDay());
    this.endTimeField.setText(String.valueOf(event.getEndTime()));
    this.onlineStatusDropdown.setSelectedItem(event.getIsOnline());
    this.locationField.setText(event.getLocation());


    Set<Integer> indices = new HashSet<>();
    int hostIndex = this.model.getAllUsers().indexOf(event.getHost());
    if (hostIndex != -1) {
      indices.add(hostIndex);
    }

    for (User invitee : event.getInvitees()) {
      int index = this.model.getAllUsers().indexOf(invitee);
      if (index != -1) {
        indices.add(index);
      }
    }

    int[] selectedIndices = indices.stream().mapToInt(i -> i).toArray();
    this.userList.setSelectedIndices(selectedIndices);
    this.isEditMode = true;
    this.selectedEvent = event;
  }

  @Override
  public void clearForm() {
    this.nameField.setText("");
    this.startTimeField.setText("");
    this.endTimeField.setText("");
    this.locationField.setText("");
    this.startDayDropdown.setSelectedIndex(-1);
    this.endDayDropdown.setSelectedIndex(-1);
    this.onlineStatusDropdown.setSelectedIndex(-1);
    this.isEditMode = false;
  }

  /**
   * Private helper which gets the reference to the parent frame and closes it so once a removal
   * or modification happens, the event frame closes.
   */
  private void closeParentFrame() {
    Frame frame = (Frame) SwingUtilities.getWindowAncestor(this);
    if (frame != null) {
      frame.dispose();
    }
  }
}
