/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management.system;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Krises Maskey
 */
public class InsertDataController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label ID;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField brand;
    @FXML
    private JFXTextField cat;
    @FXML
    private JFXTextField price;
    @FXML
    private DatePicker date;
    @FXML
    private JFXButton btn;
    Database db = new Database();
    boolean flag= false;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    public void Onclick(){
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!name.getText().isEmpty() && !brand.getText().isEmpty() && !cat.getText().isEmpty() && !price.getText().isEmpty() && date.getValue() != null){
                    flag = db.add(ID.getText(), name.getText(), price.getText(), brand.getText(), cat.getText(), date.getValue().toString());
                    name.setText("");price.setText("");brand.setText("");cat.setText("");date.setValue(null);
                    final String uuid = UUID.randomUUID().toString().replace("-", "");
                    ID.setText(uuid.substring(0,5));
                }else{
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Please fill the details in all fields");
                    alert.showAndWait();
                }
               System.out.println(flag);
            }
        });
               
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         final String uuid = UUID.randomUUID().toString().replace("-", "");
         ID.setText(uuid.substring(0,5));
         Onclick();
        // TODO
    }    
    
}
