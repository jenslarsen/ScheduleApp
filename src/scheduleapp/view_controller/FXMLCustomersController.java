/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TextField textFieldZip;

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
        // load customers from database
        try {
            customers = Datasource.getCustomersWithAddresses();
            customerList.addAll(customers);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Something went wrong retrieving customers! " + e.getMessage());
        }
        System.out.println("Customers retrieved: " + customers);

        Datasource.close();

        // display in table
        tableColName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tableColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableColPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableViewCustomers.setItems(customerList);
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
        textFieldZip.setText(selectedCustomer.getPostalCode());
    }

    @FXML
    void addButtonClicked(ActionEvent event) {
    }

    @FXML
    void searchButtonClicked(ActionEvent event) {
    }

    @FXML
    void saveButtonClicked(ActionEvent event) {
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) {
    }

    @FXML
    void customersButtonClicked(ActionEvent event) {
        // already on the customers screen - don't do anything!
        // maybe make this a radio button or tab instead to be more clear??
    }

}
