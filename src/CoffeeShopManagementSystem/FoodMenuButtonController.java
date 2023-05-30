/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package CoffeeShopManagementSystem;

import static CoffeeShopManagementSystem.EmployeeButtonController.empList;
import java.sql.Blob;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FoodMenuButtonController implements Initializable {

    @FXML
    private Label time;
    @FXML
    private Label date;
    @FXML
    private TextField productID;
    @FXML
    private TextField productName;
    @FXML
    private TextField productPrice;
    @FXML
    private ChoiceBox<String> productType;

    private String[] foodcat = {"Coffee", "Light Snacks", "Sandwiches", "Muffins", "Fruit", "Pastries"};
    @FXML
    private ImageView selectImage;
    @FXML
    private TableView<AllFoodMenuInfo> FoodAddTable;

    @FXML
    private TableColumn<AllFoodMenuInfo, String> colID;
    @FXML
    private TableColumn<AllFoodMenuInfo, String> colName;
    @FXML
    private TableColumn<AllFoodMenuInfo, String> colType;
    @FXML
    private TableColumn<AllFoodMenuInfo, Integer> colPrice;
    @FXML
    private TableColumn<AllFoodMenuInfo, ImageView> colImage;

    private FileInputStream fis;
    FileChooser filechoose;
    private File file;

    @FXML
    private Label addedSuccess;
    @FXML
    private TextField deleteProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DashboardButtonController ds = new DashboardButtonController();
        ds.showDate(date);
        ds.showTime(time);
        productType.getItems().addAll(foodcat);

        filechoose = new FileChooser();

        filechoose.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG"), new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG"));

        FoodAddTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        foodList.clear();

        try {
            foodList = getAllFood();
        } catch (SQLException ex) {
            Logger.getLogger(FoodMenuButtonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FoodMenuButtonController.class.getName()).log(Level.SEVERE, null, ex);
        }

        colID.setCellValueFactory(new PropertyValueFactory<AllFoodMenuInfo, String>("productid"));
        colName.setCellValueFactory(new PropertyValueFactory<AllFoodMenuInfo, String>("productname"));
        colType.setCellValueFactory(new PropertyValueFactory<AllFoodMenuInfo, String>("producttype"));
        colPrice.setCellValueFactory(new PropertyValueFactory<AllFoodMenuInfo, Integer>("productprice"));
        colImage.setCellValueFactory(new PropertyValueFactory<AllFoodMenuInfo, ImageView>("productImage"));

        FoodAddTable.setItems(foodList);

    }

    static ObservableList<AllFoodMenuInfo> foodList = FXCollections.observableArrayList();

    @FXML
    private void ChooseProductIMG(ActionEvent event) throws IOException {

        file = filechoose.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.getAbsoluteFile().toURI().toString(), selectImage.getFitWidth(), selectImage.getFitHeight(), true, true);
            selectImage.setImage(image);
            selectImage.setPreserveRatio(true);
        }
    }

    @FXML
    private void AddProductButtonAction(ActionEvent event) throws SQLException, FileNotFoundException, IOException {

        String productid = productID.getText();
        String productname = productName.getText();
        String getPrice = productPrice.getText();
        int productprice = parseInt(getPrice);

        String producttype = productType.getValue();

        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();
        System.out.println("Connection successful");

        String query = "insert into AllFoodMenu (productID,productName,productPrice,ProductType,ProductImage)values(?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, productid);
        pst.setString(2, productname);
        pst.setInt(3, productprice);
        pst.setString(4, producttype);

        fis = new FileInputStream(file);
        pst.setBlob(5, fis);

        Image image = selectImage.getImage();
        ImageView productImage = new ImageView();
        productImage.setImage(image);
        productImage.setFitWidth(150);
        productImage.setFitHeight(100);

        int status = pst.executeUpdate();
        if (status == 1) {
            addedSuccess.setText("Added successfully");

        }

        AllFoodMenuInfo food = new AllFoodMenuInfo(productid, productname, productprice, producttype, productImage);
        foodList.add(food);

        productID.clear();
        productName.clear();
        productPrice.clear();
        productType.setValue(null);
        selectImage.setImage(null);
    }

    @FXML
    private void DeleteProductAction(ActionEvent event) throws SQLException {

        String strDelete = deleteProduct.getText();
        if (strDelete.equals("")) {
            ObservableList<AllFoodMenuInfo> selectedFoods = FXCollections.observableArrayList();
            selectedFoods = FoodAddTable.getSelectionModel().getSelectedItems();

            SignInController s = new SignInController();
            Connection conn = s.getConnection();
            Statement statement = conn.createStatement();
            System.out.println("Connection successful");
            int status = 0;
            for (AllFoodMenuInfo food : selectedFoods) {
                String query = "delete from allfoodmenu where productID='" + food.getProductid() + "'";

                status = statement.executeUpdate(query);

            }

            if (status == 1) {
                addedSuccess.setText("Deleted SuccessFully");
            }

            foodList.removeAll(selectedFoods);
        } else {

            SignInController s = new SignInController();
            Connection conn = s.getConnection();
            Statement statement = conn.createStatement();
            System.out.println("Connection successful");

            String query = "delete from allfoodmenu where productID='" + strDelete + "'";

            int status = statement.executeUpdate(query);
            if (status == 1) {
                addedSuccess.setText("Deleted SuccessFully");
            }

        }
    }

    @FXML
    private void clearButtonAction(ActionEvent event) {

        productID.clear();
        productName.clear();
        productPrice.clear();
        productType.setValue(null);
        selectImage.setImage(null);
    }

    ObservableList<AllFoodMenuInfo> getAllFood() throws SQLException, IOException {

        ObservableList<AllFoodMenuInfo> foodList = FXCollections.observableArrayList();
        SignInController s = new SignInController();
        Connection conn = s.getConnection();
        Statement statement = conn.createStatement();

        String query = "select * from AllFoodmenu";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            String ID = rs.getString("productID");
            String name = rs.getString("productname");
            int price = rs.getInt("productPrice");
            String type = rs.getString("productType");

            Blob blob = rs.getBlob("productImage");

            InputStream in = blob.getBinaryStream();
            BufferedImage imagen = ImageIO.read(in);
            Image img = SwingFXUtils.toFXImage(imagen, null);

            ImageView productimage = new ImageView();
            productimage.setImage(img);
            productimage.setFitWidth(150);
            productimage.setFitHeight(100);

            AllFoodMenuInfo food = new AllFoodMenuInfo(ID, name, price, type, productimage);
            foodList.add(food);
        }
        return foodList;

    }

}
