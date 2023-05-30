/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package CoffeeShopManagementSystem;

import static CoffeeShopManagementSystem.EmployeeButtonController.empList;
import static CoffeeShopManagementSystem.FoodMenuButtonController.foodList;
import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class OrderButtonController implements Initializable {

    @FXML
    private Label time;
    @FXML
    private Label date;

    int count = 0;
    @FXML
    private TableView<menuTable> MenuTable;
    @FXML
    private TableColumn<menuTable, String> colID;
    @FXML
    private TableColumn<menuTable, String> colName;
    @FXML
    private TableColumn<menuTable, ImageView> colImage;
    @FXML
    private TableColumn<menuTable, Integer> colPrice;
    @FXML
    private TextField productID;
    @FXML
    private JFXButton removeButtonAction;
    @FXML
    private TableView<orderTable> cartTable;
    @FXML
    private Label orderNo;
    @FXML
    private Label totalAmount;
    @FXML
    private TableColumn<orderTable, String> colOrderID;
    @FXML
    private TableColumn<orderTable, String> colOrderName;
    @FXML
    private TableColumn<orderTable, Integer> colOrderQuantity;
    @FXML
    private TableColumn<orderTable, Integer> colOrderPrice;

    String id = "";
    String name = "";
    int quantity = 1;
    int price;
    String searchID = "";

    String orderID = "";
    String orderName = "";
    int orderQuantity = 0;
    int orderPrice;

    int initial_price = 0;
    int total_price = 0;

    String orderNO = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DashboardButtonController ds = new DashboardButtonController();
        ds.showDate(date);
        ds.showTime(time);

        MenuTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        foodList.clear();

        cartTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        orderList.clear();

        try {
            foodList = getAllFood();
        } catch (SQLException ex) {
            Logger.getLogger(FoodMenuButtonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FoodMenuButtonController.class.getName()).log(Level.SEVERE, null, ex);
        }

        colID.setCellValueFactory(new PropertyValueFactory<menuTable, String>("productid"));
        colName.setCellValueFactory(new PropertyValueFactory<menuTable, String>("productname"));
        colImage.setCellValueFactory(new PropertyValueFactory<menuTable, ImageView>("productImage"));
        colPrice.setCellValueFactory(new PropertyValueFactory<menuTable, Integer>("productPrice"));

        MenuTable.setItems(foodList);

        colOrderID.setCellValueFactory(new PropertyValueFactory<orderTable, String>("id"));
        colOrderName.setCellValueFactory(new PropertyValueFactory<orderTable, String>("name"));
        colOrderQuantity.setCellValueFactory(new PropertyValueFactory<orderTable, Integer>("quantity"));
        colOrderPrice.setCellValueFactory(new PropertyValueFactory<orderTable, Integer>("price"));

        cartTable.setItems(orderList);

    }
    static ObservableList<menuTable> foodList = FXCollections.observableArrayList();
    static ObservableList<orderTable> orderList = FXCollections.observableArrayList();

    @FXML
    private void increaseButtonAction(ActionEvent event) throws SQLException, IOException {

        if (cartTable.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        orderID = "";
        orderName = "";
        orderQuantity = 0;

        orderTable ot = cartTable.getSelectionModel().getSelectedItem();
        menuTable mt = MenuTable.getSelectionModel().getSelectedItem();

        ObservableList<orderTable> selectedFoods = FXCollections.observableArrayList();
        selectedFoods = cartTable.getSelectionModel().getSelectedItems();

        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();

        System.out.println("Connection successful");

      
            String query = "select * from Allfoodmenu where productID='" + mt.getProductid() + "'";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                initial_price = rs.getInt("productPrice");

            }
        

        System.out.println(initial_price);

        for (orderTable food : selectedFoods) {
             query = "select * from orderinfo where ID='" + ot.getId() + "'";

            ResultSet rs1 = statement.executeQuery(query);
            while (rs1.next()) {
                orderID = rs1.getString("ID");
                orderName = rs1.getString("name");
                orderQuantity = rs1.getInt("quantity");
                orderPrice = rs1.getInt("price");

            }

        }

        orderList.removeAll(selectedFoods);
        orderTable foods = new orderTable(orderID, orderName, orderQuantity + 1, (orderPrice + initial_price));
        orderList.addAll(foods);

         query = "delete from orderinfo where ID='" + ot.getId() + "'";
        int status = statement.executeUpdate(query);

        query = "insert into orderInfo (ID,name,quantity,price)values(?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, orderID);
        pst.setString(2, orderName);
        pst.setInt(3, orderQuantity += 1);
        pst.setInt(4, orderPrice += initial_price);

        status = pst.executeUpdate();
        cartTable.getSelectionModel().clearSelection();

    }

    @FXML
    private void decreaseButotnAction(ActionEvent event) throws SQLException {

        if (orderQuantity <= 0) {
            return;
        }

        orderID = "";
        orderName = "";
        orderQuantity = 0;

        orderTable ot = cartTable.getSelectionModel().getSelectedItem();
        menuTable mt = MenuTable.getSelectionModel().getSelectedItem();

        ObservableList<orderTable> selectedFoods = FXCollections.observableArrayList();
        selectedFoods = cartTable.getSelectionModel().getSelectedItems();

        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();
        System.out.println("Connection successful");

     
            String query = "select * from Allfoodmenu where productID='" + mt.getProductid() + "'";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                initial_price = rs.getInt("productPrice");
            }
        

        System.out.println(initial_price);

        for (orderTable food : selectedFoods) {
            query = "select * from orderinfo where ID='" + ot.getId() + "'";

            ResultSet rs1 = statement.executeQuery(query);
            while (rs1.next()) {
                orderID = rs1.getString("ID");
                orderName = rs1.getString("name");
                orderQuantity = rs1.getInt("quantity");
                orderPrice = rs1.getInt("price");

            }

        }

        orderList.removeAll(selectedFoods);
        orderTable foods = new orderTable(orderID, orderName, orderQuantity - 1, (orderPrice - initial_price));
        orderList.addAll(foods);

         query = "delete from orderinfo where ID='" + ot.getId() + "'";
        int status = statement.executeUpdate(query);

        query = "insert into orderInfo (ID,name,quantity,price)values(?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, orderID);
        pst.setString(2, orderName);
        pst.setInt(3, orderQuantity -= 1);
        pst.setInt(4, orderPrice -= initial_price);

        status = pst.executeUpdate();
        cartTable.getSelectionModel().clearSelection();

    }

    @FXML
    private void addButtonAction(ActionEvent event) throws SQLException {

        if (MenuTable.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        quantity = 1;
        String str = productID.getText();
        if (str.equals("")) {
            ObservableList<menuTable> selectedFoods = FXCollections.observableArrayList();
            selectedFoods = MenuTable.getSelectionModel().getSelectedItems();

            SignInController s = new SignInController();
            Connection conn = s.getConnection();
            Statement statement = conn.createStatement();
            System.out.println("Connection successful");

            for (menuTable food : selectedFoods) {
                id = food.getProductid();
                name = food.getProductname();
                price = food.getProductPrice();
            }
            System.out.println(id);
            System.out.println(name);
            System.out.println(quantity);
            System.out.println(price);

            String query = "insert into orderInfo (ID,name,quantity,price)values(?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setInt(3, quantity);
            pst.setInt(4, price);

            int status = pst.executeUpdate();

            orderTable order = new orderTable(id, name, quantity, price);
            orderList.addAll(order);

        } else {
            productID.clear();

            SignInController s = new SignInController();
            Connection conn = s.getConnection();
            Statement statement = conn.createStatement();
            System.out.println("Connection successful");

            String query = "select * from allfoodmenu where productID='" + str + "'";
            ResultSet rs = statement.executeQuery(query);

            rs = statement.executeQuery(query);
            while (rs.next()) {

                searchID = rs.getString("productID");
                name = rs.getString("productName");
                price = rs.getInt("productPrice");

            }

            query = "insert into orderInfo (ID,name,quantity,price)values(?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setInt(3, quantity);
            pst.setInt(4, price);

            int status = pst.executeUpdate();

            orderTable order = new orderTable(id, name, quantity, price);
            orderList.addAll(order);
        }

    }

    @FXML
    private void payButtonAction(ActionEvent event) throws SQLException {

        orderList.clear();
        productID.clear();

        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();
        System.out.println("Connection successful");

        String query = "insert into salesInfo (date,orderNo,orderAmount)values(?,?,?)";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, DashboardButtonController.showDate);
        pst.setString(2, orderNO);
        pst.setInt(3, total_price);
        int status = pst.executeUpdate();

        query = "truncate TABLE orderinfo";
        status = statement.executeUpdate(query);

    }

