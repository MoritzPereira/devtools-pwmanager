package de.hhn.it.devtools.javafx.controllers.pwmanager;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;
import de.hhn.it.devtools.components.pwmanager.provider.SimplePwManagerService;
import de.hhn.it.devtools.javafx.Main;
import de.hhn.it.devtools.javafx.controllers.Controller;
import de.hhn.it.devtools.javafx.controllers.template.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class PwManagerHomeScreenController extends Controller implements Initializable{
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(PwManagerHomeScreenController.class);
    public static final String SCREEN = "pwmanagerhomescreen.controller";

    private SimplePwManagerService pwManagerService = new SimplePwManagerService();

    @FXML
    private Button buttonAddEntry;

    @FXML
    private Button buttonChangeMasterpassword;

    @FXML
    private Button buttonPassword;

    @FXML
    private AnchorPane controlAnchorPane;

    public PwManagerHomeScreenController() throws IllegalParameterException {
        TestListener testListener = new TestListener();
        pwManagerService.addListener(testListener);
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    @FXML
    void buttonClicked(MouseEvent event) {

        if (buttonAddEntry == event.getSource()){
            openContextMenuAddEntry();
        }
        else if(buttonChangeMasterpassword == event.getSource()){
            openContextMenuChangeMasterPW();
        }
        else if(buttonPassword == event.getSource()){
            openContextRandomPassword();
        }

    }

    public void openContextMenuAddEntry(){

        Dialog dialog = new Dialog();
        dialog.setTitle("Add entry");
        dialog.setHeaderText("Adds an new entry to the system");

        ButtonType loginButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));


        TextField usernameText = new TextField();
        usernameText.setPromptText("Username");
        TextField urlText = new TextField();
        urlText.setPromptText("URL");
        TextField emailText = new TextField();
        emailText.setPromptText("E-Mail");
        PasswordField passwordText = new PasswordField();
        passwordText.setPromptText("Password");
        PasswordField rpasswordText = new PasswordField();
        rpasswordText.setPromptText("Repeat password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameText, 1, 0);
        grid.add(new Label("URL:"), 0, 1);
        grid.add(urlText, 1, 1);
        grid.add(new Label("E-Mail:"), 0, 2);
        grid.add(emailText, 1, 2);
        grid.add(new Label("Password:"), 0, 3);
        grid.add(passwordText, 1, 3);
        grid.add(new Label("Repeat password:"), 0, 4);
        grid.add(rpasswordText, 1, 4);

        dialog.getDialogPane().setContent(grid);

        Node loginButtonNode = dialog.getDialogPane().lookupButton(loginButton);
        loginButtonNode.setDisable(false);

        /*dialog.setResultConverter(dialogButton -> {
            if(dialogButton == loginButton){
                try {
                    pwManagerService.addEntry(urlText.getText(), usernameText.getText(), emailText.getText(), passwordText.getText());
                } catch (IllegalParameterException e) {
                    System.out.println("wdw");
                }
            }
            return null;
        });*/


        /*Optional<ButtonType> result = dialog.showAndWait();
        if(!result.isPresent()){

        }
        else if(result.get() == ButtonType.APPLY){
            System.out.println("Test123");
        }
        else if(result.get() == ButtonType.CANCEL){

        }*/
        final Button btOk = (Button) dialog.getDialogPane().lookupButton(loginButton);
        btOk.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    if(Objects.equals(usernameText.getText(), "") || Objects.equals(urlText.getText(), "") || Objects.equals(emailText.getText(), "") || Objects.equals(passwordText.getText(), "") || Objects.equals(rpasswordText.getText(), "")){
                        event.consume();
                        dialog.setHeaderText("Please fill out all fields!");
                    }
                    else if(!Objects.equals(passwordText.getText(), rpasswordText.getText())){
                        event.consume();
                        dialog.setHeaderText("Passwords do not match!");
                    }
                    else{
                        try {
                            System.out.println(urlText.getText());
                            pwManagerService.addEntry(urlText.getText(), usernameText.getText(), emailText.getText(), passwordText.getText());
                        } catch (IllegalParameterException e){
                            dialog.setHeaderText(e.getMessage());
                            event.consume();
                        }
                    }
                }
        );
        dialog.showAndWait();

    }

    public void openContextMenuChangeMasterPW(){

        Dialog dialog = new Dialog();
        dialog.setTitle("Change Masterpassword");
        //dialog.setHeaderText("Adds an new entry to the system");

        ButtonType loginButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));


        PasswordField oldmasterpasswordText = new PasswordField();
        oldmasterpasswordText.setPromptText("old masterpassword");
        PasswordField newmasterpasswordText = new PasswordField();
        newmasterpasswordText.setPromptText("new masterpassword");
        PasswordField rnewmasterpasswordText = new PasswordField();
        rnewmasterpasswordText.setPromptText("new masterpassword");
