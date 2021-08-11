/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale_System;

import com.jfoenix.controls.JFXButton;
import inventory.management.system.Adapter;
import inventory.management.system.Database;
import inventory.management.system.Person;
import static inventory.management.system.Person.flatContains;
import inventory.management.system.Search;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Krises Maskey
 */
public class Sales_LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btn;
    @FXML
    private TableView<View> customer = new TableView<View>();
    @FXML
    private JFXButton sel;
    @FXML
    private JFXButton sell;
     @FXML
    private JFXButton del;
    @FXML
    private TextField search;
    @FXML
    private ChoiceBox cat;
    @FXML
    private ChoiceBox brand;
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
    @FXML
    private TableView<Person> table = new TableView<Person>();
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn price;
    @FXML
    private TableColumn qty;
    @FXML
    private TableColumn brnd;
    @FXML
    private Label receipt;
    @FXML
    private Label total;
    double k =0;
    HashMap<String, String> arr = new HashMap<>();
    List<String> columnData = new ArrayList<>();
    List<String> data = new ArrayList<>();
   
    List<Person> common = new ArrayList<>();
  
    ArrayList<Person> find = new ArrayList<>(Arrays.asList());
    ArrayList<View> cust = new ArrayList<>(Arrays.asList());
    ArrayList<View> trial = new ArrayList<>(Arrays.asList());
    ArrayList<Double> sum = new ArrayList<>();
    HashMap<String, Integer> values = new HashMap<>();
    HashMap<String, List<String>> selected = new HashMap<>();
    public static final HashMap<String,List<String>> map = new HashMap<String, List<String>>();
    Find sr = new Find();   
    String str="", lol="";
    double x=0;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Sale_Database saledb = new Sale_Database();
    Database db = new Database();
    
 //----------------SELECT ROW -----------------------------------------------------//   
    public void getRow(){
        sel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Person per = table.getSelectionModel().getSelectedItem();
                data.clear();
                for (int i= 0;i<customer.getItems().size();i++){
                   data.add(String.valueOf(customer.getColumns().get(0).getCellObservableValue(i).getValue()));
                }
                
                if(per==null){
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Please Select an item");
                    alert.showAndWait(); 
                }else{                                                      
                    boolean flag = flatContains(selected.values(), per.getFirstName());
                    System.out.println(flag+""+selected+" = "+per.getFirstName());
                    if(data.contains(per.getLastName()) && !flag){
                        //common.add(per);
                        System.out.println("True");
                        customer.getItems().remove(data.indexOf(per.getLastName()));
                    }
                    if (!selected.containsKey(per.getLastName())){
                        selected.putIfAbsent(per.getLastName(), new ArrayList<>());
                        selected.get(per.getLastName()).add(per.getFirstName());
                    }else if(!flatContains(selected.values(), per.getFirstName())){
                        selected.get(per.getLastName()).add(per.getFirstName());
                    } 
                    cust.addAll(per.getAll(map, values));
                    customer.setItems(FXCollections.observableList(cust));

                    sum.clear();
                    for(Object o : customer.getItems()){
                        x = Double.parseDouble(price.getCellData(o).toString());
                           sum.add(x);
                    }
                    Double tot = sum.stream().mapToDouble(i -> i.doubleValue()).sum();
                    total.setText(String.valueOf("$ " + new DecimalFormat("#.##").format(tot)));
                }
                
              }
            
        });
        //------------------------DELETE---------------------------------------//
        del.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                customer.setEditable(true);
                View kk = customer.getSelectionModel().getSelectedItem();
                if(kk==null){
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Please Select an item to delete");
                    alert.showAndWait(); 
                }
                int selectedIndex = customer.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    customer.getItems().remove(selectedIndex);
                    System.out.println(kk.getFirstName());
                    map.remove(kk.getFirstName());
                    values.remove(kk.getFirstName());
                    selected.remove(kk.getFirstName());
                    System.out.println(" After Deletion  " + selected);
                }
                sum.clear();
                for(Object o : customer.getItems()){
                    x = Double.parseDouble(price.getCellData(o).toString());
                       sum.add(x);
                }
                Double tot = sum.stream().mapToDouble(i -> i.doubleValue()).sum();
                total.setText(String.valueOf("$ " + new DecimalFormat("#.##").format(tot)));
                customer.getSelectionModel().clearSelection();
              }
        });
    }
//----------------------------SELL------------------------------//   
    public void sell(){
        sell.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Clicked");
                if (!customer.getItems().isEmpty()){
                    customer.getItems().forEach((o) -> {
                        boolean insert = saledb.insert(name.getCellData(o).toString(), brnd.getCellData(o).toString(), qty.getCellData(o).toString(), price.getCellData(o).toString());
                        System.out.println(!insert);
                    });
                    System.out.println(db.delete(selected));
                    customer.getItems().clear();
                    table.setItems(FXCollections.observableList(sr.getItems()));
                    selected.clear();
                    total.setText("");
                    receipt.setText(String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 5));
                    values.clear();
                    map.clear();
            }else{
                    alert.setTitle("Error Dialog");
                    alert.setContentText("No items in the Table");
                    alert.showAndWait(); 
                }
            }
        });
    }
    
    public void Onclick(){
      btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
              if(search.getText().equals("") && cat.getValue()==null && brand.getValue()==null){
                System.out.println("NULL");
            }else{
                arr = sr.value(search.getText(), brand, cat);
                find = sr.find(arr, columnData);
                System.out.println(find);
                table.getItems().clear();
                table.setItems(FXCollections.observableList(find));
                 for (int i= 0;i<table.getItems().size();i++){
                    columnData.add(String.valueOf(table.getColumns().get(0).getCellObservableValue(i).getValue()));
                }
              }}});}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       receipt.setText((String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(0, 5)));
       table.setEditable(true);
              
        tablecolumns();
        customercolumns();
        
        table.setItems(FXCollections.observableList(sr.getItems()));
        customer.setItems(FXCollections.observableList(cust));
        brand.setItems(FXCollections.observableArrayList(sr.choicebox()));
        cat.setItems(FXCollections.observableArrayList(sr.CatBox()));
       
        Onclick();
        getRow();   
        sell();
    } 
    
    public void customercolumns(){
        name.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
 
        brnd.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
 
        qty.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
        price.setCellValueFactory(
                new PropertyValueFactory<Person, String>("first"));
 
    }
    
    public void tablecolumns(){
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
    }
    
     public static boolean flatContains(
            Iterable<? extends Collection<?>> collections,
            Object value
        ) {
            for (Collection<?> collection : collections) {
                if (collection.contains(value)) {
                    return true;
                }
            }
            return false;
        }
}
