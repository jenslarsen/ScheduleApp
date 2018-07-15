/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import scheduleapp.model.AppointmentWithContact;
import scheduleapp.model.Datasource;
import scheduleapp.model.User;

/**
 * FXML Controller class
 *
 * @author Jens Larsen
 */
public class FXMLReportsController {

    @FXML
    private Button buttonCalendar;

    @FXML
    private Button buttonCustomers;

    @FXML
    private Button buttonReports;

    @FXML
    private Button loginlogButton;

    @FXML
    private ScrollPane scrollPaneReport;

    @FXML
    private Text textReport;

    @FXML
    void openLoginLog(ActionEvent event) {
        Desktop dt = Desktop.getDesktop();
        try {
            dt.open(new File("logins.log"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error opening logins.log");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
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
    private void calendarButtonClicked(ActionEvent event) {
        Parent main;
        try {
            main = FXMLLoader.load(getClass().getResource("FXMLCalendar.fxml"));
            Scene scene = new Scene(main);
            Stage stage = (Stage) buttonCustomers.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error switching screens");
            alert.setContentText("Unable to switch to calendar screen!");
            alert.showAndWait();
        }
    }

    @FXML
    private void reportsButtonClicked(ActionEvent event) {
        // don't do anything - already on the reports screen
    }

    @FXML
    private void displaySchedule() throws ClassNotFoundException, SQLException {
        List<User> users = Datasource.getUsers();

        StringBuilder report = new StringBuilder();

        for (User user : users) {
            report.append("Upcoming appointments for ")
                    .append(user.getUserName())
                    .append("\n\n");

            List<AppointmentWithContact> appointments
                    = Datasource.getMonthApptsWithContacts(user);

            // lambda for clarity and simper code
            appointments.forEach((appointment) -> {
                report.append("\t")
                        .append(appointment.getStart())
                        .append(" to ")
                        .append(appointment.getEnd())
                        .append(" ")
                        .append(appointment.getTitle()).append("\n");
            });
            report.append("\n");
        }

        textReport.setText(report.toString());
    }

    @FXML
    private void initialize() {
        textReport.setText("Click a report button below to display it in this window");
    }
}