//    private void invoiceButtonAction(ActionEvent event) {
//       
//    }

    @FXML
    private void removeButtonAction(ActionEvent event) throws SQLException {
        orderList.clear();

        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();
        System.out.println("Connection successful");

        String query = "truncate TABLE orderinfo";
        int status = statement.executeUpdate(query);

    }

    @FXML
    private void proceedButtonAction(ActionEvent event) throws SQLException {

        Random rand = new Random();
        int i = rand.nextInt(1000);
        orderNO = "OCF-" + i;

        orderNo.setText("OCF-" + i);

        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();
        System.out.println("Connection successful");

        String query = "select sum(price) from orderinfo";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {

            total_price = rs.getInt("sum(price)");
            totalAmount.setText("" + total_price);

        }
        
      

        orderList.clear();
        productID.clear();

        System.out.println("Connection successful");

        query = "truncate TABLE orderinfo";
        int status = statement.executeUpdate(query);

    }

    @FXML
    private void clearButtonAction(ActionEvent event) {

        orderList.clear();
        productID.clear();
    }

    ObservableList<orderTable> getOrderedFood() throws SQLException, IOException {

        ObservableList<orderTable> orderList = FXCollections.observableArrayList();
        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();

        String query = "select * from orderInfo";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            String ID = rs.getString("ID");
            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");
            int price = rs.getInt("price");

            orderTable food = new orderTable(ID, name, quantity, price);
            orderList.add(food);
        }
        return orderList;

    }

    ObservableList<menuTable> getAllFood() throws SQLException, IOException {

        ObservableList<menuTable> foodList = FXCollections.observableArrayList();
        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();

        String query = "select * from AllFoodmenu";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            String ID = rs.getString("productID");
            String name = rs.getString("productname");
            Blob blob = rs.getBlob("productImage");

            InputStream in = blob.getBinaryStream();
            BufferedImage imagen = ImageIO.read(in);
            Image img = SwingFXUtils.toFXImage(imagen, null);

            ImageView productimage = new ImageView();
            productimage.setImage(img);
            productimage.setFitWidth(150);
            productimage.setFitHeight(100);
            int productPrice = rs.getInt("productPrice");

            menuTable food = new menuTable(ID, name, productimage, productPrice);
            foodList.add(food);
        }
        return foodList;

    }

}
