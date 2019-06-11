package game.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable
{
    @FXML Button btnExit;
    @FXML Button btnConnectServer;
    @FXML Button btnCreateServer;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Font.loadFont(getClass().getResource("font/Assassin$.ttf").toExternalForm(),30);

        btnExit.setOnAction(event -> {
            System.exit(0);
        });
        btnCreateServer.setOnAction(event -> {
            try {
                Menu.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("fxml/CreateServer.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Menu.stage.show();

        });
        btnConnectServer.setOnAction(event -> {


            System.out.println("shits fine");
            try {
                Menu.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("fxml/connectToServer.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Menu.stage.show();

        });


    }
}
