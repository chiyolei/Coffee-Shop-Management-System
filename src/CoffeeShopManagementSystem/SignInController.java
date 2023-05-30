/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package CoffeeShopManagementSystem;

import java.sql.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SignInController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label wrongCredential;
    
    public static String str1;
    
    public static Stage stage;
  
    @FXML
    private ToggleButton loginAdmin;
    @FXML
    private Label signInType;
    @FXML
    private ToggleButton loginEmployee;
    
   
    
     public static String loginType;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        password.textProperty();
    }    

    
 

    
    
    @FXML
    private void signInButtonAction(ActionEvent event) throws IOException, SQLException {
        
        
        this.str1=str1;
        this.stage=stage;
        this.loginType=loginType;
        str1=username.getText();
        String str2=password.getText();
        username.clear();
        password.clear();
        
       
        Connection conn=getConnection();
        
     
        
        
        if(loginAdmin.isSelected()) {
            loginType="Admin";
            String query="select * from adminlogininfo where username = ? and password = ?";
             PreparedStatement pst=conn.prepareStatement(query);
             
             pst.setString(1, str1);
             pst.setString(2, str2);
             ResultSet rs=pst.executeQuery();
            
        if(rs.next())
        {
            
            Parent dashboard=FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            
            Scene scene=new Scene(dashboard);
            Stage stage=new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            MainFX.primaryStage.close();
            stage.show();
        }
        else
        {
            wrongCredential.setText("Invalid Information");
        }
             
        }
        
        if(loginEmployee.isSelected()) {
            this.loginType="Employee";
             String query="select * from employeelogininfo where username = ? and password = ?";
             PreparedStatement pst=conn.prepareStatement(query);
             
             pst.setString(1, str1);
             pst.setString(2, str2);
             ResultSet rs=pst.executeQuery();
            
        if(rs.next())
        {
            
            Parent dashboard=FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            
            Scene scene=new Scene(dashboard);
            Stage stage=new Stage();
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            MainFX.primaryStage.close();
            stage.show();
        }
        else
        {
            wrongCredential.setText("Invalid Information");
        }
        }
        
       
        
         
            
    }
    

      //database connection

     Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:MySQL://localhost:3306/CoffeeShopManagement", "root", "Coffeepassword");
   
        
        return conn;
    }

    @FXML
    private void forgetPasswordAction(ActionEvent event) throws IOException {
        
           
            Parent dashboard=FXMLLoader.load(getClass().getResource("ForgetPassword.fxml"));
            
            Scene scene=new Scene(dashboard);
            Stage stage=new Stage();
            stage.setTitle("Reset Password");
            stage.setScene(scene);
            stage.show();
        
        
    }

 
}
