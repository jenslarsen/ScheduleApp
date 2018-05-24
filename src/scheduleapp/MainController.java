package scheduleapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jens Larsen
 */
public class MainController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = primaryStage;
        
        FXMLLoader mainController = new FXMLLoader();
        
        mainController.setLocation(getClass().getResource("view_controller/FXMLLogin.fxml"));
        
        Parent root = mainController.load();
        
        stage.setScene(new Scene(root));
        
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
