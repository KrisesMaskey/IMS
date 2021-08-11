/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management.system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.time.LocalTime;
import java.util.UUID;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Krises Maskey
 */
public class MainScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXHamburger hamburg;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane anc;
    @FXML
    private AnchorPane AA;
    @FXML
    private Label time;
    
    
    public void hamclick(){
        HamburgerBackArrowBasicTransition task = new HamburgerBackArrowBasicTransition(hamburg);
        try{
        AnchorPane box = FXMLLoader.load((getClass().getResource("NavDrawer.fxml")));
        drawer.setSidePane(box);
            for(Node node: box.getChildren()){
                if(node instanceof Button){
                    System.out.println("lol");
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
                    switch(node.getAccessibleText()){
                        case "M1": 
                            anc.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
                            try{
                            AnchorPane fxmlLoader = (AnchorPane) FXMLLoader.load(getClass().getResource("Inventory_Search.fxml"));
                            anc.getChildren().setAll(fxmlLoader);
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }
                            task.setRate(task.getRate() * -1);
                            task.play();
                            drawer.close();
                            break;
                        case "M2": 
                            anc.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY, Insets.EMPTY)));
                            try{
                            AnchorPane fxmlLoader = (AnchorPane) FXMLLoader.load(getClass().getResource("Stock_Level.fxml"));
                            anc.getChildren().setAll(fxmlLoader);
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }
                            task.setRate(task.getRate() * -1);
                            task.play();
                            drawer.close();
                            break;
                        case "M3": 
                            anc.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
                            try{
                            AnchorPane fxmlLoader = (AnchorPane) FXMLLoader.load(getClass().getResource("Sales.fxml"));
                            anc.getChildren().setAll(fxmlLoader);
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }
                            task.setRate(task.getRate() * -1);
                            task.play();
                            drawer.close();
                            break;
                            
                        case "add": 
                            anc.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
                            try{
                            AnchorPane fxmlLoader = (AnchorPane) FXMLLoader.load(getClass().getResource("InsertData.fxml"));
                            anc.getChildren().setAll(fxmlLoader);
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }
                            task.setRate(task.getRate() * -1);
                            task.play();
                            drawer.close();
                            break;
                         case "log": 
                            try{
                                Stage primary = new Stage();
                                Stage s = (Stage) anc.getScene().getWindow();
                                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                                primary.setScene(new Scene(root));
                                s.close();
                                primary.show();
                            }catch(Exception ex){
                                ex.printStackTrace();
                            } break;
                            
                        case "sign": 
                            try{
                            VBox fxmlLoader = (VBox) FXMLLoader.load(getClass().getResource("Signup.fxml"));
                            AA.getChildren().setAll(fxmlLoader);
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }break;
                            
                    }
            });
                }
                }
               
            
        }
        
        catch(Exception ex){
            ex.printStackTrace();
        }
       
        task.setRate(-1);
        hamburg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                task.setRate(task.getRate() * -1);
                task.play();
                 if(drawer.isShown()){
                        drawer.close();
                    }else{
                        drawer.open();
                    }
            }
        });
    }
  
            
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hamclick();
        //btnClick();
        // TODO
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {        
        LocalTime currentTime = LocalTime.now();
        time.setText(currentTime.getHour() + ":" + currentTime.getMinute());
        time.setStyle("-fx-font: 42 Bold Garamond;");
        }),
             new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        }    
    
    }