;

        grid.add(new Label("Old password:"), 0, 0);
        grid.add(oldmasterpasswordText, 1, 0);
        grid.add(new Label("New password:"), 0, 1);
        grid.add(newmasterpasswordText, 1, 1);
        grid.add(new Label("Repeat new password:"), 0, 2);
        grid.add(rnewmasterpasswordText, 1, 2);
        grid.add(new Label(" "), 0, 3);


        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == loginButton){

            }
            return null;
        });

        dialog.showAndWait();

    }

    public void openContextRandomPassword(){

        Dialog dialog = new Dialog();
        dialog.setTitle("Generate password");
        //dialog.setHeaderText("Adds an new entry to the system");

        ButtonType loginButton = new ButtonType("Generate", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label useuppercaseLabel = new Label("Use uppercase");
        Label uselowercaseLabel = new Label("Use lowercase");
        Label usenumbersLabel = new Label("Use numbers");
        Label usespecialsLabel = new Label("Use specials");
        Label numbersLabel = new Label("Numbers:");
        CheckBox useuppercaseBox = new CheckBox();
        CheckBox uselowercaseBox = new CheckBox();
        CheckBox usenumbersBox = new CheckBox();
        CheckBox usespecialsBox = new CheckBox();
        TextField generatedPassword = new TextField("");
        TextField numbersText = new TextField("");
        generatedPassword.setDisable(true);
        Button copiePasswordButton = new Button("Copy");
        Slider slider = new Slider(0,10,5);


        grid.add(generatedPassword, 1,0);
        grid.add(copiePasswordButton, 2,0);
        //grid.add(slider, 0,1);
        grid.add(numbersLabel, 0,1);
        grid.add(numbersText, 1,1);
        grid.add(useuppercaseBox, 0, 2);
        grid.add(useuppercaseLabel, 1, 2);
        grid.add(uselowercaseBox, 0, 3);
        grid.add(uselowercaseLabel, 1, 3);
        grid.add(usenumbersBox, 0, 4);
        grid.add(usenumbersLabel, 1, 4);
        grid.add(usespecialsBox, 0, 5);
        grid.add(usespecialsLabel, 1, 5);

        dialog.getDialogPane().setContent(slider);
        dialog.getDialogPane().setContent(grid);

        final Button btOk = (Button) dialog.getDialogPane().lookupButton(loginButton);
        btOk.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    if(Integer.parseInt(numbersText.getText()) <= 4 ){
                        dialog.setHeaderText("Invalid password length");
                    }
                    else{
                        String password = null;
                        try {
                            password = pwManagerService.generateNewPw(useuppercaseBox.isSelected(), uselowercaseBox.isSelected(), usenumbersBox.isSelected(), usespecialsBox.isSelected(), Integer.parseInt(numbersText.getText()));
                            System.out.println(password);
                        } catch (IllegalParameterException e) {
                            e.printStackTrace();
                        }
                        generatedPassword.setText(password);
                    }
                    event.consume();
                }
        );
        dialog.showAndWait();

    }

    public void changeWindow(String name) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/" + name + ".fxml"));
        controlAnchorPane.getChildren().clear();
        //GameScreen gameScreen = new GameScreen(snakeService);
        try {
            controlAnchorPane.getChildren().add(fxmlLoader.load());
            logger.info("Der Screen wurde zu: " + name + " gewechselt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateUI(){

    }


    class TestListener implements PwManagerListener{

        @Override
        public void loggingin(String masterPw) {

        }

        @Override
        public void loggedin() {

        }

        @Override
        public void logout() {

        }

        @Override
        public void loggedout() {

        }

        @Override
        public void entryAdded(Entry newEntry) {
            System.out.println("Listener: Entry mit der id:["+newEntry.getEntryId()+"] wurde hinzugefügt.");
            updateUI();
        }

        @Override
        public void entryDeleted(Entry currentEntry) {

        }

        @Override
        public void entryChanged(Entry entry) {
            System.out.println("Listener: Entry mit der id:["+entry.getEntryId()+"] wurde verändert.");
        }

        @Override
        public void changePasswordVisibility(int id) {

        }

        @Override
        public void updateEntryList() {

        }

        @Override
        public void generatePw(boolean useUpper, boolean useLower, boolean useDigits, boolean useSpecialChars) {

        }

        @Override
        public void showNewPw(String pw) {

        }

        @Override
        public void showsortedEntryList(ArrayList<Entry> entryList) {

        }
    }

}
