/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CoffeeShopManagementSystem;

import java.time.LocalDate;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class AllEmployeeInfo {
    
    
     private String id;
     private String name;
     private String email;
     private String phone;
     private String address;
     private String catagory;
     private String bloodGroup;
     private String joiningDate;

    public AllEmployeeInfo() {
    }

    public AllEmployeeInfo(String id, String name, String email, String phone, String address, String catagory, String bloodGroup, String joiningDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.catagory = catagory;
        this.bloodGroup = bloodGroup;
        this.joiningDate = joiningDate;
    }

 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    @Override
    public String toString() {
        return "AllEmployeeInfo{" + "id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address + ", catagory=" + catagory + ", bloodGroup=" + bloodGroup + ", joiningDate=" + joiningDate + '}';
    }

    

   

   
     
     
}
