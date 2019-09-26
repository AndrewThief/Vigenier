package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import sample.DatabaseHandler;
import sample.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SignInController {

    @FXML
    private Button signUpButton;

    @FXML
    private TextField loginSignInField;

    @FXML
    private PasswordField passwordSignInField;

    @FXML
    private Button signInButton;

    @FXML
    private Text warningText;

   public void initialize() {

       signUpButton.setOnAction(event -> {
           signUpButton.getScene().getWindow().hide();
           openScene("/sample/View/signUp.fxml");
       });

       signInButton.setOnAction(event -> {

           String loginText = loginSignInField.getText().trim();
           String loginPassword = passwordSignInField.getText().trim();
           warningText.setText("");

           if (!loginText.equals("") && !loginPassword.equals("")) {

               loginUser(loginText, loginPassword);

       }else{
        warningText.setText("Login or password field is empty");}
    });
   }
   public void loginUser(String loginText, String loginPassword){

       DatabaseHandler dbHandler = new DatabaseHandler();
       User user = new User();
       user.setLogin(loginText);
       user.setPassword(loginPassword);
       ResultSet result = dbHandler.getUser(user);

       int counter = 0;
       while (true){
           try {
               if (!result.next()) break;
           } catch (SQLException e) {
               e.printStackTrace();
           }
           counter++;

       }

       if(counter >= 1){
           signInButton.getScene().getWindow().hide();
           openScene("/sample/View/encryption.fxml");
       }else{
           warningText.setText("Error! User is not exist.");
       }
   }
    public void openScene(String window){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}