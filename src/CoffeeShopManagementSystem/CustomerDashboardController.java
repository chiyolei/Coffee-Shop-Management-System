/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package CoffeeShopManagementSystem;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CustomerDashboardController implements Initializable {

    @FXML
    private Label showUsername;
    @FXML
    private VBox mainContent;
    @FXML
    private Label time;
    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DashboardButtonController ds = new DashboardButtonController();
        ds.showDate(date);
        ds.showTime(time);
    }

    @FXML
    private void dashboardButtonAction(ActionEvent event) {
    }

    @FXML
    private void orderButtonAction(ActionEvent event) throws IOException {
        Parent order = FXMLLoader.load(getClass().getResource("OrderButton.fxml"));
        mainContent.getChildren().setAll(order);
    }

    @FXML
    private void messageButtonAction(ActionEvent event) throws IOException {

        Parent message = FXMLLoader.load(getClass().getResource("MessageButton.fxml"));
        mainContent.getChildren().setAll(message);
    }

    @FXML
    private void signOutButtonAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sign Out");
        alert.setHeaderText("You are about to sign out");

        if (alert.showAndWait().get() == ButtonType.OK) {
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            MainFX.primaryStage.show();
        }

    }

}
