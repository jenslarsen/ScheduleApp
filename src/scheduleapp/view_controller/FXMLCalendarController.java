/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dasuperman
 */
public class FXMLCalendarController implements Initializable {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonEdit;

    @FXML
    private TableView<?> tableViewCustomers;

    @FXML
    private TableColumn<?, ?> tableColName;

    @FXML
    private TableColumn<?, ?> tableColAddress;

    @FXML
    private TableColumn<?, ?> tableColPhone;

    @FXML
    private TableColumn<?, ?> tableColPhone1;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonCalendar;

    @FXML
    private Button buttonCustomers;

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
        // already on the customers screen - don't do anything!
        // maybe make this a radio button or tab instead to be more clear??    }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
