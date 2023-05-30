/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package CoffeeShopManagementSystem;

import java.sql.Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SignUPController implements Initializable {

    @FXML
    private ToggleButton loginCustomer;
    @FXML
    private ToggleGroup loginType;
    @FXML
    private ToggleButton loginEmployee;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField pin;
    @FXML
    private Label addedSuccess;
    
    public static String signInType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signUpButtonAction(ActionEvent event) throws SQLException {
        
        String strName=name.getText();
        String strEmail=email.getText();
        String strPhone=phone.getText();
        String strUsername=username.getText();
        String strPassword=password.getText();
        String strPin=pin.getText();
        name.clear();
        email.clear();
        phone.clear();
        username.clear();
        password.clear();
        pin.clear();
        
        
        SignInController s=new SignInController();
        Connection conn=s.getConnection();
        
         if(loginEmployee.isSelected()) {
            this.signInType="Employee";
            
            
            String query="insert into employeelogininfo (name,email,phone,username,password,pin)values(?,?,?,?,?,?)";
             PreparedStatement pst=conn.prepareStatement(query);
             pst.setString(1,strName);
             pst.setString(2,strEmail);
             pst.setString(3,strPhone);
             pst.setString(4,strUsername);
             pst.setString(5,strPassword);
             pst.setString(6,strPin);
             
             int status=pst.executeUpdate();
             
             if(status==1)
             {
                 addedSuccess.setText("Signed up successfully");
                 System.out.println("done");
             }
             
             
             
            
         }
         
         else if(loginCustomer.isSelected()) {
            this.signInType="Customer";
            
         }
         
         else
             addedSuccess.setText("Please make sure all fields \n are filled in correctly");
             
         
         
        
        
        
        
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {
        
        name.clear();
        email.clear();
        phone.clear();
        username.clear();
        password.clear();
        pin.clear();
        
    }
    
}
