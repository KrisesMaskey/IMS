/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management.system;
import  Sale_System.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Krises Maskey
 */
public class FXMLDocumentController implements Initializable{
   
    @FXML
    private Label lbl;
    @FXML
    private VBox rootpane;
    @FXML
    private Label login;
    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;
    
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Database db = new Database();
    static String flag = "";
    //For Sign up Button------------------------------------------------
    private void initialize() {
        
            
        }
    
     //For login button----------------------------------------------------------------
    
    private void init(){
        login.setText("Login");
        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(user.getText().equals("") || pass.getText().equals("")){
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Please fill the details in all required fields");
                    alert.showAndWait(); 
                }else{
                    System.out.println("Success");
                    flag=db.login(user.getText(), pass.getText());
                    
                    if (flag==null){
                        alert.setTitle("Error Dialog");
                        alert.setContentText("Invalid Username or Password!");
                        alert.showAndWait(); 
                    }else if(flag.equals("Inventory")){
                        try {
                            AnchorPane fxmlLoader = (AnchorPane) FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                            rootpane.getChildren().setAll(fxmlLoader);
                        } catch(Exception excep) {
                            excep.printStackTrace();
                        }
                    }else if(flag.equals("Sales")){
                        try {
                            Stage primary = new Stage();
                            //AnchorPane fxmlLoader = (AnchorPane) FXMLLoader.load(getClass().getResource("/Sale_System/Sales_Login.fxml"));
                            Stage s = (Stage) rootpane.getScene().getWindow();
                            Parent root = FXMLLoader.load(getClass().getResource("/Sale_System/Sales_Login.fxml"));
                            primary.setScene(new Scene(root));
                            s.close();
                            primary.show();
                            //rootpane.getChildren().setAll(fxmlLoader);
                        } catch(Exception excep) {
                            excep.printStackTrace();
                        }
                    }
                }
            }
        });
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         initialize();
         init();
    } 
}
