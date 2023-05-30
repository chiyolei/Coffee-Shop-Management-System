/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package CoffeeShopManagementSystem;

import static CoffeeShopManagementSystem.SignInController.loginType;
import static CoffeeShopManagementSystem.SignInController.str1;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private PasswordField pin;
    @FXML
    private ToggleGroup reset;
    @FXML
    private TextField username;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmNewPassword;
    @FXML
    private ToggleButton loginAdmin;
    @FXML
    private ToggleButton loginEmployee;
    @FXML
    private Label showStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveButtonAction(ActionEvent event) throws SQLException {

        String userName = username.getText();
        String newPass = newPassword.getText();
        String conNewpass = confirmNewPassword.getText();
        String Pin = pin.getText();

        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();

        if (loginAdmin.isSelected()) {

            if (newPass.equals(conNewpass)) {
                System.out.println("Success");
                String query = "update adminlogininfo set password='" + newPass + "'where username='" + userName + "'and pin='" + Pin + "'";

                int status = statement.executeUpdate(query);

                 
               if (status == 1) {
                    showStatus.setText("Password Changed Successfully");

                } else {
                    showStatus.setText("Invalid information");
                }

            }
            else
                showStatus.setText("Password Don't Match");
            


            }
        

        if (loginEmployee.isSelected()) {

            if (newPass.equals(conNewpass)) {
                System.out.println("Success");
                String query = "update employeelogininfo set password='" + newPass + "'where username='" + userName + "'and pin='" + Pin + "'";

                int status = statement.executeUpdate(query);

               if (status == 1) {
                    showStatus.setText("Password Changed Successfully");

                } else {
                    showStatus.setText("Invalid information");
                }

            }
            else
                showStatus.setText("Password Don't Match");
                
        }
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {

        username.clear();
        newPassword.clear();
        confirmNewPassword.clear();
        pin.clear();
        showStatus.setText("");
    }

}
