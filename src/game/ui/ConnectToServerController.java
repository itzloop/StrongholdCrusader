package game.ui;

import game.player.Player;
import game.player.Request;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ConnectToServerController implements Initializable
{
    @FXML Button btnConnect;
    @FXML Button btnBackToMenu;
    @FXML TextField txtName;
    //TODO inja txtName o validation kon
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnConnect.setOnAction(event -> {
            try {
                Optional<Player> player = Player.createPlayer(txtName.getText());
                if(player.isPresent())
                {
                    player.get().sendRequest(Request.ESTABLISHING_CONNECTION);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnBackToMenu.setOnAction(event -> {
            try {
                Menu.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("fxml/menu.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Menu.stage.show();

        });
    }
}

//link: https://stackoverflow.com/questions/12018245/regular-expression-to-validate-username