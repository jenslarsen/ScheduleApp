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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import scheduleapp.model.Appointment;
import scheduleapp.model.Datasource;

/**
 * FXML Controller class
 *
 * @author Jens Larsen
 */
public class FXMLCalendarController {

    private List<Appointment> appointments;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonEdit;

    @FXML
    private TableView<Appointment> tableViewCalendar;

    @FXML
    private TableColumn<Appointment, String> tableColTitle;

    @FXML
    private TableColumn<Appointment, String> tableColLocation;

    @FXML
    private TableColumn<Appointment, String> tableColContact;

    @FXML
    private TableColumn<Appointment, Timestamp> tableColDate;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonCalendar;

    @FXML
    private Button buttonCustomers;

    @FXML
    private ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

    @FXML
    private void addButtonClicked() {
    }

    @FXML
    private void editButtonClicked() {
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
    private void calendarButtonClicked() {
        // already on the calendar screen - don't do anything!
        // maybe make this a radio button or tab instead to be more clear??    }
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() throws SQLException {
        appointments = new ArrayList<>();
        appointmentList = FXCollections.observableArrayList();

        try {
            appointments = Datasource.getAppointments();
        } catch (ClassNotFoundException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Exeception encountered");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        appointmentList.addAll(appointments);

        Datasource.close();

        // display in table
        tableColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableColLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tableColContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tableColDate.setCellValueFactory(new PropertyValueFactory<>("start"));

        tableViewCalendar.setItems(appointmentList);
    }

}
