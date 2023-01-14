package de.hhn.it.devtools.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class PaintLauncher extends Application {



    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/fxml/paint/paint.fxml"))));
        primaryStage.setTitle("Paint");
        primaryStage.setScene(new Scene(root, 1500, 1000));
        primaryStage.show();
    }



}
