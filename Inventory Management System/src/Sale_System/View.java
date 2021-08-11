/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale_System;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Krises Maskey
 */
public class View {
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
        private final SimpleStringProperty first;
        //private final SimpleStringProperty last;
        //private final SimpleStringProperty em;
 
        public View(String fName, String lName, String email, String first) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
            this.first = new SimpleStringProperty(first);
            //this.last = new SimpleStringProperty(last);
            //this.em = new SimpleStringProperty(em);
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
        /*
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
        }*/
        public ArrayList<String> getAll(){
            ArrayList<String> all = new ArrayList<>();
            all.add(String.valueOf(firstName.get().toString()).concat("\t\t " + lastName.get().toString()).concat("\t\t"+ email.get().toString()).concat("\t\t" + first.get().toString()));
            return all;
        }
    
}
