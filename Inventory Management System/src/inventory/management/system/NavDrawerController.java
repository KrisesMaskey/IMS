/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management.system;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import static inventory.management.system.Database.insert;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Krises Maskey
 */
public class NavDrawerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //MainScreenController main = new MainScreenController();
    @FXML
    private JFXButton insert;
    @FXML
    private JFXButton log;
    @FXML
    private JFXButton sign;
    @FXML
    private JFXButton inv;
    @FXML
    private JFXButton stk;
    @FXML
    private JFXButton sale;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        insert.setGraphic(new ImageView("images/add (1).png"));
        insert.setStyle("-fx-alignment: LEFT;");  
        
        sale.setGraphic(new ImageView("images/bm.png"));
        sale.setStyle("-fx-alignment: LEFT;");  
        
        inv.setGraphic(new ImageView("images/inventory.png"));
        inv.setStyle("-fx-alignment: LEFT;"); 
        
        stk.setGraphic(new ImageView("images/factory.png"));
        stk.setStyle("-fx-alignment: LEFT;"); 
        
        log.setGraphic(new ImageView("images/logout.png"));
        log.setStyle("-fx-alignment: LEFT;");  
        
        sign.setGraphic(new ImageView("images/signup.png"));
        sign.setStyle("-fx-alignment: LEFT;");  
        // TODO
    }    
    
}
