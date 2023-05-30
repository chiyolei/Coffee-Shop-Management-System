/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package CoffeeShopManagementSystem;

import static CoffeeShopManagementSystem.MainFX.primaryStage;
import static CoffeeShopManagementSystem.SignInController.stage;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DashBoardController implements Initializable {

    @FXML
    private Label showUsername;
    private JFXButton signOutButton;
    @FXML
    private JFXButton signOutBtn;

    @FXML
    private VBox mainContent;
    @FXML
    private Label logintype;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUsername.setText(" "+SignInController.str1);
        if (SignInController.loginType == "Admin") {
            logintype.setText("Admin");

            try {
                Parent dashboard = FXMLLoader.load(getClass().getResource("DashBoardButton.fxml"));
                mainContent.getChildren().removeAll();
                mainContent.getChildren().setAll(dashboard);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (SignInController.loginType == "Employee") {
             logintype.setText("Employee");

            try {
                Parent dashboard = FXMLLoader.load(getClass().getResource("OrderButton.fxml"));
                mainContent.getChildren().removeAll();
                mainContent.getChildren().setAll(dashboard);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void dashboardButtonAction(ActionEvent event) throws IOException {
        if (SignInController.loginType == "Admin") {
            Parent dashboard = FXMLLoader.load(getClass().getResource("DashBoardButton.fxml"));
            mainContent.getChildren().setAll(dashboard);
        } else {
            Parent dashboard = FXMLLoader.load(getClass().getResource("OnlyAdminCanAccessMessage.fxml"));
            mainContent.getChildren().setAll(dashboard);
        }

    }

    @FXML
    private void employeeButtonAction(ActionEvent event) throws IOException {
        if (SignInController.loginType == "Admin") {
            Parent employee = FXMLLoader.load(getClass().getResource("EmployeeButton.fxml"));
            mainContent.getChildren().setAll(employee);
        } else {
            Parent dashboard = FXMLLoader.load(getClass().getResource("OnlyAdminCanAccessMessage.fxml"));
            mainContent.getChildren().setAll(dashboard);
        }
    }

    @FXML
    private void salesButtonAction(ActionEvent event) throws IOException {
          if (SignInController.loginType == "Admin") {
        Parent sales = FXMLLoader.load(getClass().getResource("SalesButton.fxml"));
        mainContent.getChildren().setAll(sales);
          }
          else {
            Parent dashboard = FXMLLoader.load(getClass().getResource("OnlyAdminCanAccessMessage.fxml"));
            mainContent.getChildren().setAll(dashboard);
          }

    }

    @FXML
    private void orderButtonAction(ActionEvent event) throws IOException {

        Parent order = FXMLLoader.load(getClass().getResource("OrderButton.fxml"));
        mainContent.getChildren().setAll(order);
    }

    @FXML
    private void FoodMenuButtonAction(ActionEvent event) throws IOException {

        Parent foodmenu = FXMLLoader.load(getClass().getResource("FoodMenuButton.fxml"));
        mainContent.getChildren().removeAll();
        mainContent.getChildren().setAll(foodmenu);

    }



    @FXML
    private void signOutButtonAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Sign Out");
        alert.setHeaderText("You are about to sign out");

        if (alert.showAndWait().get() == ButtonType.OK) {
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            MainFX.primaryStage.show();
        }

    }

    @FXML
    private void settingsButtonAction(ActionEvent event) throws IOException {
        
          Parent setting = FXMLLoader.load(getClass().getResource("SettingsButton.fxml"));
        mainContent.getChildren().removeAll();
        mainContent.getChildren().setAll(setting);
        
        
    }

   
}
