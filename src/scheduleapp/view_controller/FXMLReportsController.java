/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
    void openLoginLog(ActionEvent event) {
        Desktop dt = Desktop.getDesktop();
        try {
            dt.open(new File("logins.log"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error loading logins.log");
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

    }
}
