module interfaceG {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens interfaceG to javafx.fxml;
    exports interfaceG;
}
