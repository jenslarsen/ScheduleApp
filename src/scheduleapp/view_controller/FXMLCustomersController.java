/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private TableColumn<CustomerWithAddress, String> tableColName;

    @FXML
    private TableColumn<CustomerWithAddress, String> tableColAddress;

    @FXML
    private TableColumn<CustomerWithAddress, String> tableColPhone;

    @FXML
    private List<CustomerWithAddress> customers = new ArrayList<>();

    @FXML
    private ObservableList<CustomerWithAddress> customerList = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {
        loadCustomersFromDatabase();
    }

    @FXML
    void editButtonClicked(ActionEvent event) {
    }

    @FXML
    void addButtonClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader addCustomerLoader = new FXMLLoader();
        addCustomerLoader.setLocation(getClass().getResource("FXMLAddCustomer.fxml"));

        Parent root = addCustomerLoader.load();

        stage.setScene(new Scene(root));

        stage.setTitle("Add Customer");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setResizable(false);

        stage.showAndWait();
    }

    @FXML
    void searchButtonClicked(ActionEvent event) {
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete customer");
        alert.setHeaderText("Are you sure you want to remove this customer?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            int index = tableViewCustomers.getSelectionModel().getSelectedIndex();
            int customerid = customers.get(index).getCustomerID();
            boolean inactivateSuccessful = Datasource.inactivateCustomer(customerid);
            if (inactivateSuccessful) {
                // refresh customer list
                loadCustomersFromDatabase();
            }
        }
    }

    @FXML
    void customersButtonClicked(ActionEvent event
    ) {
        // already on the customers screen - don't do anything!
        // maybe make this a radio button or tab instead to be more clear??
    }

    void loadCustomersFromDatabase() throws SQLException {
        customers = new ArrayList<>();
        customerList = FXCollections.observableArrayList();;

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
