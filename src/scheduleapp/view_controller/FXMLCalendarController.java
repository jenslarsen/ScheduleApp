/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scheduleapp.model.AppointmentWithContact;
import scheduleapp.model.Datasource;

/**
 * FXML Controller class
 *
 * @author Jens Larsen
 */
public class FXMLCalendarController {

    private List<AppointmentWithContact> appointments;

    @FXML
    private RadioButton radioMonth;

    @FXML
    private RadioButton radioWeek;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonEdit;

    @FXML
    private TableView<AppointmentWithContact> tableViewCalendar;

    @FXML
    private TableColumn<AppointmentWithContact, String> tableColTitle;

    @FXML
    private TableColumn<AppointmentWithContact, String> tableColLocation;

    @FXML
    private TableColumn<AppointmentWithContact, String> tableColCustomer;

    @FXML
    private TableColumn<AppointmentWithContact, Timestamp> tableColDate;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonCalendar;

    @FXML
    private Button buttonCustomers;

    @FXML
    private final ToggleGroup calType = new ToggleGroup();

    @FXML
    private ObservableList<AppointmentWithContact> appointmentList
            = FXCollections.observableArrayList();

    @FXML
    private void addButtonClicked() throws IOException {
        Stage stage = new Stage();

        FXMLLoader addAppointmentLoader = new FXMLLoader();
        addAppointmentLoader.setLocation(getClass().getResource("FXMLAddAppointment.fxml"));

        Parent root = addAppointmentLoader.load();

        stage.setScene(new Scene(root));

        stage.setTitle("Add Appointment");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);

        stage.showAndWait();

        // reload appointments after the change
        loadAppointmentsWithContacts();
    }

    @FXML
    private void editButtonClicked() throws IOException, SQLException {

        int index = tableViewCalendar.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            return;
        }

        Datasource.appointmentBeingEdited = appointments.get(index);

        Stage stage = new Stage();

        FXMLLoader editAppointmentLoader = new FXMLLoader();
        editAppointmentLoader.setLocation(getClass().getResource("FXMLEditAppointment.fxml"));

        Parent root = editAppointmentLoader.load();

        stage.setScene(new Scene(root));

        stage.setTitle("Edit Customer");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);

        stage.showAndWait();

        // reload appointments after the change
        loadAppointmentsWithContacts();
    }

    @FXML
    private void deleteButtonClicked() {
    }

    @FXML
    private void customersButtonClicked() {
        Parent main;
        try {
            main = FXMLLoader.load(getClass().getResource("FXMLCustomers.fxml"));
            Scene scene = new Scene(main);
            Stage stage = (Stage) buttonCustomers.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error switching screens");
            alert.setContentText("Unable to switch to customer screen!");
            alert.showAndWait();
        }
    }

    @FXML
    private void reportsButtonClicked(ActionEvent event) {
        Parent main;
        try {
            main = FXMLLoader.load(getClass().getResource("FXMLReports.fxml"));
            Scene scene = new Scene(main);
            Stage stage = (Stage) buttonCustomers.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error switching screens");
            alert.setContentText("Unable to switch to report screen!");
            alert.showAndWait();
        }
    }

    @FXML
    private void calendarButtonClicked() {
        // already on the calendar screen - don't do anything!
        // maybe make this a radio button or tab instead to be more clear??    }
    }

    /**
     * Initializes the calendar view.
     *
     * @throws java.sql.SQLException
     */
    @FXML
    public void initialize() throws SQLException {

        radioMonth.setToggleGroup(calType);
        radioWeek.setToggleGroup(calType);
        calType.selectToggle(radioMonth);

        loadAppointmentsWithContacts();

        // add a listener for when the user selects another calendar view
        calType.selectedToggleProperty().addListener((
                ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) -> {
            if (calType.getSelectedToggle() != null) {
                loadAppointmentsWithContacts();
            }
        });
    }

    /**
     * Loads appointment and associated contacts from the database
     *
     * Detects if upcoming month or week is show before loading data
     *
     * @return true if successful
     */
    private boolean loadAppointmentsWithContacts() {
        appointments = new ArrayList<>();
        appointmentList = FXCollections.observableArrayList();

        try {
            if (calType.getSelectedToggle().equals(radioWeek)) {
                appointments = Datasource.getWeekApptsWithContacts();
            } else if (calType.getSelectedToggle().equals(radioMonth)) {
                appointments = Datasource.getMonthApptsWithContacts();
            } else {
                System.err.println("Bad things happened: no radio button selected?");
                return false;
            }

            appointmentList.addAll(appointments);

            Datasource.close();

        } catch (ClassNotFoundException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Exeception encountered");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return false;
        }

        // display in table
        tableColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableColLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tableColCustomer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tableColDate.setCellValueFactory(new PropertyValueFactory<>("start"));

        tableViewCalendar.setItems(appointmentList);

        return true;
    }

}
