/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scheduleapp.model.Appointment;
import scheduleapp.model.CustomerWithAddress;
import scheduleapp.model.Datasource;
import scheduleapp.model.TimeEntryException;

/**
 * FXML Controller class for editing appointments
 *
 * @author Jens Larsen
 */
public class FXMLEditAppointmentController {

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextField textFieldLocation;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private ComboBox<String> comboCustomer;

    @FXML
    private TextField textFieldUrl;

    @FXML
    private ComboBox<String> comboStartHour;

    @FXML
    private ComboBox<String> comboStartMinute;

    @FXML
    private ComboBox<String> comboEndHour;

    @FXML
    private ComboBox<String> comboEndMinute;

    @FXML
    private TextField textFieldTitle;

    private List<CustomerWithAddress> customers;
    ObservableList<String> customerList
            = FXCollections.observableArrayList();

    @FXML
    void cancelButtonClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel Editing Appointment");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void saveButtonClicked(ActionEvent event) throws ParseException, ClassNotFoundException, SQLException {
        Appointment editAppointment = new Appointment();

        editAppointment.setAppointmentID(Datasource.appointmentBeingEdited.getAppointmentID());

        editAppointment.setTitle(textFieldTitle.getText());
        editAppointment.setDescription(textFieldDescription.getText());
        editAppointment.setLocation(textFieldLocation.getText());
        editAppointment.setUrl(textFieldUrl.getText());

        if (editAppointment.getTitle().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input error");
            alert.setHeaderText("You must enter a title");
            alert.setContentText("Some fields are empty");
            alert.showAndWait();
            return;
        }

        try {
            String startString = datePickerStart.getValue() + "T"
                    + comboStartHour.getValue() + ":" + comboStartMinute.getValue() + ":00";

            LocalDateTime ldtStart = LocalDateTime.parse(startString);
            editAppointment.setStart(ldtStart);

            String endString = datePickerEnd.getValue() + "T"
                    + comboEndHour.getValue() + ":" + comboEndMinute.getValue() + ":00";

            LocalDateTime ldtEnd = LocalDateTime.parse(endString);
            editAppointment.setEnd(ldtEnd);

            if (ldtStart.isAfter(ldtEnd)) {
                throw new TimeEntryException(
                        "Start date and time must be before end date and time");
            }

            if (ldtStart.toLocalTime().isBefore(Datasource.BUS_OPEN)
                    || ldtEnd.toLocalTime().isAfter(Datasource.BUS_CLOSE)) {
                throw new TimeEntryException(
                        "Appointments must be within business hours");
            }

        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Time entry error");
            alert.setHeaderText("You must enter a start and end dates and times");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        } catch (TimeEntryException | DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Time entry error");
            alert.setHeaderText("Problem with appointment times");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }

        editAppointment.setContact(Datasource.loggedInUser);
        editAppointment.setCreateDate(LocalDateTime.now());
        editAppointment.setCreatedBy(Datasource.loggedInUser);
        editAppointment.setLastUpdate(editAppointment.getCreateDate());
        editAppointment.setLastUpdateBy(Datasource.loggedInUser);

        int customerIndex = comboCustomer.getSelectionModel().getSelectedIndex();

        try {
            editAppointment.setCustomerID(customers.get(customerIndex).getCustomerID());
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer seleciton error");
            alert.setHeaderText("You must select a customer");
            alert.setContentText("Some fields are empty");
            alert.showAndWait();
            return;
        }

        Datasource.updateAppointment(editAppointment);

        Stage stage = (Stage) buttonSave.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the window on load
     *
     * Populates drop down menus for customer and time entry and loads
     * appointment data into the fields
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void initialize() throws ClassNotFoundException, SQLException {

        // load customers into dropdown
        customers = Datasource.getCustomersWithAddresses();
        List<String> listOfCustomers = new ArrayList();

        // lambda to populate the customer combobox
        customers.forEach((customer) -> {
            listOfCustomers.add(customer.getCustomerName());
        });
        customerList = FXCollections.observableArrayList(listOfCustomers);
        comboCustomer.setItems(customerList);

        // populate time dropdowns
        ObservableList<String> hours = FXCollections.observableArrayList(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"
        );

        ObservableList<String> mins = FXCollections.observableArrayList(
                "00", "15", "30", "45"
        );

        comboStartHour.setItems(hours);
        comboStartMinute.setItems(mins);
        comboEndHour.setItems(hours);
        comboEndMinute.setItems(mins);

        textFieldTitle.setText(Datasource.appointmentBeingEdited.getTitle());
        textFieldDescription.setText(Datasource.appointmentBeingEdited.getDescription());
        textFieldLocation.setText(Datasource.appointmentBeingEdited.getLocation());
        datePickerStart.setValue(
                Datasource.appointmentBeingEdited.getStart().toLocalDate());
        datePickerEnd.setValue(
                Datasource.appointmentBeingEdited.getEnd().toLocalDate());

        comboStartHour.getSelectionModel().select(Datasource.appointmentBeingEdited
                .getStart().getHour());
        comboEndHour.getSelectionModel().select(Datasource.appointmentBeingEdited
                .getEnd().getHour());

        int startMin = Datasource.appointmentBeingEdited.getStart().getMinute();
        if (startMin == 0) {
            comboStartMinute.getSelectionModel().select("00");
        } else {
            comboStartMinute.getSelectionModel().select(Integer.toString(startMin));
        }
        int endMin = Datasource.appointmentBeingEdited.getEnd().getMinute();
        if (endMin == 0) {
            comboEndMinute.getSelectionModel().select("00");
        } else {
            comboEndMinute.getSelectionModel().select(Integer.toString(endMin));
        }

        String customerName = Datasource.appointmentBeingEdited.getCustomerName();

        comboCustomer.getSelectionModel().select(customerList.indexOf(customerName));

        textFieldUrl.setText(Datasource.appointmentBeingEdited.getUrl());
    }
}
