/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management.system;

import Sale_System.View;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javafx.collections.FXCollections;

/**
 *
 * @author Krises Maskey
 */
public class Database {
    
    public static boolean insert(String name, String lname, String user, String pass, String pos){
        boolean k=false;
             try{
                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_user","root","");
                System.out.println("Connected");
                PreparedStatement st= conn.prepareStatement("Insert into info(FNAME,LName,Username, Password, Position) Values (?,?,?,?,?)");
                
                st.setString(1,name);
                st.setString(2,lname);
                st.setString(3,user);
                st.setString(4,pass);
                st.setString(5,pos);
//                
                k=st.execute();
             }catch(SQLException ex) {
                throw new IllegalStateException("Cannot connect the database!", ex);
                }
        
     return k;
    }
    public static String login(String user, String pass){
        HashMap<String,String> map = new LinkedHashMap<>();
        ArrayList<String> arr = new ArrayList<>();
       
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_user","root","");
            String q= "select * from info";
            PreparedStatement pst= conn.prepareStatement(q);
            ResultSet rs= pst.executeQuery();

            while (rs.next()){
             //System.out.println(rs.getString("Username")+" = "+rs.getString("Password"));
             map.put(rs.getString("Username"), rs.getString("Password"));
             arr.add(rs.getString("Position"));
            }
            
          }catch (SQLException ex) {
             System.out.println(ex);}
        
           ArrayList<String> check = new ArrayList<>(map.values());
          if(pass.equals(map.get(user))){
              System.out.println(arr.get(check.indexOf(map.get(user))));
              return arr.get(check.indexOf(map.get(user)));
          }else
              return null;
}
    public static boolean add(String a, String b, String c, String d, String e, String f){
        boolean k=false;
        try{
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_user","root","");
                    System.out.println("Connected");
                    PreparedStatement st= conn.prepareStatement("Insert into data(ID,Name,Price,Brand,Category,Date) Values (?,?,?,?,?,?)");

                        st.setString(1,a);
                        st.setString(2,b);
                        st.setString(3,c);
                        st.setString(4,d);
                        st.setString(5,e);
                        st.setString(6,f);

                        k=st.execute();
                    
                } 
               catch (SQLException ex) {
                        System.out.println(ex);
                    }
        return k;
    }
    
    public static boolean delete(HashMap a){
        boolean k=false;
        String z="";
       
        try{
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_user","root","");
                    System.out.println("Connected");
                    
                    for (Object val : a.values()){
                        System.out.println(val.toString().replaceAll("[\\[\\],]",""));
                        z= "Delete from data where ID ='" + val.toString().replaceAll("[\\[\\],]","") + "'";
                        PreparedStatement ps= conn.prepareStatement(z);
                        ps.executeUpdate();
                    }
       
                } 
               catch (SQLException ex) {
                        System.out.println(ex);
                    }
        return k;
    }
    
    /**
     *
     * @return
     */
    public ArrayList sales(){
        ArrayList<View> arr = new ArrayList<>(Arrays.asList());
       
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_user","root","");
            System.out.println("db connected");
            String q= "select * from sale";
            PreparedStatement pst= conn.prepareStatement(q);
            ResultSet rs= pst.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("Name"));
                arr.addAll(FXCollections.observableArrayList(new View(rs.getString("Name"), rs.getString("Brand"), rs.getString("TotalSales"), rs.getString("Revenue"))));
            }
            
          }catch (SQLException ex) {
             System.out.println(ex);}
          
             return arr;
        }
   }
