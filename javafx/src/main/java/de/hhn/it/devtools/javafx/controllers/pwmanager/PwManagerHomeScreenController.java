package de.hhn.it.devtools.javafx.controllers.pwmanager;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;
import de.hhn.it.devtools.components.pwmanager.provider.SimplePwManagerService;
import de.hhn.it.devtools.javafx.Main;
import de.hhn.it.devtools.javafx.controllers.Controller;
import de.hhn.it.devtools.javafx.controllers.template.ScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
        TextField rpasswordText = new TextField();
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

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == loginButton){
                try {
                    pwManagerService.addEntry(urlText.getText(), usernameText.getText(), emailText.getText(), passwordText.getText());
                } catch (IllegalParameterException e) {
                    System.out.println("wdw");
                }
            }
            return null;
        });

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
