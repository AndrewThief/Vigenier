package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import sample.DatabaseHandler;
import sample.User;



public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginSignUpField;


    @FXML
    private Button signUpButton;


    @FXML
    private Button returnToSignInButton;

    @FXML
    private TextField nameSignUpField;

    @FXML
    private TextField lastNameSignUpField;

    @FXML
    private Text warningtext;

    @FXML
    private PasswordField passwordSignUpField1;

    @FXML
    private PasswordField passwordSignUpField2;


    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {
            warningtext.setText("");
            String pass1 = passwordSignUpField1.getText().trim();
            String pass2 = passwordSignUpField2.getText().trim();
            String f1 = loginSignUpField.getText().trim();
            String f2 = nameSignUpField.getText().trim();
            String f3 = lastNameSignUpField.getText().trim();

            System.out.println(pass1+pass2+f1+f2+f3);

            if(confirmPass(pass1,pass2) && confirmFields(pass1,pass2,f1,f2,f3)){
                signUpNewUser();
                signUpButton.getScene().getWindow().hide();

                openScene("/sample/View/encryption.fxml");

            }else if (!confirmPass(pass1,pass2)&&confirmFields(pass1,pass2,f1,f2,f3)&&!pass1.equals("")&&!pass2.equals("")){
                warningtext.setText("Passwords don`t much");
            }else if(!confirmFields(pass1,pass2,f1,f2,f3)){
                warningtext.setText("Fields must not be empty");
            }else{
                warningtext.setText("Error! Check the fields");
            }
        });

        returnToSignInButton.setOnAction(event -> {
                returnToSignInButton.getScene().getWindow().hide();

                openScene("/sample/View/signIn.fxml");
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstName = nameSignUpField.getText();
        String lastName = lastNameSignUpField.getText();
        String login = loginSignUpField.getText();
        String password = passwordSignUpField1.getText();

        User user = new User(firstName, lastName, login, password);

        dbHandler.signUpUser(user);
    }

    private boolean confirmPass(String a, String b){
        if (a.equals(b) && !a.equals("")){
            return true;
        }else{
            return false;
        }
    }
    private boolean confirmFields(String a, String b, String c, String d, String e){
        if(!a.equals("")||!b.equals("")||!c.equals("")||!d.equals("")||!e.equals("")){
           return true;
        }else{
            return false;
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

