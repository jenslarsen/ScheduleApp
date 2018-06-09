/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.sql.SQLException;
import java.util.Locale;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scheduleapp.model.Datasource;

/**
 * FXML Login Controller class
 *
 * @author Jens Larsen
 */
public class FXMLLoginController extends Application {

    Stage stage;

    // Variables to store text for the login screen
    // Will be set depending on the local to en or fr (default to en)
    private String loginText;
    private String errorTitle;
    private String unableToLogin;
    private String enterUserPass;
    private String successText;
    private String loginSuccess;
    private String incorrectUserPass;
    private String passwordText;
    private String usernameText;
    private String signinText;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField loginUserName;

    @FXML
    private Label loginSignInText;

    @FXML
    private ChoiceBox<String> loginLocation;

    String language = "en"; // english by default

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        FXMLLoader mainController = new FXMLLoader();

        mainController.setLocation(getClass().getResource("FXMLLogin.fxml"));

        Parent root = mainController.load();

        stage.setScene(new Scene(root));

        stage.setTitle(loginText);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {

        // uncomment this line to test French locale
        //Locale.setDefault(new Locale("fr", "FR"));
        launch(args);
    }

    @FXML
    public void initialize() {

        // detect locale
        Locale locale = Locale.getDefault();
        language = locale.getLanguage();

        // Update text based on locale
        if (language.equals("fr")) {
            loginText = "S'identifier";
            errorTitle = "Erreur";
            unableToLogin = "Connection impossible";
            enterUserPass = "Vous devez entrer un nom d'utilisateur et un mot de passe";
            successText = "Succès";
            loginSuccess = "Vous vous êtes connecté avec succès à ";
            incorrectUserPass = "L'identifiant ou le mot de passe est incorrect";
            passwordText = "mot de passe";
            usernameText = "nom d'utilisateur";
            signinText = "Se connecter";
        } else {
            loginText = "Login";
            errorTitle = "Error";
            unableToLogin = "Unable to login";
            enterUserPass = "You must enter a username and password";
            successText = "Success";
            loginSuccess = "You've successfully logged in to ";
            incorrectUserPass = "Username or password is incorrect";
            passwordText = "password";
            usernameText = "username";
            signinText = "Sign in";
        }

        // update on screen controls
        loginButton.setText(loginText);
        loginPassword.setPromptText(passwordText);
        loginUserName.setPromptText(usernameText);
        loginSignInText.setText(signinText);

        // add locations
        loginLocation.getItems().addAll("Phoenix", "New York", "London");
        loginLocation.setValue("New York");
    }

    @FXML
    void loginButtonClick(ActionEvent event) throws ClassNotFoundException, SQLException {
        String userName = loginUserName.getText();
        String password = loginPassword.getText();

        if (userName.equals("") || password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errorTitle);
            alert.setHeaderText(unableToLogin);
            alert.setContentText(enterUserPass);
            alert.showAndWait();
            return;
        }

        // get the location
        String location = loginLocation.getValue();

        boolean loginSuccessful = Datasource.checkLogin(userName, password);

        if (loginSuccessful) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(successText);
            alert.setHeaderText(loginText);
            alert.setContentText(loginSuccess
                    + location + " " + language + "!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errorTitle);
            alert.setHeaderText(unableToLogin);
            alert.setContentText(incorrectUserPass);
            alert.showAndWait();
        }
    }
}
