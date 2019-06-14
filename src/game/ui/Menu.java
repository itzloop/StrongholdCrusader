package game.ui;

import game.player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

public class Menu extends Application
{
    public static Stage stage;
    static Player player;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        player = Player.createPlayer("").get();
        Parent root = FXMLLoader.load(getClass().getResource("fxml/menu.fxml"));
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Stronghold Crusader");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
