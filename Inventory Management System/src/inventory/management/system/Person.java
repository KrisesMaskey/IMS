/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management.system;

import Sale_System.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

/**
 *
 * @author Krises Maskey
 */

 public class Person {
        
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
        private final SimpleStringProperty first;
        private final SimpleStringProperty last;
        private final SimpleStringProperty em;
 
        public Person(String fName, String lName, String email, String first, String last, String em) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
            this.first = new SimpleStringProperty(first);
            this.last = new SimpleStringProperty(last);
            this.em = new SimpleStringProperty(em);
            
        }
 
        public String getFirstName() {
            return firstName.get();
        }
 
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
 
        public String getLastName() {
            return lastName.get();
        }
 
        public void setLastName(String fName) {
            lastName.set(fName);
        }
 
        public String getEmail() {
            return email.get();
        }
 
        public void setEmail(String fName) {
            email.set(fName);
        }
        public String getFirst() {
            return first.get();
        }
 
        public void setFirst(String fName) {
            first.set(fName);
        }
 
        public String getLast() {
            return last.get();
        }
 
        public void setLast(String fName) {
            last.set(fName);
        }
 
        public String getEm() {
            return em.get();
        }
 
        public void setEm(String fName) {
            em.set(fName);
        }
        public ArrayList<View> getAll(HashMap<String, List<String>> ids, HashMap<String,Integer> values){
            
            ArrayList<View> all = new ArrayList<>(Arrays.asList());
            boolean flag = flatContains(ids.values(), firstName.get().toString());
          
            if(!values.containsKey(String.valueOf(lastName.get()))){
                values.put(String.valueOf(lastName.get()), 1);
            }else if(!flag){
                values.replace(String.valueOf(lastName.get()), values.get(String.valueOf(lastName.get())) + 1);
                
            }
            if (!flag){
                if(values.containsKey(lastName.get().toString())){
                    
                }
                all.addAll(FXCollections.observableArrayList(new View(String.valueOf(lastName.get()), String.valueOf(email.get()), String.valueOf(values.get(lastName.get())), String.valueOf(Double.parseDouble(last.get()) * values.get(lastName.get())))));
                ids.putIfAbsent(String.valueOf(lastName.get()), new ArrayList<String>());
                ids.get(String.valueOf(lastName.get())).add(String.valueOf(firstName.get()));
            }
            
            //System.out.println(values);
            return all;
        }
        
        //CUSTOM CONTAINS CLASS FOR COMPARING LIST AND STRING///
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
        ////////////////////////////////////////////////////
    
 }    

