package CoffeeShopManagementSystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EmployeeButtonController implements Initializable {

    @FXML
    private Label time;
    @FXML
    private Label date;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField empName;
    @FXML
    private TextField empEmail;

    @FXML
    private TextField empPhone;
    @FXML
    private TextField empAddress;

    @FXML
    private ChoiceBox<String> empcatagory;

    private String[] empcat = {"Attendent", "Server", "Worker", "Cook"};
    private TextField empID;

    @FXML
    private ChoiceBox<String> bloodGroup;
    private String[] bloodtype = {"A+", "B+", "O+", "A-", "B-", "O-", "AB+", "AB-"};
    @FXML
    private TableView<AllEmployeeInfo> EmpInfo;
    @FXML
    private TableColumn<AllEmployeeInfo, String> colID;
    @FXML
    private TableColumn<AllEmployeeInfo, String> colName;
    @FXML
    private TableColumn<AllEmployeeInfo, String> colEmail;
    @FXML
    private TableColumn<AllEmployeeInfo, String> colPhone;
    @FXML
    private TableColumn<AllEmployeeInfo, String> colAddress;
    @FXML
    private TableColumn<AllEmployeeInfo, String> colCatagory;
    @FXML
    private TableColumn<AllEmployeeInfo, String> colBloodGroup;
    @FXML
    private TableColumn<AllEmployeeInfo, String> colJoiningDate;
    @FXML
    private Label addedSuccess;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empcatagory.getItems().addAll(empcat);
        bloodGroup.getItems().addAll(bloodtype);
        DashboardButtonController ds = new DashboardButtonController();
        ds.showDate(date);
        ds.showTime(time);
        
        
          EmpInfo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
          empList.clear();
          
        try {
            empList=getAllEmployee();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeButtonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            colID.setCellValueFactory(new PropertyValueFactory<AllEmployeeInfo, String>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<AllEmployeeInfo, String>("name"));
            colEmail.setCellValueFactory(new PropertyValueFactory<AllEmployeeInfo, String>("email"));
            colPhone.setCellValueFactory(new PropertyValueFactory<AllEmployeeInfo, String>("phone"));
            colAddress.setCellValueFactory(new PropertyValueFactory<AllEmployeeInfo,String>("address"));
            colCatagory.setCellValueFactory(new PropertyValueFactory<AllEmployeeInfo, String>("catagory"));
            colBloodGroup.setCellValueFactory(new PropertyValueFactory<AllEmployeeInfo,String>("bloodGroup"));
            colJoiningDate.setCellValueFactory(new PropertyValueFactory<AllEmployeeInfo,String>("joiningDate"));

            EmpInfo.setItems(empList);
          

    }
     static ObservableList<AllEmployeeInfo> empList = FXCollections.observableArrayList();
    @FXML
    private void addButtonAction(ActionEvent event) throws SQLException {
        

       
        String name = empName.getText();
        String email = empEmail.getText();
        String phone = empPhone.getText();
        String address = empAddress.getText();
        String catagory = empcatagory.getValue();
        String bloodgroup = bloodGroup.getValue();
        LocalDate date = datePicker.getValue();
        String joiningdate = date.toString();
         String ID = "CF-"+name.substring(0,5)+"-"+phone.substring(0,4);
        
                     ///////   ERROR     ////////
//        if(ID.contains("")&&name.contains("")&&email.contains("")&&phone.contains("")&&address.contains("")&&catagory.contains("")&&bloodgroup.contains("")&&joiningdate.contains(""))
//             addedSuccess.setText("Please make sure all fields are filled in correctly");

        
        
          AllEmployeeInfo emp=new AllEmployeeInfo(ID,name,email,phone,address,catagory,bloodgroup,joiningdate);
          empList.add(emp);
          
           SignInController s=new SignInController();
           Connection conn=s.getConnection();
           Statement statement = conn.createStatement();
           System.out.println("Connection successful");
           
    
        
             String query="insert into AllEmployeeInfo (ID,name,email,phone,address,catagory,bloodgroup,JoiningDate)values(?,?,?,?,?,?,?,?)";
             PreparedStatement pst=conn.prepareStatement(query);
             pst.setString(1,ID);
             pst.setString(2,name);
             pst.setString(3,email);
             pst.setString(4,phone);
             pst.setString(5,address);
             pst.setString(6,catagory);
             pst.setString(7,bloodgroup);
             pst.setString(8,joiningdate);
             
             int status=pst.executeUpdate();
             
             if(status==1)
             {
                 addedSuccess.setText("Added successfully");
                 
             }
           
             
         
          
          
          
          
          
     
        empName.clear();
        empEmail.clear();
        empPhone.clear();
        empAddress.clear();
        empcatagory.setValue(null);
        bloodGroup.setValue(null);
        datePicker.setValue(null);

    }

    @FXML
    private void clearButtonAction(ActionEvent event) {

    
        empName.clear();
        empEmail.clear();
        empPhone.clear();
        empAddress.clear();
        empcatagory.setValue(null);
        bloodGroup.setValue(null);
        datePicker.setValue(null);
         addedSuccess.setText("");
    }
    
    ObservableList<AllEmployeeInfo> getAllEmployee() throws SQLException {
        
        ObservableList<AllEmployeeInfo> empList=FXCollections.observableArrayList();
        SignInController s=new SignInController();
        Connection conn=s.getConnection();
        Statement statement = conn.createStatement();

        String query = "select * from AllEmployeeInfo";
        ResultSet rs = statement.executeQuery(query);
             while (rs.next()) {
            String ID=rs.getString("ID");
            String name=rs.getString("name"); 
            String email=rs.getString("email");
            String phone=rs.getString("phone");
            String address=rs.getString("address");
            String catagory=rs.getString("catagory"); 
            String bloodgroup=rs.getString("bloodgroup");
            String joiningdate=rs.getString("joiningdate");
            AllEmployeeInfo employee=new AllEmployeeInfo(ID,name,email,phone,address,catagory,bloodgroup,joiningdate);
            empList.add(employee);
        }
        return empList;
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) throws SQLException {
        
        ObservableList<AllEmployeeInfo> selectedEmployees=FXCollections.observableArrayList();
         selectedEmployees=EmpInfo.getSelectionModel().getSelectedItems();
         
          SignInController s=new SignInController();
          Connection conn=s.getConnection();
        Statement statement = conn.createStatement();
        System.out.println("Connection successful");
                 
        for(AllEmployeeInfo employee:selectedEmployees)
        {
             String query="delete from allemployeeinfo where ID='"+employee.getId()+"'"; 
           
             statement.executeUpdate(query);
            
          
        }
        
        empList.removeAll(selectedEmployees);
    }
    
  
    

}
