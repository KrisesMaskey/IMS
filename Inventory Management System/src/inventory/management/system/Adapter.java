/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.management.system;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Krises Maskey
 */
public class Adapter {
      private final SimpleStringProperty firstName;
      private final SimpleStringProperty lastName;
      private final SimpleStringProperty first;
      private final SimpleStringProperty last;
       private final SimpleStringProperty email;
      
 
        public Adapter(String fName, String lName, String fame, String lame, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.first = new SimpleStringProperty(fame);
            this.last = new SimpleStringProperty(lame);
            this.email = new SimpleStringProperty(email);
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
         public String getEmail() {
            return email.get();
        }
 
        public void setEmail(String fName) {
            email.set(fName);
        }
    
}
