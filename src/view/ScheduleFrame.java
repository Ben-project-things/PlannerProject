package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.util.List;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;

import controller.Features;
import model.ReadOnlyPlannerModel;
import schedule.User;

/**
 * View which represents the new ScheduleFrame and its appropriate features.
 */
public class ScheduleFrame extends JFrame {
  private JTextField eventNameField;
  private JTextField durationField;
  private JTextField locationField;
  private JComboBox<Boolean> onlineStatusDropdown;
  private JList<User> userList;
  private Features features;
  private final ReadOnlyPlannerModel model;

  /**
   * Constructor for the new Schedule frame which takes in the model and features and initializes
   * the UI.
   *
   * @param model    is the model being used
   * @param features is the features to deal with the actions of the scheduleButton
   */
  public ScheduleFrame(ReadOnlyPlannerModel model, Features features) {
    this.model = Objects.requireNonNull(model);
    this.features = Objects.requireNonNull(features);
    initializeUI();
  }

  /**
   * Private helper which initializes the setup of the new scheduleFrame.
   */
  private void initializeUI() {
    setTitle("Schedule Event");
    setSize(400, 300);
    setLayout(new BorderLayout());

    JPanel northPanel = new JPanel(new GridLayout(0, 2));
    eventNameField = new JTextField(10);
    durationField = new JTextField(5);
    locationField = new JTextField(10);
    onlineStatusDropdown = new JComboBox<>(new Boolean[]{Boolean.TRUE, Boolean.FALSE});

    northPanel.add(new JLabel("Event Name:"));
    northPanel.add(eventNameField);
    northPanel.add(new JLabel("Duration (minutes):"));
    northPanel.add(durationField);
    northPanel.add(new JLabel("Location:"));
    northPanel.add(locationField);
    northPanel.add(new JLabel("Online:"));
    northPanel.add(onlineStatusDropdown);

    add(northPanel, BorderLayout.NORTH);

    DefaultListModel<User> listModel = new DefaultListModel<>();
    model.getAllUsers().forEach(listModel::addElement);
    userList = new JList<>(listModel);
    add(new JScrollPane(userList), BorderLayout.CENTER);

    JButton scheduleButton = new JButton("Schedule Event");
    add(scheduleButton, BorderLayout.SOUTH);

    scheduleButton.addActionListener(e -> {
      try {
        int duration = Integer.parseInt(durationField.getText());
        boolean isOnline = (Boolean) onlineStatusDropdown.getSelectedItem();
        List<User> selectedUsers = userList.getSelectedValuesList();
        features.scheduleAutomaticEvent(eventNameField.getText(), duration, locationField.getText(),
                isOnline, selectedUsers, model.getSelectedUser());
        dispose();
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this,
                "Invalid number format for duration.", "Error",
                JOptionPane.ERROR_MESSAGE);
      }
    });

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

}
