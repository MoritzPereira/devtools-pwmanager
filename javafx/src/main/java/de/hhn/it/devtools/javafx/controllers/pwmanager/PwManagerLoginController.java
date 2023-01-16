package de.hhn.it.devtools.javafx.controllers.pwmanager;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.pwmanager.Entry;
import de.hhn.it.devtools.apis.pwmanager.PwManagerListener;
import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;
import de.hhn.it.devtools.javafx.Main;
import de.hhn.it.devtools.components.pwmanager.provider.SimplePwManagerService;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import de.hhn.it.devtools.javafx.controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PwManagerLoginController extends Controller implements Initializable {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(PwManagerLoginController.class);
    private SimplePwManagerService pwManagerService = new SimplePwManagerService();

    @FXML
    private AnchorPane controlAnchorPane;

    @FXML
    private Button firstScreenLoginButton;

    @FXML
    private PasswordField firstScreenPasswordField;

    @FXML
    void buttonClicked(MouseEvent event) throws IllegalMasterPasswordException {

        login();

    }

    @FXML
    void keyPressed(KeyEvent event) {

        if(event.getCode() == KeyCode.ENTER){
            login();
        }

    }

    private void login() {
        String osPath = System.getProperty("user.dir");
        osPath = osPath.replace("components", "");
        osPath = osPath.replace("javafx", "");
        osPath += "/components/src/main/entries.txt";
        File file = new File(osPath);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            try {
                pwManagerService.loginFromLoginController(firstScreenPasswordField.getText(), br.readLine());
            } catch (IllegalMasterPasswordException e) {
            } catch (IOException e) {
                logger.error("Could not interact with file:" + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            logger.error("Could not find file: " + e.getMessage());
        }
    }

    public PwManagerLoginController() throws IllegalParameterException {
        LoginListener loginListener = new LoginListener();
        pwManagerService.addListener(loginListener);
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {}

    public void changeWindow(String name) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/pwmanager/" + name + ".fxml"));
        controlAnchorPane.getChildren().clear();
        //GameScreen gameScreen = new GameScreen(snakeService);
        try {
            controlAnchorPane.getChildren().add(fxmlLoader.load());
            logger.info("Der Screen wurde zu: " + name + " gewechselt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class LoginListener implements PwManagerListener {

        @Override
        public void loggingin(String masterPw) {

        }

        @Override
        public void loggedin() {
            changeWindow("PwManagerHomeScreen");
        }

        @Override
        public void logout() {

        }

        @Override
        public void loggedout() {

        }

        @Override
        public void entryAdded(Entry newEntry) {

        }

        @Override
        public void entryDeleted(Entry currentEntry) {

        }

        @Override
        public void entryChanged(Entry entry) {

        }

        @Override
        public void changePasswordVisibility(int id) {

        }

        @Override
        public void updateEntryListFile(String input) {

        }

        @Override
        public void deleteContentOfFile() {

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
