/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management.system;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Krises Maskey
 */
public class SignupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label sign;
    @FXML
    private VBox rootpane;
    @FXML
    private AnchorPane anc;
     @FXML
    private Label login;
    @FXML
    private TextField name;
    @FXML
    private TextField lname;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    private TextField pos;
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Database db = new Database();
    
    private void initialize(){
        sign.setText("Sign Up");
        sign.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
                if(name.getText().equals("") || lname.getText().equals("") || user.getText().equals("") || pass.getText().equals("") || pos.getText().equals("")){
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Please fill the details in all fields");
                    alert.showAndWait();
                }else{
                     System.out.println("Check");
                     try{
                      db.insert(name.getText(), lname.getText(), user.getText(), pass.getText(), pos.getText());
                        alert.setTitle("Dialog");
                        alert.setContentText("User successfully added!");
                        alert.showAndWait();
                      VBox fxmlLoader = (VBox) FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                      rootpane.getChildren().setAll(fxmlLoader);
                     }catch(Exception ex){
                         System.out.println(ex);
                     }
                }   
                }
            });}
    
    private void onclick(){
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try{
                      AnchorPane fxmlLoader = (AnchorPane) FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                      anc.getChildren().setAll(fxmlLoader);
                     }catch(Exception ex){
                         System.out.println(ex);
                     }
            }
        });
    }
       
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize();
        onclick();
        // TODO
    }    
    
}
