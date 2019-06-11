package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class createServerController implements Initializable {

    @FXML
    Button btnConnect;
    @FXML
    Button btnBack;
    @FXML
    TextField txtfIP;
    @FXML
    CheckBox localhost;
    @FXML
    Text txtInvalidIP;
    //String string = "[01]?[0-9]{1,2}\\.|2[0-4][0-9]\\.|25[0-5]\\."

    public void initialize(URL location, ResourceBundle resources) {
        txtInvalidIP.setVisible(false);
        btnBack.setOnAction(event -> {
            try {
                Menu.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("menu.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Menu.stage.show();
        });
//        boolean selelcted = localhost.isSelected();
//            localhost.isSelected()
//                txtfIP.setDisable(true);

    }
}
