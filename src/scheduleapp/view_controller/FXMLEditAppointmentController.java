/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
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
    private ComboBox<?> comboCustomer;

    @FXML
    private TextField textFieldUrl;

    @FXML
    private ComboBox<?> comboStartHour;

    @FXML
    private ComboBox<?> comboStartMinute;

    @FXML
    private ComboBox<?> comboEndHour;

    @FXML
    private ComboBox<?> comboEndMinute;

    @FXML
    private TextField textFieldTitle;

    @FXML
    void cancelButtonClicked(ActionEvent event) {

    }

    @FXML
    void saveButtonClicked(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
    }
}
