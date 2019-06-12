package game.ui;

import game.GV;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateServerController implements Initializable {

    @FXML Button btnBack;
    @FXML Button btnConnect;
    @FXML TextField txtfIP;
    @FXML Text txtInvalidIP;
    @FXML Text lblIpFormatChecker;
    @FXML CheckBox localhost;


    public void initialize(URL location, ResourceBundle resources) {


        localhost.setOnAction(event -> {
            txtfIP.setDisable(!txtfIP.isDisabled());
        });
        txtfIP.setOnAction(event -> {
            localhost.setDisable(!localhost.isDisabled());
        });
        txtfIP.setPromptText("ranges from 0-255 e.g. 127.0.0.1 ");
        lblIpFormatChecker.setStyle("-fx-font-size: 15");
        txtfIP.setOnKeyReleased(event -> {
            lblIpFormatChecker.setLayoutX(370);
            lblIpFormatChecker.setLayoutY(197);
            lblIpFormatChecker.setVisible(true);
            if (txtfIP.getText().matches(GV.ipFormat)){
                lblIpFormatChecker.setText(Character.toString((char)0x2713));

                lblIpFormatChecker.setFill(Color.GREEN);
            }
            else
            {
                lblIpFormatChecker.setText("X");
                lblIpFormatChecker.setFill(Color.RED);

            }
        });
        btnBack.setOnAction(event -> {
            try {
                Menu.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("fxml/menu.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Menu.stage.show();
        });
    }
}
