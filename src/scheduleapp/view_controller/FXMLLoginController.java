/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduleapp.view_controller;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
 * @author dasuperman
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
    void loginButtonClick(ActionEvent event) {
        System.out.println("Login clicked!");
    }
}
