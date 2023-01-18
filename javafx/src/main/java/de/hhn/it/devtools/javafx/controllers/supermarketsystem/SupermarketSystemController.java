package de.hhn.it.devtools.javafx.controllers.supermarketsystem;

import de.hhn.it.devtools.apis.supermarketsystem.PosSystemListener;
import de.hhn.it.devtools.apis.supermarketsystem.PosSystemState;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SupermarketSystemController extends AnchorPane implements PosSystemListener {

  @Override
  public void newState(PosSystemState state) {

  }

  //Labels Landing Page
  @FXML
  Label home;

  //Labels Scanning Products
  @FXML
  Label noProductsScanned;
  @FXML
  Label productDeletion;
  @FXML
  Label billDeletion;


  //Buttons Landing Page
  @FXML
  Button scanProductsHome;
  @FXML
  Button productInfoHome;

  //Buttons Scanning Page
  @FXML
  Button payment;
  @FXML
  Button deleteProduct;
  @FXML
  Button productInfo;
  @FXML
  Button amount;
  @FXML
  Button deleteBill;

  //Button home;

  @FXML
  Button confirmProductDeletion;
  @FXML
  Button cancelProductDeletion;
  @FXML
  Button confirmBillDeletion;
  @FXML
  Button cancelBillDeletion;

}
