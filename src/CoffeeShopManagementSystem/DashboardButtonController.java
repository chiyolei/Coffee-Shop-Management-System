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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.chart.PieChart;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DashboardButtonController implements Initializable {

    @FXML
    private AnchorPane sceneChange_dashboard;
    @FXML
    private Label showtodaysOrder;
    @FXML
    private Label showTodaysRevenue;
    private Label showWeeklyRevenue;
    private Label showMonthlyRevenue;
    @FXML
    private BarChart<String, Integer> WeeklyRevenueStaticsGraph;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label date;
    @FXML
    private Label time;
    
     private volatile boolean stop=false;

public static String showDate;
    @FXML
    private Label numberOfEmployee;
     
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTime(time);
        showDate(date);

        try {
            todaysOrder();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardButtonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            todaysRevenue();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardButtonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            numberOfEmployee();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardButtonController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

     

        XYChart.Series<String, Integer> month = new XYChart.Series();
        month.setName("Coffee");

        month.getData().add(new XYChart.Data("Cappuchino", 1000));
        month.getData().add(new XYChart.Data("Latte", 2000));
        month.getData().add(new XYChart.Data("Mocha", 400));
        month.getData().add(new XYChart.Data("Cocoa", 6000));
        month.getData().add(new XYChart.Data("Espresso", 2000));
        month.getData().add(new XYChart.Data("Glace", 300));
        WeeklyRevenueStaticsGraph.getData().addAll(month);
        

        ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList(
                new PieChart.Data("Coffee", 55), 
                new PieChart.Data("Cold Drinks", 10),
                new PieChart.Data("Snacks", 20),
                new PieChart.Data("Tea", 15)
        );
        
        pieChart.setData(piechartData);
    }
    
 

    
    
    public void showTime(Label time)
    {
        
        Thread thread=new Thread(()->{
         SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");
         while(!stop){
             try{
                 Thread.sleep(100);
             } catch(Exception e){
                 System.out.println(e);
             }
             final String timenow=sdf.format(new Date());
             Platform.runLater(()->{
                 time.setText(timenow);
             });
         }
     });
     thread.start();
    }
    
    public void showDate(Label date)
    { 
        this.showDate=showDate;
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMM,yyyy");
         showDate=sdf.format(new Date());
        date.setText(showDate);
    }
    
    public void todaysOrder() throws SQLException{
        
        
        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();
        System.out.println("Connection successful");

        String query = "select COUNT(orderNo) from  salesinfo;";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {

            int todayOrder = rs.getInt("count(orderNo)");
          
            showtodaysOrder.setText("" + todayOrder);

        }
    }
        
   
    public void todaysRevenue() throws SQLException{
        
        
        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();
        System.out.println("Connection successful");

         String query = "select sum(orderamount) from salesinfo";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {

            int total = rs.getInt("sum(orderamount)");
          
            showTodaysRevenue.setText("BDT " + total);

        }
    }
    
    
    public void numberOfEmployee() throws SQLException
    {
           
        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();
        System.out.println("Connection successful");
        
      
        String query = "select COUNT(ID) from  allemployeeinfo;";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {

            int total = rs.getInt("count(ID)");
          
            numberOfEmployee.setText(""+total);

        }
        
        
    }
          

}



