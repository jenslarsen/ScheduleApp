/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scheduleapp.model.Datasource;
import scheduleapp.model.LoginException;

/**
 * FXML Login Controller class Loads at application to handle user login
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
    private String switchError;
    private String incorrectUserPass;
    private String passwordText;
    private String usernameText;
    private String signinText;
    private String unableToSwitch;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField loginUserName;

    @FXML
    private Label loginSignInText;

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

    /**
     * Main method - commented line here to test locale change to FR
     *
     * @param args
     */
    public static void main(String[] args) {

        // uncomment this line to test French locale
        //Locale.setDefault(new Locale("fr", "FR"));
        launch(args);
    }

    /**
     * Load when window is loaded
     *
     * Detects system language from the locale and sets text strings accordingly
     */
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
            incorrectUserPass = "Connexion incorrecte ou base de données indisponible";
            passwordText = "mot de passe";
            usernameText = "nom d'utilisateur";
            signinText = "Se connecter";
            switchError = "Ce n'est pas bon...";
            unableToSwitch = "Erreur lors du passage à l'écran du calendar";
        } else { // default to English
            loginText = "Login";
            errorTitle = "Error";
            unableToLogin = "Unable to login";
            enterUserPass = "You must enter a username and password";
            incorrectUserPass = "Login incorrect or database unavailable";
            passwordText = "password";
            usernameText = "username";
            signinText = "Sign in";
            switchError = "This isn't good...";
            unableToSwitch = "Error switching to calendar screen!";
        }

        // update on screen controls
        loginButton.setText(loginText);
        loginPassword.setPromptText(passwordText);
        loginUserName.setPromptText(usernameText);
        loginSignInText.setText(signinText);

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

        boolean loginSuccessful = false;

        try {
            loginSuccessful = Datasource.checkLogin(userName, password);
        } catch (LoginException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errorTitle);
            alert.setHeaderText(unableToLogin);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        if (loginSuccessful) {
            // write login to log file
            FileWriter logFile = null;
            try {
                logFile = new FileWriter("logins.log", true);
                logFile.append(LocalDateTime.now().toString() + " " + userName
                        + " logged in\n");
            } catch (IOException e) {
                System.out.println("Unable to open log file for writing!");
            } finally {
                try {
                    logFile.close();
                } catch (IOException ex) {
                    System.out.println("Unable to close log file!");
                }
            }
            switchToCalendarScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errorTitle);
            alert.setHeaderText(unableToLogin);
            alert.setContentText(incorrectUserPass);
            alert.showAndWait();
        }
    }

    private void switchToCalendarScreen() {
        Parent main;
        try {
            main = FXMLLoader.load(getClass().getResource("FXMLCalendar.fxml"));
            Scene scene = new Scene(main);
            stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(errorTitle);
            alert.setHeaderText(switchError);
            alert.setContentText(unableToSwitch);
            System.err.println(e.getMessage());
            alert.showAndWait();
        }
    }
}
