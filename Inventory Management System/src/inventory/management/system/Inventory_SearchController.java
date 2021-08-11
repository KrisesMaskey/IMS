/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management.system;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.table.DefaultTableModel;

/**
 * FXML Controller class
 *
 * @author Krises Maskey
 */
public class Inventory_SearchController implements Initializable {

    /**
     * Initializes the controller class.
     */  
    @FXML
    private TextField search;
    @FXML
    private ChoiceBox cat;
    @FXML
    private ChoiceBox brand;
    @FXML
    private DatePicker date;
    @FXML
    private JFXButton icon;
    @FXML
    private TableView<Person> table = new TableView<Person>();
    @FXML
    private TableColumn one;
    @FXML
    private TableColumn two;
    @FXML
    private TableColumn three;
    @FXML
    private TableColumn four;
    @FXML
    private TableColumn five;
    @FXML
    private TableColumn six;
    String uniqueID="";
    HashMap<String, String> arr = new HashMap<>();
    List<String> columnData = new ArrayList<>();
    ArrayList<Person> item = new ArrayList<>(Arrays.asList());
    ArrayList<Person> find = new ArrayList<>(Arrays.asList());
    Search sr = new Search();           
    String xx;
    public void Onclick(){
      icon.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
              if(search.getText().equals("") && cat.getValue()==null && brand.getValue()==null && date.getValue()== null){
                System.out.println("NULL");
            }else{
                arr = sr.value(search.getText(), brand, cat,date);
                find = sr.find(arr, columnData);
                System.out.println(find);
                table.getItems().clear();
                table.setItems(FXCollections.observableList(find));
                 for (int i= 0;i<table.getItems().size();i++){
                    columnData.add(String.valueOf(table.getColumns().get(0).getCellObservableValue(i).getValue()));
                }
                }}});}
 //----------------------------------------------------------------------------------------------------------//   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setEditable(true);
        try{
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_user","root","");
        System.out.println("Connected");
        String q= "select * from data";
        PreparedStatement pst= conn.prepareStatement(q);
        ResultSet rs= pst.executeQuery();
        while (rs.next()){  
        item.addAll(FXCollections.observableArrayList(new Person(rs.getString("ID"), rs.getString("Name"), rs.getString("Brand"), rs.getString("Category"), rs.getString("Price"), rs.getString("Date"))));
         }
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        one.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
 
        two.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
 
        three.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
        four.setCellValueFactory(
                new PropertyValueFactory<Person, String>("first"));
 
        five.setCellValueFactory(
                new PropertyValueFactory<Person, String>("last"));
 
        six.setCellValueFactory(
                new PropertyValueFactory<Person, String>("em"));
        
        //System.out.println(item);
        table.setItems(FXCollections.observableList(item));
        brand.setItems(FXCollections.observableArrayList(sr.choicebox()));
        cat.setItems(FXCollections.observableArrayList(sr.CatBox()));
        Onclick();
        // TODO
    }}
