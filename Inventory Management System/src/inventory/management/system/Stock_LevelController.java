/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management.system;

import static java.lang.reflect.Array.set;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Krises Maskey
 */
public class Stock_LevelController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Adapter> table = new TableView<Adapter>();
    @FXML
    private TableColumn lvl;
    @FXML
    private TableColumn one;
    @FXML
    private TableColumn two;
    @FXML
    private TableColumn three;
    @FXML
    private TableColumn four;
    HashMap<String, Integer> map = new HashMap<>();
    ArrayList<Adapter> arr = new ArrayList<Adapter>(Arrays.asList());
    Search sr = new Search();
          
    
    public void getData(){
        arr = sr.getAll(sr.getName());
        System.out.println(sr.getName());
         System.out.println(arr);
        table.setItems(FXCollections.observableList(arr));        
    }
    
    public void setColor(){
        for (Object item : table.getItems()) {
            String str = (four.getCellData(item).toString());
            
        }
        lvl.setCellFactory(new Callback<TableColumn, TableCell<Adapter, String>>() {
            @Override
            public TableCell call(TableColumn param) {
               return new TableCell<Adapter, String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                    if (!isEmpty() && Integer.parseInt(item)<3) {
                        this.setTextFill(Color.RED);
                        this.setStyle("-fx-background-color: red ;");
                        //setText(item);
                    }else if(!isEmpty() && Integer.parseInt(item)== 3 ){//&& Integer.parseInt(item)<4){
                        this.setTextFill(Color.YELLOW);
                        this.setStyle("-fx-background-color: yellow ;");
                        //setText(item);
                        
                    }else if(!isEmpty() && Integer.parseInt(item)>4){
                        this.setTextFill(Color.GREEN);
                        this.setStyle("-fx-background-color: green ;");
                        //setText(item);}
                    }
                    else{
                        this.setTextFill(Color.WHITESMOKE);
                    }}};}});}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //lvl.setStyle("-fx-background-color: yellow ;");
        
        map = sr.getName();
        Collection values =  map.values();
        ArrayList<Integer> newList = new ArrayList<>(values);
        table.setEditable(true);
        three.setCellValueFactory(
                new PropertyValueFactory<Adapter, String>("firstName"));
 
        one.setCellValueFactory(
                new PropertyValueFactory<Adapter, String>("lastName"));
        two.setCellValueFactory(
                new PropertyValueFactory<Adapter, String>("first"));
 
        four.setCellValueFactory(
                new PropertyValueFactory<Adapter, String>("last"));
        lvl.setCellValueFactory(
                new PropertyValueFactory<Adapter, String>("email"));
        
        getData();
        setColor();
    }   
    
   }
