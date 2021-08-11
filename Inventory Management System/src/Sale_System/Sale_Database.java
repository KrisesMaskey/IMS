/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Krises Maskey
 */
public class Sale_Database {
    public static boolean insert(String name, String lname, String user, String pass){
        ArrayList<String> arr_list = new ArrayList<>();
        boolean k=false;
             try{
                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_user","root","");
                System.out.println("Connected");
                
                String q= "select * from sale";
                PreparedStatement pst= conn.prepareStatement(q);
                ResultSet rs= pst.executeQuery();
                
                rs.beforeFirst();
                while (rs.next()){
                 arr_list.add(rs.getString("Name"));
                }
                
                if(arr_list.contains(name)){
                    ArrayList<String> sale = new ArrayList<>();
                    ArrayList<String> rev = new ArrayList<>();
                    String str = "select * from sale where Name= '"+ name + "'";
                    PreparedStatement stmt= conn.prepareStatement(str);
                    ResultSet set= stmt.executeQuery();
                    
                    set.beforeFirst();
                    while (set.next()){
                        
                        sale.add(set.getString("TotalSales"));
                        rev.add(set.getString("Revenue"));
                    }
                    
                    String  ql = "update sale set TotalSales= ? , Revenue= ? where Name= '" + name + "'";
                    PreparedStatement p = conn.prepareStatement(ql);

                    p.setString (1, String.valueOf(Double.parseDouble(user) + Double.parseDouble(sale.get(0))));
                    p.setString(2, String.valueOf(Double.parseDouble(pass) + Double.parseDouble(rev.get(0))));
                    
                    int a = p.executeUpdate();
                  
                    p.close();
                    conn.close(); 
                }else{
                    PreparedStatement st= conn.prepareStatement("Insert into sale(Name,Brand,TotalSales, Revenue) Values (?,?,?,?)");
                    System.out.println(name + lname + user + pass);
                    st.setString(1,name);
                    st.setString(2,lname);
                    st.setString(3,user);
                    st.setString(4,pass);

                    k=st.execute();
                    conn.close(); 
                }
                
             }catch(SQLException ex) {
                throw new IllegalStateException("Cannot connect the database!", ex);
                }
              
     return k;
    } 
} 
    
    