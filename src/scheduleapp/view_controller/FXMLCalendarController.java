/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
