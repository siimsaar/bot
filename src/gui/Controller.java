package gui;

import core.Topic;
import javafx.application.Platform;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller extends OutputStream implements Initializable {

    boolean dlWEBM;

    @FXML
    public Button startButton;
    @FXML
    public CheckBox enableWEBM;
    @FXML
    public TextArea stdout;
    @FXML
    public ComboBox<String> topic = new ComboBox<>();
    @FXML
    public TextField refreshInt;


    public void startThread(ActionEvent event) {
        System.out.println(refreshInt.getText());
        try {
            if (topic.getValue().equals("/mu/ - kpg")) {
                Topic kek = new Topic("mu", "/home/ayy/dlthing/", "/home/ayy/dlthing/catalogMu.json", "mu");
                if (enableWEBM.isSelected()) {
                    kek.setWEBM();
                }
                kek.start();
            } else if (topic.getValue().equals("/p/ - Recent Photo")) {
                Topic kuk = new Topic("p", "/home/ayy/dlthing/", "/home/ayy/dlthing/catalogP.json", "p");
                kuk.start();
            }
        } catch (NullPointerException e) {
            System.out.println("[WARNING] Pick a topic!");
        }
    }

    //this is from stackoverflow, i have no idea how it works
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        topic.getItems().addAll("/mu/ - kpg", "/p/ - Recent Photo");
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out, true));
    }
    //this is from stackoverflow, i have no idea how it works
    public void appendText(String str) {
        Platform.runLater(() -> stdout.appendText(str));
    }

    @Override
    public void write(int arg0) throws IOException {
        // TODO Auto-generated method stub

    }
}
