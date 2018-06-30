/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.sql.SQLException;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scheduleapp.model.AppointmentWithContact;
import scheduleapp.model.CustomerWithAddress;
import scheduleapp.model.Datasource;

/**
 * FXML Controller class
 *
 * @author jlarsen
 */
public class FXMLAddAppointmentController {

    @FXML
    private AnchorPane datePickerStart;

    @FXML
    private TextField textTitle;

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextField textFieldLocation;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private TextField textStartTime;

    @FXML
    private TextField textEndTime;

    @FXML
    private TextField textFieldUrl;

    @FXML
    private ComboBox<String> comboCustomer;

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
    void saveButtonClicked(ActionEvent event) {
        AppointmentWithContact newAppointment = new AppointmentWithContact();

        newAppointment.setTitle(textTitle.getText());
        newAppointment.setDescription(textFieldDescription.getText());
        newAppointment.setLocation(textFieldLocation.getText());
        newAppointment.setUrl(textFieldUrl.getText());
    }

    public void initialize() throws ClassNotFoundException, SQLException {
        customers = Datasource.getCustomersWithAddresses();

        List<String> listOfCustomers = new ArrayList();

        for (CustomerWithAddress customer : customers) {
            listOfCustomers.add(customer.getCustomerName());
        }

        customerList = FXCollections.observableArrayList(listOfCustomers);

        comboCustomer.setItems(customerList);
    }
}
