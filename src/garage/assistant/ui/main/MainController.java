package garage.assistant.ui.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadAddMember(ActionEvent event) {
        loadWindow("/garage/assistant/ui/addmember/member_add.fxml", "Add new Member");
    }

    @FXML
    private void loadAddMotorbike(ActionEvent event) {
        loadWindow("/garage/assistant/ui/addmotorbike/add_motorbike.fxml", "Add new Motorbike");
    }

    @FXML
    private void loadMemberTable(ActionEvent event) {
        loadWindow("/garage/assistant/ui/listmember/member_list.fxml", "All Member");
    }

    @FXML
    private void loadMotorbikeTable(ActionEvent event) {
        loadWindow("/garage/assistant/ui/listmotorbike/motorbike_list.fxml", "All Motorbike");
    }
    
    @FXML
    private void loadMotorbikesTable(ActionEvent event) {
        loadWindow("/garage/assistant/ui/listmotorbike/motorbike_list.fxml", "All Motorbike");
    }
    
    void loadWindow(String lct, String title) {
        try {
            Parent parent  = FXMLLoader.load(getClass().getResource(lct));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent)); //create a scene from the parent
            stage.show();
            
        } catch (IOException ex) { //auto generated by try-catch
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}