
package CoffeeShopManagementSystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MessageButtonController implements Initializable {

   
    @FXML
    private Label showMsg;
    @FXML
    private Label time;
    @FXML
    private Label date;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         DashboardButtonController ds=new DashboardButtonController();
         ds.showDate(date);
         ds.showTime(time);
        String str=("Your Message Will Appear Here");
        showMsg.setText(str);
    }



    
}