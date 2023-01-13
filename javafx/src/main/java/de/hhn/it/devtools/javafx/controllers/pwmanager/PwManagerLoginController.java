package de.hhn.it.devtools.javafx.controllers.pwmanager;

import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;
import de.hhn.it.devtools.javafx.Main;
import de.hhn.it.devtools.components.pwmanager.provider.SimplePwManagerService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.hhn.it.devtools.javafx.controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PwManagerLoginController extends Controller implements Initializable {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(PwManagerLoginController.class);
    public static final String SCREEN_CONTROLLER = "screen.controller";

    private de.hhn.it.devtools.components.pwmanager.provider.SimplePwManagerService pwManagerService = new de.hhn.it.devtools.components.pwmanager.provider.SimplePwManagerService();

    @FXML
    private AnchorPane controlAnchorPane;

    @FXML
    private Button firstScreenLoginButton;

    @FXML
    private PasswordField firstScreenPasswordField;

    @FXML
    void buttonClicked(MouseEvent event) throws IllegalMasterPasswordException {

        try{
            pwManagerService.login(firstScreenPasswordField.getText());
        }
        catch (IllegalMasterPasswordException e){
            logger.info("Wrong password!.");
        }
        if(pwManagerService.loggenIn){
            changeWindow("PwManagerHomeScreen");
        }
    }


    public PwManagerLoginController() {
        logger.debug("PwManagerHomeScreen Controller created. Hey, if you have copied me, update this message!");
        this.pwManagerService = new SimplePwManagerService();
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {



    }

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

}
