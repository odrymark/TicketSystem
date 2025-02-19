package dk.easv.ticketsystem;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginController
{
    private Boolean isLogin = false;
    private Label title;
    private TextField nameField;
    private PasswordField passwordField;
    private TextField emailField;
    private Button confirmButton;
    private Button switchButton;

    public VBox createLogin()
    {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPrefWidth(500);
        root.setPrefHeight(400);

        title = new Label("Sign Up");
        title.setId("title");

        nameField = new TextField();
        nameField.setOnAction(_ -> login());
        nameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        emailField = new TextField();
        emailField.setPromptText("Email");

        confirmButton = new Button("Sign Up");
        confirmButton.setId("confirm");
        confirmButton.setOnAction(_ -> login());

        switchButton = new Button("To Login");
        switchButton.setId("switch");
        switchButton.setOnAction(_ -> switchLogin());

        root.getChildren().addAll(title, nameField, passwordField, emailField, confirmButton, switchButton);
        root.getStylesheets().add(getClass().getResource("/dk/easv/ticketsystem/loginStyle.css").toExternalForm());

        return root;
    }

    private void switchLogin()
    {
        isLogin = !isLogin;

        if(isLogin)
        {
            confirmButton.setText("Log In");
            title.setText("Log In");
            switchButton.setText("To Signup");
            emailField.setDisable(true);
            emailField.setVisible(false);
            nameField.setPromptText("Username/Email");
        }
        else
        {
            confirmButton.setText("Sign Up");
            title.setText("Sign Up");
            switchButton.setText("To Login");
            emailField.setDisable(false);
            emailField.setVisible(true);
            nameField.setPromptText("Username");
        }
    }

    private void login()
    {
        if(nameField.getText().equals("1"))
        {
            HomeController homeC = new HomeController();
            Stage stage = (Stage) title.getScene().getWindow();
            Scene scene = new Scene(homeC.createHomeP());
            stage.setScene(scene);
            stage.show();
        }
        else if(nameField.getText().equals("2"))
        {
            CoordController homeC = new CoordController();
            Stage stage = (Stage) title.getScene().getWindow();
            Scene scene = new Scene(homeC.createCoord());
            stage.setScene(scene);
            stage.show();
        }
    }
}