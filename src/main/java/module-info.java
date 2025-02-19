module dk.easv.ticketsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens dk.easv.ticketsystem to javafx.fxml;
    exports dk.easv.ticketsystem;
}