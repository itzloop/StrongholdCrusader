package game.ui;

import game.GV;
import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class createServerController implements Initializable {

    @FXML Button btnConnect;
    @FXML Button btnBack;
    @FXML TextField txtfIP;
    @FXML CheckBox localhost;
    @FXML Text txtInvalidIP;
    @FXML Text lblIpFormatChecker;

    public void initialize(URL location, ResourceBundle resources) {
        txtInvalidIP.setVisible(false);

        localhost.setOnAction(event -> {
            txtfIP.setDisable(!txtfIP.isDisabled());
        });
        txtfIP.setPromptText("ranges from 0-255 e.g. 127.0.0.1 ");
        txtfIP.setOnKeyReleased(event -> {
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
