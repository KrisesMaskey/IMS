/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management.system;

import Sale_System.View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Krises Maskey
 */
public class SalesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Database db = new Database();
    
    @FXML
    private TableView table;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn brand;
    @FXML
    private TableColumn total;
    @FXML
    private TableColumn revenue;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setEditable(true);
        System.out.println(db.sales());
        customercolumns();
        table.setItems(FXCollections.observableList(db.sales()));
        // TODO
    }    
    public void customercolumns(){
        name.setCellValueFactory(
                new PropertyValueFactory<View, String>("firstName"));
 
        brand.setCellValueFactory(
                new PropertyValueFactory<View, String>("lastName"));
 
        total.setCellValueFactory(
                new PropertyValueFactory<View, String>("email"));
        revenue.setCellValueFactory(
                new PropertyValueFactory<View, String>("first"));
 
    }
}
