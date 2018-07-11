/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
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

/**
 * FXML Controller class
 *
 * @author Jens Larsen
 */
public class FXMLAddAppointmentController {

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
        alert.setHeaderText("Cancel Adding Appointment");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void saveButtonClicked(ActionEvent event) throws ParseException, ClassNotFoundException, SQLException {
        Appointment newAppointment = new Appointment();

        newAppointment.setTitle(textFieldTitle.getText());
        newAppointment.setDescription(textFieldDescription.getText());
        newAppointment.setLocation(textFieldLocation.getText());
        newAppointment.setUrl(textFieldUrl.getText());

        if (newAppointment.getTitle().equals("")) {
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

            newAppointment.setStart(LocalDateTime.parse(startString));

            String endString = datePickerEnd.getValue() + "T"
                    + comboEndHour.getValue() + ":" + comboEndMinute.getValue() + ":00";

            newAppointment.setEnd(LocalDateTime.parse(endString));
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Time entry error");
            alert.setHeaderText("You must enter a start and end dates and times");
            alert.setContentText("Some fields are empty");
            alert.showAndWait();
            return;
        }

        newAppointment.setContact(Datasource.loggedInUser);
        newAppointment.setCreateDate(LocalDateTime.now());
        newAppointment.setCreatedBy(Datasource.loggedInUser);
        newAppointment.setLastUpdate(newAppointment.getCreateDate());
        newAppointment.setLastUpdateBy(Datasource.loggedInUser);

        int customerIndex = comboCustomer.getSelectionModel().getSelectedIndex();

        try {
            newAppointment.setCustomerID(customers.get(customerIndex).getCustomerID());
        } catch (ArrayIndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer seleciton error");
            alert.setHeaderText("You must select a customer");
            alert.setContentText("Some fields are empty");
            alert.showAndWait();
            return;
        }
        Datasource.addAppointment(newAppointment);

        Stage stage = (Stage) buttonSave.getScene().getWindow();
        stage.close();
    }

    /**
     * Loads customers into the drop down list and populates the time entry
     * fields when the window is loaded
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void initialize() throws ClassNotFoundException, SQLException {

        // load customers into dropdown
        customers = Datasource.getCustomersWithAddresses();
        List<String> listOfCustomers = new ArrayList();
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
    }
}
