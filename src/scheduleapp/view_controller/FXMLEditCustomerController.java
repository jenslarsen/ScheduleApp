/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.sql.SQLException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scheduleapp.model.Address;
import scheduleapp.model.City;
import scheduleapp.model.Country;
import scheduleapp.model.Customer;
import scheduleapp.model.Datasource;

/**
 * FXML Controller class
 *
 * @author dasuperman
 */
public class FXMLEditCustomerController {

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldAddress;

    @FXML
    private TextField textFieldAddress2;

    @FXML
    private TextField textFieldCity;

    @FXML
    private TextField textFieldCountry;

    @FXML
    private TextField textFieldPhone;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    @FXML
    private TextField textFieldPostalCode;

    @FXML
    void cancelButtonClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel Editing Customer");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    void saveButtonClicked(ActionEvent event) throws ClassNotFoundException, SQLException {

        String name = textFieldName.getText();
        String address = textFieldAddress.getText();
        String address2 = textFieldAddress2.getText();
        String city = textFieldCity.getText();
        String phone = textFieldPhone.getText();
        String postalCode = textFieldPostalCode.getText();
        String country = textFieldCountry.getText();
        int addressId = Datasource.customerBeingEdited.getAddressId();
        int customerId = Datasource.customerBeingEdited.getCustomerID();

        if (name.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("You must enter a name");
            alert.setContentText(name);
            return; // don't add anything
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Save customer details");
        alert.setContentText(name);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK) {
            return;
        }

        int countryId = Datasource.countryExists(country);

        if (countryId < 1) {            /// country isn't in the dateabase
            System.out.println("Couldn't find " + country + " in the database");
            Country countryToAdd = new Country(country);
            countryId = Datasource.addCountry(countryToAdd);
            System.out.println("Added " + country + " with ID " + countryId);
        }

        int cityId = Datasource.cityExists(city, country);

        if (cityId < 1) {            /// city isn't in the dateabase
            System.out.println("Couldn't find " + city + " in the database");
            City cityToAdd = new City(city, countryId);
            cityId = Datasource.addCity(cityToAdd);
            System.out.println("Added " + city + " with ID " + cityId);
        }

        Address addressToUpdate = new Address(address, address2, cityId, postalCode, phone);
        addressToUpdate.setAddressId(addressId);

        boolean addressUpdateSuccessful = Datasource.updateAddress(addressToUpdate);

        if (addressUpdateSuccessful != true) {
            System.out.println("Updating address failed!");
            Stage stage = (Stage) buttonSave.getScene().getWindow();
            stage.close();
            return;
        }

        Customer customerToUpdate = new Customer(name, addressId);
        customerToUpdate.setCustomerID(customerId);

        boolean customerUpdateSuccessful = Datasource.updateCustomer(customerToUpdate);

        if (customerUpdateSuccessful != true) {
            System.out.println("Updating customer failed!");
            Stage stage = (Stage) buttonSave.getScene().getWindow();
            stage.close();
            return;
        }

        System.out.println("Updated " + name + " with ID " + customerId);

        Stage stage = (Stage) buttonSave.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        textFieldName.setText(Datasource.customerBeingEdited.getCustomerName());
        System.out.println(Datasource.customerBeingEdited.getCustomerName());
        textFieldAddress.setText(Datasource.customerBeingEdited.getAddress());
        textFieldAddress2.setText(Datasource.customerBeingEdited.getAddress2());
        textFieldCity.setText(Datasource.customerBeingEdited.getCity());
        textFieldCountry.setText(Datasource.customerBeingEdited.getCountry());
        textFieldPhone.setText(Datasource.customerBeingEdited.getPhone());
        textFieldPostalCode.setText(Datasource.customerBeingEdited.getPostalCode());
    }
}
