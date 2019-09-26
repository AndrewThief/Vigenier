package sample.Controllers;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
 import javafx.stage.Stage;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import sample.Encryption;

public class EncryptionController extends Encryption {

    private String information;
    private String key;
    private String hided_information;

    public String getInformation() {
        return information;
    }
    public String getKey() {
        return key;
    }
    public String getHided_information() { return hided_information;  }

    public void setInformation(String information) {
        this.information = information.toLowerCase();
    }
    public void setKey(String key) {
        this.key = key.toLowerCase();
    }
    public void setHided_information(String hided_information) {
        this.hided_information = hided_information;
    }

    @FXML
    private Button decryptButton;

    @FXML
    private Text warningText;

    @FXML
    private TextArea informationField;

    @FXML
    private Button encryptButton;

    @FXML
    private TextArea hidedInformationField;

    @FXML

    private TextField theKeyField;
    @FXML
    void initialize() {

        encryptButton.setOnAction(event ->  {

            if( !informationField.getText().trim().equals("") &&  !theKeyField.getText().trim().equals("")){
                warningText.setText("");

                setInformation(informationField.getText().trim());
                setKey(theKeyField.getText().trim());
                setHided_information(encrypt(getInformation(),getKey()));

                hidedInformationField.setText(getHided_information());

                decrypt(getHided_information(),getKey());
                System.out.println(getHided_information() + "  " + decrypt(getHided_information(),getKey()));

            }else{
                warningText.setText("The key and information fields must not be empty");
            }
        });
        decryptButton.setOnAction(event -> {

            decryptButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/decryption.fxml"));

            try {
                loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });


    }
}
