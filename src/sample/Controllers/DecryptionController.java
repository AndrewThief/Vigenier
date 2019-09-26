package sample.Controllers;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import sample.Encryption;

public class DecryptionController extends Encryption {

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
    private Text warningText;

    @FXML
    private TextArea informationField;

    @FXML
    private Button decryptButton;

    @FXML
    private TextArea hidedInformationField;

    @FXML

    private TextField theKeyField;
    @FXML
    void initialize() {

        decryptButton.setOnAction(event ->  {

            if( !hidedInformationField.getText().trim().equals("") &&  !theKeyField.getText().trim().equals("")){
                warningText.setText("");

                setHided_information((hidedInformationField.getText().trim()));
                setKey(theKeyField.getText().trim());
                setInformation(decrypt(getHided_information(),getKey()));

                System.out.println(getHided_information() + "  " + decrypt(getHided_information(),getKey()));
                informationField.setText(getInformation());
            }else{
                warningText.setText("The key and information fields must not be empty");
            }
        });
    }
}
