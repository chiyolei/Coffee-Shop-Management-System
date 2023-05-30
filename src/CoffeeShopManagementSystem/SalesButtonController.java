/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package CoffeeShopManagementSystem;

import static CoffeeShopManagementSystem.EmployeeButtonController.empList;
import java.io.File;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SalesButtonController implements Initializable {

    @FXML
    private Label time;
    @FXML
    private Label date;
    @FXML
    private TableView<salesInfo> salesTable;
    @FXML
    private TableColumn<salesInfo, String> colDate;
    @FXML
    private TableColumn<salesInfo, String> colOrderNo;
    @FXML
    private TableColumn<salesInfo, Integer> colAmount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         DashboardButtonController ds=new DashboardButtonController();
         ds.showDate(date);
         ds.showTime(time);
         
           salesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
          salesList.clear();
          
        try {
            salesList=getAllSales();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeButtonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           colDate.setCellValueFactory(new PropertyValueFactory<salesInfo, String>("date"));
            colOrderNo.setCellValueFactory(new PropertyValueFactory<salesInfo, String>("orderNo"));
            colAmount.setCellValueFactory(new PropertyValueFactory<salesInfo, Integer>("amount"));
          
            salesTable.setItems(salesList);
          

        // TODO
    }    

   static ObservableList<salesInfo> salesList = FXCollections.observableArrayList();
   
   ObservableList<salesInfo> getAllSales() throws SQLException {
        
        ObservableList<salesInfo> salesList=FXCollections.observableArrayList();
        SignInController s=new SignInController();
        Connection conn=s.getConnection();
        Statement statement = conn.createStatement();

        String query = "select * from salesInfo";
        ResultSet rs = statement.executeQuery(query);
             while (rs.next()) {
            String date=rs.getString("date");
            String orderNo=rs.getString("orderNo"); 
            int amount=rs.getInt("orderAmount");
       
            salesInfo sale=new salesInfo(date,orderNo,amount);
            salesList.add(sale);
        }
        return salesList;
    }
    
}
