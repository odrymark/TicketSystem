package dk.easv.ticketsystem;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class AdminController
{
    private HBox hBox;
    private FlowPane eventsTickets;
    private VBox rightBoxHome;
    private VBox currP2Box;
    private Label currentP;
    private Image eventsImg;
    private Image eventsNotSelImg;
    private ImageView eventsIcon;

    public HBox createAdminP()
    {
        HBox root = new HBox(10);
        root.setPrefWidth(790);
        root.setPrefHeight(400);

        VBox leftBox = new VBox(10);
        leftBox.setId("leftBox");
        leftBox.setAlignment(Pos.TOP_LEFT);

        Label title = new Label("EASV TICKET");
        title.setId("title");

        eventsImg = new Image(getClass().getResourceAsStream("/dk/easv/ticketsystem/events.png"));
        eventsNotSelImg = new Image(getClass().getResourceAsStream("/dk/easv/ticketsystem/eventsNotSel.png"));
        eventsIcon = new ImageView(eventsNotSelImg);
        eventsIcon.setFitWidth(20);
        eventsIcon.setFitHeight(15);
        eventsIcon.setPreserveRatio(true);
        Button eventsButton = new Button("Events", eventsIcon);
        eventsButton.setId("sideBtnNotSelected");
        eventsButton.setPadding(new Insets(0, 0, 0, 11));
        eventsButton.setGraphicTextGap(10);

        Image userImg = new Image(getClass().getResourceAsStream("/dk/easv/ticketsystem/user.png"));
        ImageView userIcon = new ImageView(userImg);
        userIcon.setFitWidth(20);
        userIcon.setFitHeight(18);
        userIcon.setPreserveRatio(true);
        Button usersButton = new Button("Users", userIcon);
        usersButton.setId("sideBtnSelected");
        usersButton.setPadding(new Insets(0, 0, 0, 12));
        usersButton.setGraphicTextGap(11);

        Separator vSeparator = new Separator(Orientation.VERTICAL);
        vSeparator.setId("vSeparator");

        rightBoxHome = new VBox(10);
        rightBoxHome.setId("rightBox");
        rightBoxHome.setAlignment(Pos.TOP_RIGHT);

        ChoiceBox<String> user = new ChoiceBox<>();
        user.getItems().add("Alex Robert");
        user.getSelectionModel().selectFirst();
        user.setId("user");

        HBox currentPBox = new HBox(10);
        currentPBox.setPadding(new Insets(0, 0, 0, 20));
        currP2Box = new VBox(10);
        currentP = new Label("Users");
        currentP.setId("currentP");
        TextField search = new TextField();
        search.setId("search");
        search.setPromptText("Search");
        ImageView searchIcon = new ImageView(new Image(getClass().getResourceAsStream("/dk/easv/ticketsystem/search.png")));
        searchIcon.setId("searchIcon");
        searchIcon.setFitWidth(22);
        searchIcon.setFitHeight(22);
        Button btn = new Button("New User");
        btn.setId("newUser");
        btn.setOnAction(_ -> addWindow(true));

        HBox searchBox = new HBox(2);
        searchBox.setId("searchBox");
        searchBox.getChildren().addAll(searchIcon, search);
        hBox = new HBox(searchBox, btn);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        currP2Box.getChildren().addAll(currentP, hBox);
        currentPBox.getChildren().add(currP2Box);

        eventsTickets = new FlowPane();
        eventsTickets.setId("eveTick");
        eventsTickets.setHgap(10);
        eventsTickets.setVgap(15);
        eventsTickets.setPrefWrapLength(600);
        for (int i = 1; i <= 3; i++) {
            eventsTickets.getChildren().add(createUserCard("User " + i, "example@gmail.com", "User"));
        }

        leftBox.getChildren().addAll(title, eventsButton, usersButton);
        rightBoxHome.getChildren().addAll(user, currentPBox, eventsTickets);
        root.getChildren().addAll(leftBox, vSeparator, rightBoxHome);
        root.getStylesheets().add(getClass().getResource("/dk/easv/ticketsystem/homeStyle.css").toExternalForm());
        return root;
    }


    private VBox createUserCard(String name, String email, String type) {
        VBox row = new VBox(-10);
        row.setId("userCard");
        row.setAlignment(Pos.CENTER_LEFT);

        VBox userInfo = new VBox(5);

        Label nameLabel = new Label(name);
        nameLabel.setId("username");

        Label emailLabel = new Label("Email: " + email);
        emailLabel.setId("infoLabel");

        Label typeLabel = new Label("Type: " + type);
        typeLabel.setId("infoLabel");

        userInfo.getChildren().addAll(nameLabel, emailLabel, typeLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Hyperlink editLink = new Hyperlink("Edit");
        editLink.setId("link");

        Hyperlink deleteLink = new Hyperlink("Delete");
        deleteLink.setId("link");

        HBox actions = new HBox(10, editLink, deleteLink);
        actions.setAlignment(Pos.CENTER_RIGHT);

        row.getChildren().addAll(userInfo, spacer, actions);

        return row;
    }

    private void addWindow(boolean isAddWindow)
    {
        if(isAddWindow)
        {
            currP2Box.getChildren().remove(1);
            rightBoxHome.getChildren().remove(2);
            rightBoxHome.getChildren().add(newUser());
            currentP.setText("Create User");
        }
        else
        {
            currP2Box.getChildren().add(hBox);
            rightBoxHome.getChildren().remove(2);
            rightBoxHome.getChildren().add(eventsTickets);
            currentP.setText("Users");
        }
    }

    private VBox newUser()
    {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));

        HBox inputRow = new HBox(15);
        inputRow.setAlignment(Pos.CENTER_LEFT);

        TextField titleField = new TextField();
        titleField.setPromptText("Type Name");
        titleField.setId("inputField");
        TextField locationField = new TextField();
        locationField.setPromptText("Type Email");
        locationField.setId("inputField");
        TextField dateTimeField = new TextField();
        dateTimeField.setPromptText("Select Type");
        dateTimeField.setId("inputField");

        VBox titleBox = labeledInputs("Name", titleField);
        VBox locationBox = labeledInputs("Email", locationField);
        VBox dateTimeBox = labeledInputs("Type", dateTimeField);

        inputRow.getChildren().addAll(titleBox, locationBox, dateTimeBox);

        Button submitButton = new Button("Submit");
        submitButton.setId("addNewEvent");
        submitButton.setOnAction(_ -> {});

        Button cancelButton = new Button("Cancel");
        cancelButton.setId("addNewEvent");
        cancelButton.setOnAction(_ -> addWindow(false));

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        HBox buttonBox = new HBox(cancelButton, region, submitButton);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);

        root.getChildren().addAll(inputRow, buttonBox);
        return root;
    }

    private VBox labeledInputs(String label, Control inputField) {
        Label inputLabel = new Label(label);
        return new VBox(5, inputLabel, inputField);
    }
}
