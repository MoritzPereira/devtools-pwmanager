package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.pwmanager.exceptions.IllegalMasterPasswordException;
import de.hhn.it.devtools.javafx.Main;
import de.hhn.it.devtools.javafx.controllers.template.ScreenController;
import de.hhn.it.devtools.components.pwmanager.provider.SimplePwManagerService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PwManagerHomeScreenController extends Controller implements Initializable {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(PwManagerHomeScreenController.class);
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

        pwManagerService.login(firstScreenPasswordField.getText());
        System.out.println("logged in");
    }


    public PwManagerHomeScreenController() {
        logger.debug("PwManagerHomeScreen Controller created. Hey, if you have copied me, update this message!");
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {


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

}
