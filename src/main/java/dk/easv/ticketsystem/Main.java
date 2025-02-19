package dk.easv.ticketsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        LoginController controller = new LoginController();
        VBox root = controller.createLogin();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Ticket System");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}