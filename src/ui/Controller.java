package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    Button btnExit;
    @FXML
    Button btnConnectServer;
    @FXML
    Button btnCreateServer;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnExit.setText("Exit");
        btnConnectServer.setText("Connect to server");
        btnCreateServer.setText("Create server");
        btnExit.setOnAction(event -> {
            System.exit(0);
        });
        btnCreateServer.setOnAction(event -> {
            try {
                Menu.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("CreateServer.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Menu.stage.show();

        });
        btnConnectServer.setOnAction(event -> {

            try {
                Menu.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("ConnectToServer.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Menu.stage.show();

        });


    }
}
