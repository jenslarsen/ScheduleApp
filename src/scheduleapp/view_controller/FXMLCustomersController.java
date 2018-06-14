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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import scheduleapp.model.Address;
import scheduleapp.model.Customer;
import scheduleapp.model.CustomerWithAddress;
import scheduleapp.model.Datasource;

/**
 * FXML Controller class
 *
 * @author Jens Larsen
 */
public class FXMLCustomersController {

    @FXML
    private Button buttonCalendar;

    @FXML
    private Button buttonCustomers;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private Button buttonSearch;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonEdit;

    @FXML
    private TableView<CustomerWithAddress> tableViewCustomers;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldAddress;

    @FXML
    private TextField textFieldAddress2;

    @FXML
    private TextField textFieldCity;

    @FXML
    private TextField textFieldState;

    @FXML
    private TextField textFieldPhone;

    @FXML
    private TextField textFieldPostalCode;

    @FXML
    private TextField textFieldCountry;

    @FXML
    private TableColumn<CustomerWithAddress, String> tableColName;

    @FXML
    private TableColumn<CustomerWithAddress, String> tableColAddress;

    @FXML
    private TableColumn<CustomerWithAddress, String> tableColPhone;

    @FXML
    private List<CustomerWithAddress> customers = new ArrayList<>();

    @FXML
    private final ObservableList<CustomerWithAddress> customerList = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {
        loadCustomersFromDatabase();
    }

    @FXML
    void editButtonClicked(ActionEvent event) {

        CustomerWithAddress selectedCustomer;

        int index = tableViewCustomers.getSelectionModel().getSelectedIndex();

        if (index > customerList.size() || index < 0) {
            return; // don't do anything if no customer is selected
        }

        selectedCustomer = customers.get(index);
        textFieldName.setText(selectedCustomer.getCustomerName());
        textFieldAddress.setText(selectedCustomer.getAddress());
        textFieldAddress2.setText(selectedCustomer.getAddress2());
        textFieldCity.setText(selectedCustomer.getCity());
        textFieldPhone.setText(selectedCustomer.getPhone());
        textFieldPostalCode.setText(selectedCustomer.getPostalCode());
        textFieldCountry.setText(selectedCustomer.getCountry());
    }

    @FXML
    void addButtonClicked(ActionEvent event) {

        Customer newCustomer = new Customer();
        Address newAddress = new Address();

        String name = textFieldName.getText();
        String address = textFieldAddress.getText();
        String address2 = textFieldAddress2.getText();
        String city = textFieldCity.getText();
        String state = textFieldState.getText();
        String phone = textFieldPhone.getText();
        String zip = textFieldPostalCode.getText();

        if (name.equals("")) {
            return; // don't do anything
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Add new customer?");
        alert.setContentText(name);

        Optional<ButtonType> result = alert.showAndWait();

    }

    @FXML
    void searchButtonClicked(ActionEvent event) {
    }

    @FXML
    void saveButtonClicked(ActionEvent event) {
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete customer");
        alert.setHeaderText("Are you sure you want to remove this customer?");
        alert.setContentText(textFieldName.getText());
        alert.showAndWait();
    }

    @FXML
    void customersButtonClicked(ActionEvent event) {
        // already on the customers screen - don't do anything!
        // maybe make this a radio button or tab instead to be more clear??
    }

    void loadCustomersFromDatabase() throws SQLException {
        try {
            customers = Datasource.getCustomersWithAddresses();
        } catch (ClassNotFoundException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Exeception encountered");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        customerList.addAll(customers);

        Datasource.close();

        // display in table
        tableColName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tableColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableColPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableViewCustomers.setItems(customerList);
    }
}
