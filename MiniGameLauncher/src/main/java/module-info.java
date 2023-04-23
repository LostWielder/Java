module com.example.minigamelauncher {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;
    requires java.desktop;

    opens com.example.minigamelauncher to javafx.fxml;
    exports com.example.minigamelauncher;
    exports com.example.minigamelauncher.IntelliDice;
    opens com.example.minigamelauncher.IntelliDice to javafx.fxml;
    opens com.example.minigamelauncher.intellimatch to javafx.fxml;
    opens com.example.minigamelauncher.ttt.controller to javafx.fxml;
    opens com.example.minigamelauncher.checkersfx to javafx.fxml;
    //This is probably the fix stop looking
}