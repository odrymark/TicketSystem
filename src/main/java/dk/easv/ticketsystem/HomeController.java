package dk.easv.ticketsystem;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeController
{
    public HBox createHomeP()
    {
        HBox root = new HBox(10);
        root.setPrefWidth(790);
        root.setPrefHeight(400);

        VBox leftBox = new VBox(10);
        leftBox.setId("leftBox");
        leftBox.setAlignment(Pos.TOP_LEFT);

        Label title = new Label("EASV TICKET");
        title.setId("title");

        Button eventsButton = createSidebarButton("Events", true);
        eventsButton.setId("eventsBtn");

        Button ticketsButton = createSidebarButton("Tickets", false);
        ticketsButton.setId("ticketsBtn");

        Separator vSeparator = new Separator(Orientation.VERTICAL);
        vSeparator.setId("vSeparator");

        VBox rightBox = new VBox(10);
        rightBox.setId("rightBox");
        rightBox.setAlignment(Pos.TOP_RIGHT);

        ChoiceBox<String> user = new ChoiceBox<>();
        user.getItems().add("Alex Robert");
        user.getSelectionModel().selectFirst();
        user.setId("user");

        HBox currentPBox = new HBox(10);
        currentPBox.setPadding(new Insets(0, 0, 0, 20));
        VBox currP2Box = new VBox(10);
        Label currentP = new Label("Events");
        currentP.setId("currentP");
        TextField search = new TextField();
        search.setId("search");
        search.setPromptText("Search");
        ImageView searchIcon = new ImageView(new Image(getClass().getResourceAsStream("/dk/easv/ticketsystem/search.png")));
        searchIcon.setId("searchIcon");
        searchIcon.setFitWidth(22);
        searchIcon.setFitHeight(22);
        HBox searchBox = new HBox(2);
        searchBox.setId("searchBox");
        searchBox.getChildren().addAll(searchIcon, search);
        currP2Box.getChildren().addAll(currentP, searchBox);
        currentPBox.getChildren().add(currP2Box);

        FlowPane eventsTickets = new FlowPane();
        eventsTickets.setId("eveTick");
        eventsTickets.setHgap(10);
        eventsTickets.setVgap(15);
        eventsTickets.setPrefWrapLength(600);
        for (int i = 1; i <= 3; i++) {
            eventsTickets.getChildren().add(createEventCard("Event " + i));
        }

        leftBox.getChildren().addAll(title, eventsButton, ticketsButton);
        rightBox.getChildren().addAll(user, currentPBox, eventsTickets);
        root.getChildren().addAll(leftBox, vSeparator, rightBox);
        root.getStylesheets().add(getClass().getResource("/dk/easv/ticketsystem/homeStyle.css").toExternalForm());
        return root;
    }

    private VBox createEventCard(String eventTitle)
    {
        VBox card = new VBox(10);
        card.setId("card");

        Label title = new Label(eventTitle);
        title.setId("eventTitle");

        Label description = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        description.setWrapText(true);
        description.setId("description");

        Label coordinator = new Label("Event Coordinator: John Snow");
        coordinator.setId("coordinator");

        Separator separator = new Separator();

        HBox bottomRow = new HBox();
        bottomRow.setSpacing(10);
        bottomRow.setAlignment(Pos.CENTER_RIGHT);

        Label time = new Label("Posted at 12:45 AM");
        time.setId("time");

        Hyperlink ticketLink = new Hyperlink("Get Ticket");
        ticketLink.setId("link");

        bottomRow.getChildren().addAll(time, ticketLink);

        card.getChildren().addAll(title, description, coordinator, separator, bottomRow);

        return card;
    }

    public Button createSidebarButton(String text, boolean selected) {
        Button button = new Button(text);
        button.setId("sideBtn");
        button.setStyle(selected ? "-fx-background-color: #f4f0ff; -fx-text-fill: #6c43ff;" : "-fx-background-color: transparent; -fx-text-fill: black;");

        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #f4f0ff; -fx-text-fill: #6c43ff;"));
        button.setOnMouseExited(e -> button.setStyle(selected ? "-fx-background-color: #f4f0ff; -fx-text-fill: #6c43ff;" : "-fx-background-color: transparent; -fx-text-fill: black;"));

        return button;
    }
}
