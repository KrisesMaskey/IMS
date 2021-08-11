/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Krises Maskey
 */
public class Search {
    public static HashMap value( String a, ChoiceBox b,ChoiceBox x, DatePicker y){
        HashMap<String,String> array = new HashMap<>();
       //String tryy = x.getValue().toString();
        //String tyy = b.getValue().toString();
        if (!a.equals(" ")){
            array.put("Name", a);
            
        }if(x.getValue() != null  && !x.getValue().toString().equals("")){
            array.put("Category",x.getValue().toString());
        } 
        if(b .getValue() !=null && !b.getValue().toString().equals("")){
            array.put("Brand", b.getValue().toString());
        } 
        if(y.getValue()!= null){
            array.put("Date", y.getValue().toString());
        }
         return array;
    }
//-------------------------------------------------------------------------------------//
    public static ArrayList find(HashMap arr, List k){
        ArrayList<Person> item = new ArrayList<>(Arrays.asList());
        ArrayList<String> ass = new ArrayList<>();
        Set keys = arr.keySet();
        String q="", z="";
        ResultSet rs=null, r=null;
        try{
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_user","root","");
        System.out.println("Connected");
        System.out.println(arr.size());
       
        for (Object key: keys){
            //System.out.println(key + ": " + arr.get(key));
            z= "select * from data where ID ='" + arr.get(key) + "'";
            q= "select * from data where " + key +"='" + arr.get(key) + "'" ;
            PreparedStatement pst= conn.prepareStatement(q);
            PreparedStatement ps= conn.prepareStatement(z);
            rs= pst.executeQuery();
            r= ps.executeQuery();
      
            rs.beforeFirst();
         while (rs.next()) {
             if(!ass.contains(rs.getString("ID"))){
                 ass.add(rs.getString("ID"));
                 item.addAll(FXCollections.observableArrayList(new Person(rs.getString("ID"), rs.getString("Name"), rs.getString("Brand"), rs.getString("Category"), rs.getString("Price"), rs.getString("Date"))));
             }
             }
           if (r.next() && (!ass.contains(r.getString("ID")))){
               item.addAll(FXCollections.observableArrayList(new Person(r.getString("ID"), r.getString("Name"), r.getString("Brand"), r.getString("Category"), r.getString("Price"), r.getString("Date"))));
            }
                      
        }
              }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return item;
    }
     public static ArrayList choicebox(){
        ArrayList<String> arr = new ArrayList<>();
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_user","root","");
            String q= "select * from data";
            PreparedStatement pst= conn.prepareStatement(q);
            ResultSet rs= pst.executeQuery();

            while (rs.next()){
                if (!arr.contains(rs.getString("Brand"))){
                     arr.add(rs.getString("Brand"));
                }
               }
          }catch (SQLException ex) {
             System.out.println(ex);}
           
        return arr;
    }
     public static ArrayList CatBox(){
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(" "));
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_user","root","");
            String q= "select * from data";
            PreparedStatement pst= conn.prepareStatement(q);
            ResultSet rs= pst.executeQuery();

            while (rs.next()){
                if (!arr.contains(rs.getString("Category"))){
                     arr.add(rs.getString("Category"));
                }
               }
          }catch (SQLException ex) {
             System.out.println(ex);}
           
        return arr;
    }
  
 //----------------------Stock-Level-------------------------------------------------------------------------------------//
     
    public static HashMap getName(){
        HashMap<String, Integer> arr = new HashMap<>();
        int c=1;
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_user","root","");
            String q= "select * from data";
            PreparedStatement pst= conn.prepareStatement(q);
            ResultSet rs= pst.executeQuery();

            while (rs.next()){
                if (!arr.containsKey(rs.getString("Name"))){
                     arr.put(rs.getString("Name"), 1);
                }else{
                    arr.replace(rs.getString("Name"), arr.get(rs.getString("Name"))+1);
                }
               }
          }catch (SQLException ex) {
             System.out.println(ex);}
           
        return arr;
    }
    
     public static ArrayList getAll(HashMap map){
        ArrayList<Adapter> arr = new ArrayList<Adapter>();
        Set keys = map.keySet();
        ArrayList<String> ass = new ArrayList<>();
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_user","root","");
           
            for (Object key: keys){
                String q= "select * from data where Name='" + key + "'";    
                PreparedStatement pst= conn.prepareStatement(q);
                ResultSet rs= pst.executeQuery();

                while (rs.next()){
                    if (!ass.contains(key.toString())){
                        ass.add(key.toString());
                        arr.addAll(FXCollections.observableArrayList(new Adapter(String.valueOf(key), rs.getString("Brand"), rs.getString("Category"), String.valueOf(map.get(key)), (map.get(key)).toString())));
                   }}
            }
          }catch (SQLException ex) {
             System.out.println(ex);}
           
        
        return arr;
    }
}
