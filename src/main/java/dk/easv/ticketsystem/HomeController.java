package dk.easv.ticketsystem;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class HomeController
{
    private Label currentP;
    private FlowPane eventsTickets;
    private Button eventsButton;
    private Button ticketsButton;
    private Image ticketsImg;
    private Image ticketsSelImg;
    private ImageView ticketsIcon;
    private Image eventsImg;
    private Image eventsNotSelImg;
    private ImageView eventsIcon;

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

        eventsNotSelImg = new Image(getClass().getResourceAsStream("/dk/easv/ticketsystem/eventsNotSel.png"));
        eventsImg = new Image(getClass().getResourceAsStream("/dk/easv/ticketsystem/events.png"));
        eventsIcon = new ImageView(eventsImg);
        eventsIcon.setFitWidth(20);
        eventsIcon.setFitHeight(15);
        eventsIcon.setPreserveRatio(true);
        eventsButton = new Button("Events", eventsIcon);
        eventsButton.setGraphicTextGap(10);
        eventsButton.setId("sideBtnSelected");
        eventsButton.setPadding(new Insets(0, 0, 0, 11));
        eventsButton.setOnAction(_ -> toTickets(false));

        ticketsSelImg = new Image(getClass().getResourceAsStream("/dk/easv/ticketsystem/ticketSel.png"));
        ticketsImg = new Image(getClass().getResourceAsStream("/dk/easv/ticketsystem/ticket.png"));
        ticketsIcon = new ImageView(ticketsImg);
        ticketsIcon.setFitWidth(20);
        ticketsIcon.setFitHeight(15);
        ticketsIcon.setPreserveRatio(true);
        ticketsButton = new Button("Tickets", ticketsIcon);
        ticketsButton.setGraphicTextGap(8);
        ticketsButton.setPadding(new Insets(0, 0, 0, 10));
        ticketsButton.setId("sideBtnNotSelected");
        ticketsButton.setOnAction(_ -> toTickets(true));

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
        currentP = new Label("Events");
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

        eventsTickets = new FlowPane();
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

        Label time = new Label("Posted at 12:45 AM");
        time.setId("time");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox titleRow = new HBox(title, spacer, time);
        titleRow.setAlignment(Pos.CENTER_LEFT);

        Label description = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        description.setWrapText(true);
        description.setId("description");

        Label coordinator = new Label("Event Coordinator: John Snow");
        coordinator.setId("coordinator");

        Separator separator = new Separator();

        HBox bottomRow = new HBox();
        bottomRow.setSpacing(370);
        bottomRow.setAlignment(Pos.CENTER_LEFT);

        Hyperlink ticketLink = new Hyperlink("Get Ticket");
        ticketLink.setId("link");

        bottomRow.getChildren().addAll(coordinator, ticketLink);
        card.getChildren().addAll(titleRow, description, separator, bottomRow);

        return card;
    }

    private void toTickets(boolean isToTickets)
    {
        ObservableList<Node> cards = eventsTickets.getChildren();

        for(int i = 0; i < 3; i++)
        {
            if(cards.get(i) instanceof VBox vBox)
            {
                HBox hBox = (HBox) vBox.getChildren().getLast();

                Hyperlink link = (Hyperlink) hBox.getChildren().getLast();

                if(isToTickets)
                {
                    link.setText("Open Ticket");
                    currentP.setText("Tickets");
                    eventsButton.setId("sideBtnNotSelected");
                    ticketsButton.setId("sideBtnSelected");
                    ticketsIcon.setImage(ticketsSelImg);
                    eventsIcon.setImage(eventsNotSelImg);
                }
                else
                {
                    link.setText("Get Ticket");
                    currentP.setText("Events");
                    eventsButton.setId("sideBtnSelected");
                    ticketsButton.setId("sideBtnNotSelected");
                    ticketsIcon.setImage(ticketsImg);
                    eventsIcon.setImage(eventsImg);
                }
            }
        }
    }
}
