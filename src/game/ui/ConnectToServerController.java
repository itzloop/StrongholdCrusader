package game.ui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import game.GV;
import game.player.Player;
import game.comunication.Request;
import game.comunication.RequestType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ConnectToServerController implements Initializable
{

    @FXML Button btnConnect;
    @FXML Button btnBackToMenu;
    @FXML TextField txtName;
    @FXML ComboBox cmbServerList;
    
    private Player player;
    private boolean flag = true;
    Thread fillCombobox =  new Thread(() -> {

            try {
                player.handleRequest(new Request(player.getId() , player.getPort(), player.getLocalHost(),RequestType.GET_SERVERS , "get me all the available servers" ));
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
        fillCombobox.start();
        System.out.println(GV.Ip);
        btnConnect.setOnAction(event -> {
            try {
                if(!cmbServerList.getSelectionModel().isEmpty())
                {
                    player.setName(txtName.getText());
                    player.handleRequest(new Request(player.getId() , player.getPort(),GV.Ip,RequestType.CONNECT_TO_SERVER , cmbServerList.getValue().toString() ));
                    while (!player.hasMap()) { Thread.sleep(100); }
                    Menu.stage.setScene(new Scene(player.getMap().getPane()));
                    Menu.stage.setX(10);
                    Menu.stage.setY(10);
                    Menu.stage.setFullScreen(true);
                    Menu.stage.show();
                    System.out.println(player.getLocalHost().getHostAddress());
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