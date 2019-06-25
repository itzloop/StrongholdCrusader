package game.ui;

import game.GV;
import game.player.Player;
import game.network.communications.message.Request;
import game.network.communications.message.RequestType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ConnectToServerController implements Initializable
{

    @FXML Button btnConnect;
    @FXML Button btnBackToMenu;
    @FXML Button btnFetch;
    @FXML TextField txtName;
    @FXML TextField txtIP;
    @FXML ComboBox cmbServerList;
    private Player player;
    private boolean flag = true;
    Thread fillCombobox =  new Thread(() -> {

            try {
                player.Communication().communicate(new Request(player.getId() , player.getPort(), player.getServerIp(),RequestType.GET_SERVERS , "get me all the available servers" ));
                while (flag)
                {
                    Optional<List<Integer>> servers =  player.serverList();
                    if(servers.isPresent())
                    {
                        System.out.println(servers.get());
                        cmbServerList.getItems().clear();
                        cmbServerList.getItems().addAll(FXCollections.observableArrayList(servers.get()));
                        cmbServerList.setVisibleRowCount(servers.get().size());
                        break;
                    }
                    Thread.sleep(100);
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    });


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player = new Player("");
        btnFetch.setOnAction(event -> {
            if(!txtIP.getText().isEmpty() && txtIP.getText().trim().matches(GV.ipFormat))
            {
                try {
                    player.setServerIp(InetAddress.getByName(txtIP.getText()));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                fillCombobox.start();
            }
            else
            {
                System.out.println("wrong input");
            }
        });

        System.out.println(GV.Ip);
        btnConnect.setOnAction(event -> {
            try {
                if(!cmbServerList.getSelectionModel().isEmpty())
                {
                    player.setName(txtName.getText());
                    player.Communication().communicate(new Request(player.getId() , player.getPort(),GV.Ip,RequestType.CONNECT_TO_SERVER , cmbServerList.getValue().toString() ));
                    while (!player.hasMap()) { Thread.sleep(100); }
                    Menu.stage.setScene(new Scene(player.getMap().getPane()));
                    Menu.stage.setX(10);
                    Menu.stage.setY(10);
                    Menu.stage.setFullScreen(true);
                    Menu.stage.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        btnBackToMenu.setOnAction(event -> {
            try {
                flag= false;
                Menu.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("fxml/menu.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Menu.stage.show();

        });
    }


}