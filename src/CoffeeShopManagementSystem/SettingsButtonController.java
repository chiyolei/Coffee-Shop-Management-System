/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package CoffeeShopManagementSystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SettingsButtonController implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private TextField userEmail;
    @FXML
    private TextField userPhone;
    @FXML
    private PasswordField oldPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmNewPassword;
    @FXML
    private Label showStatus;

    String name;
    String phone;
    String email;
    @FXML
    private Label showUsername;
    @FXML
    private Label success;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUsername.setText(SignInController.str1);
        try {
            showInfo();
        } catch (SQLException ex) {
            Logger.getLogger(SettingsButtonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateButtonAction(ActionEvent event) throws SQLException {

        String name = userName.getText();
        String email = userEmail.getText();
        String phone = userPhone.getText();

        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();

        if (SignInController.loginType == "Employee") {

            String query = "update employeelogininfo set name='" + name + "',email='" + email + "',phone='" + phone + "'where username='" + SignInController.str1 + "'";

            int status = statement.executeUpdate(query);
            if (status == 1) {
                success.setText("Updated Successfully");
            } else {
                System.out.println("not success");
            }
        } else if (SignInController.loginType == "Admin") {
            String query = "update adminlogininfo set name='" + name + "',email='" + email + "',phone='" + phone + "'where username='" + SignInController.str1 + "'";

            int status = statement.executeUpdate(query);
            if (status == 1) {
                success.setText("Updated Successfully");
            } else {
                System.out.println("not success");
            }

        }

    }

    @FXML
    private void clearButtonAction(ActionEvent event) {

        userName.clear();
        userEmail.clear();
        userPhone.clear();

    }

    @FXML
    private void saveButtonAction(ActionEvent event) throws SQLException {

        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();

        String oldPass = oldPassword.getText();
        String newPass = newPassword.getText();
        String conNewpass = confirmNewPassword.getText();

        if (SignInController.loginType == "Admin") {

            if (newPass.equals(conNewpass)) {
                System.out.println("Success");
                String query = "update adminlogininfo set password='" + newPass + "'where username='" + SignInController.str1 + "'and password='" + oldPass + "'";

                int status = statement.executeUpdate(query);

                if (status == 1) {
                    success.setText("Password Changed Successfully");

                } else {
                    success.setText("Invalid information");
                }

            } else {
                success.setText("Password Don't Match");
            }

        }
        else if(SignInController.loginType == "Employee")
        {
             if (newPass.equals(conNewpass)) {
                System.out.println("Success");
                String query = "update employeelogininfo set password='" + newPass + "'where username='" + SignInController.str1 + "'and password='" + oldPass + "'";

                int status = statement.executeUpdate(query);

                if (status == 1) {
                    success.setText("Password Changed Successfully");

                } else {
                    success.setText("Invalid information");
                }

            } else {
                success.setText("Password Don't Match");
            }
        }
    }

    @FXML
    private void passClearButtonAction(ActionEvent event) {
        oldPassword.clear();
        newPassword.clear();
        confirmNewPassword.clear();
    }

    void showInfo() throws SQLException {

        if (SignInController.loginType == "Employee") {
            SignInController s = new SignInController();
            Connection conn = s.getConnection();
            Statement statement = conn.createStatement();

            String query = "select * from employeeloginInfo where username='" + SignInController.str1 + "'";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("name");
                email = rs.getString("email");
                phone = rs.getString("phone");

            }

            userName.setText(name);
            userEmail.setText(email);
            userPhone.setText(phone);

        } else if (SignInController.loginType == "Admin") {
            SignInController s = new SignInController();
            Connection conn = s.getConnection();
            Statement statement = conn.createStatement();

            String query = "select * from adminloginInfo where username='" + SignInController.str1 + "'";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("name");
                email = rs.getString("email");
                phone = rs.getString("phone");

            }

            userName.setText(name);
            userEmail.setText(email);
            userPhone.setText(phone);

        }

    }
}
