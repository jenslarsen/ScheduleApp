/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Login Controller class
 *
 * @author Jens Larsen
 */
public class FXMLLoginController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = primaryStage;

        FXMLLoader mainController = new FXMLLoader();

        mainController.setLocation(getClass().getResource("FXMLLogin.fxml"));

        Parent root = mainController.load();

        stage.setScene(new Scene(root));

        stage.setTitle("Login");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField loginUserName;

    @FXML
    private Label loginSignInText;

    @FXML
    private ChoiceBox<?> loginLocation;

    @FXML
    private RadioButton loginRadioEnglish;

    @FXML
    private RadioButton loginRadioFrench;

    @FXML
    public void initialize() {

        // do init here?
    }

    @FXML
    void loginButtonClick(ActionEvent event) throws ClassNotFoundException {
        String userName = loginUserName.getText();
        String password = loginPassword.getText();

        if (userName.equals("") || password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to login");
            alert.setContentText("You must enter a username and password");
            alert.showAndWait();
            return;
        }

        connectToDatabase();

        if (userName.equals("test") && password.equals("test")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Login");
            alert.setContentText("You've successfully loggged in!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to login");
            alert.setContentText("Username or password is incorrect");
            alert.showAndWait();
        }
    }

    void connectToDatabase() throws ClassNotFoundException {
        Connection connection = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String database = "U04H9n";
        String dbURL = "jdbc:mysql://52.206.157.109/" + database;
        String dbUser = "U04H9n";
        String dbPassword = "53688238693";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            System.out.println("Connected to database : " + database);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

    }
}
