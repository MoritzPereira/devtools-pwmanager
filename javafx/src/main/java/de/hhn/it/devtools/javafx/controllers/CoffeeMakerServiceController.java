package de.hhn.it.devtools.javafx.controllers;

import de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerDescriptor;
import de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
import de.hhn.it.devtools.javafx.controllers.coffeemaker.CoffeeMakerController;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;



public class CoffeeMakerServiceController extends Controller implements Initializable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(CoffeeMakerServiceController.class);
  @FXML
  ListView<CoffeeMakerDescriptor> makerListView;
  @FXML
  AnchorPane controlAnchorPane;
  Node actualControlAnchorPaneNode;
  Label pleaseSelectLabel;
  List<CoffeeMakerDescriptor> descriptors;
  ObservableList<CoffeeMakerDescriptor> observableDescriptors;
  CoffeeMakerDescriptor actualMaker;
  private CoffeeMakerService coffeeMakerService;
  private Map<Integer, CoffeeMakerController> id2CoffeeMakerControllerMap;

  public CoffeeMakerServiceController() {
    WnckCoffeeMakerService wnckCoffeeMakerService = new WnckCoffeeMakerService();
    AdminCoffeeMakerService adminCoffeeMakerService = wnckCoffeeMakerService;
    coffeeMakerService = wnckCoffeeMakerService;
    observableDescriptors = FXCollections.observableArrayList();
    id2CoffeeMakerControllerMap = new HashMap<>();

    pleaseSelectLabel = new Label("Please select a coffeMaker");

    try {
      CoffeeMakerDescriptor descriptor1 = new CoffeeMakerDescriptor("A106", "Senseo muddy brown");
      adminCoffeeMakerService.addCoffeeMaker(descriptor1);
      CoffeeMakerDescriptor descriptor2 = new CoffeeMakerDescriptor("F229", "Senseo dirty grey");
      adminCoffeeMakerService.addCoffeeMaker(descriptor2);
      CoffeeMakerDescriptor descriptor3 = new CoffeeMakerDescriptor("A317", "Nespresso basics");
      adminCoffeeMakerService.addCoffeeMaker(descriptor3);
      descriptors = coffeeMakerService.getCoffeeMakers();

      descriptors.forEach((d) -> observableDescriptors.add(d));

    } catch (IllegalParameterException e) {
      e.printStackTrace();
    }
  }

  private CoffeeMakerController getCoffeeMakerController(CoffeeMakerDescriptor descriptor) {
    if (!id2CoffeeMakerControllerMap.containsKey(descriptor.getId())) {
      id2CoffeeMakerControllerMap.put(descriptor.getId(),
              new CoffeeMakerController(coffeeMakerService, descriptor));
    }
    return id2CoffeeMakerControllerMap.get(descriptor.getId());
  }

  /**
   * Called to initialize a controller after its root element has been
   * completely processed.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  <code>null</code> if the location is not known.
   * @param resources The resources used to localize the root object, or <code>null</code> if
   */
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    makerListView.setItems(observableDescriptors);
    makerListView.getSelectionModel().selectedItemProperty().
            addListener((observable, oldValue, newValue) -> switchCoffeeMaker(oldValue, newValue));
    makerListView.setCellFactory((list) -> new CoffeeMakerCell());

    actualControlAnchorPaneNode = pleaseSelectLabel;
    controlAnchorPane.getChildren().add(pleaseSelectLabel);
  }

  private void switchCoffeeMaker(final CoffeeMakerDescriptor oldValue, final CoffeeMakerDescriptor newValue) {
    assert newValue != null;
    assert newValue != oldValue;

    controlAnchorPane.getChildren().clear();
    actualMaker = newValue;
    CoffeeMakerController actualCoffeeMakerController = getCoffeeMakerController(newValue);
    actualControlAnchorPaneNode = actualCoffeeMakerController;
    controlAnchorPane.getChildren().add(actualCoffeeMakerController);

  }

  private void raiseExceptionToUI(final Exception e, final String header) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(header);
    alert.setContentText(e.getMessage());
    alert.showAndWait();
  }

  @Override
  void pause() {
    logger.debug("pause: -");
  }

  @Override
  void resume() {
    logger.debug("resume: -");
  }

  private class CoffeeMakerCell extends ListCell<CoffeeMakerDescriptor> {
    @Override
    protected void updateItem(final CoffeeMakerDescriptor item, final boolean empty) {
      super.updateItem(item, empty);
      Label label = new Label();
      if (item != null) {
        label.setText(item.getLocation() + " : " + item.getModel());
      }
      setGraphic(label);

    }
  }
}
