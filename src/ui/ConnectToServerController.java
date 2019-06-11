package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectToServerController implements Initializable
{
    @FXML
    Button btnConnectToServer;
    @FXML
    Button btnBackToMenu;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBackToMenu.setOnAction(event -> {
            try {
                Menu.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("menu.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Menu.stage.show();

        });
    }
}
