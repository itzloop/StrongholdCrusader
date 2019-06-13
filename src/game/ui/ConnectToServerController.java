package game.ui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import game.GV;
import game.player.Player;
import game.comunication.Request;
import game.comunication.RequestType;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
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
//    Thread getServers = new Thread(() -> {
//        try {
//            DatagramSocket socket = new DatagramSocket();
//            System.out.println(socket.getPort());
//            Request getServerRequest = new Request(RequestType.GET_SERVERS , socket.getPort()+"");
//            DatagramPacket packet;
//            byte[] buffer = new byte[GV.packetSize];
//            while (true)
//            {
//                packet = new DatagramPacket(buffer , buffer.length);
//                socket.receive(packet);
//                byte[] data = new byte[packet.getLength()];
//                System.arraycopy(packet.getData() , 0 , data , 0 , packet.getLength());
//                String s = new String(data);
//                integers = new Gson().fromJson(s , type);
//                cmbServerList.setItems(FXCollections.observableList(integers));
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    });

    //TODO Fix this here and in the Player and Servers Class
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Optional<Player> player = Player.createPlayer(txtName.getText());
        new Thread(() -> {
            if (player.isPresent()){
                try {
                    player.get().sendRequest(RequestType.GET_SERVERS);
                    while (true)
                    {
                        Optional<List<Integer>> servers =  player.get().serverList();
                        if(servers.isPresent())
                        {
                            System.out.println(servers.get());
                            cmbServerList.getItems().addAll(FXCollections.observableArrayList(servers.get()));
                            cmbServerList.setVisibleRowCount(3);
                            break;
                        }
                        continue;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        btnConnect.setOnAction(event -> {
            try {

                if(player.isPresent())
                {
                    player.get().sendRequest(RequestType.ESTABLISHING_CONNECTION);
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