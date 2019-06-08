package game.player;

import javafx.application.Application;
import javafx.stage.Stage;

public class Player extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Qazal");
        primaryStage.show();
    }
}
