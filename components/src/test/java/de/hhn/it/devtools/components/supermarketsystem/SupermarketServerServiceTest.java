package de.hhn.it.devtools.components.supermarketsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.supermarketsystem.PosSystemListener;
import de.hhn.it.devtools.apis.supermarketsystem.PosSystemState;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SupermarketServerServiceTest {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SupermarketServerServiceTest.class);

  private SupermarketServerService supermarketServerService;

  @BeforeEach
  @DisplayName("Clears the product list of the bill before each test.")
  public void setup() {
    supermarketServerService.clearBill();
  }

  @BeforeAll
  @DisplayName("Adds Products to System for Test.")
  public void addProductsToSystem() {
    supermarketServerService = new SupermarketServerService();

    try {
      List<Product> productList = ProductGenerator.getSampleProductList(false);
      supermarketServerService.addProducts(productList);

      logger.info("All products added to the store");
    } catch (Exception exception) {
      logger.warn(exception.getMessage());
    }
  }

  @Test
  @DisplayName("Get Product list.")
  public void checkProductList() {
    assertDoesNotThrow(() -> supermarketServerService.getProducts());
  }

  @Test
  @DisplayName("Product from bill compared with product with same id.")
  public void checkIfProductFromBillEqualsProduct() throws IllegalParameterException {
    supermarketServerService.addProductToBill(1);

    assertEquals(supermarketServerService.getBill().getEntry(1).getProduct(),
        supermarketServerService.getProduct(1));
  }

  @Test
  @DisplayName("Try to add products to bill.")
  public void checkIfProductIsAddedToBill() throws IllegalParameterException {
    int previousBillSize = supermarketServerService.getBill().getList().size();

    supermarketServerService.addProductToBill(1);

    assertTrue(previousBillSize < supermarketServerService.getBill().getList().size());
    assertNotNull(supermarketServerService.getBill().getEntry(1));
  }

  @Test
  @DisplayName("Try to add a none existing Product to bill.")
  public void addToBillWithNonExistingProduct() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.addProductToBill(26));
  }

  @Test
  @DisplayName("Adds a second product with the same id to the bill.")
  public void addSameIdToBill() throws IllegalParameterException {
    supermarketServerService.addProductToBill(1);

    assertDoesNotThrow(() -> supermarketServerService.addProductToBill(1));

    assertEquals(supermarketServerService.getBill().getEntry(1).getQuantity(), 2);
  }

  @Test
  @DisplayName("Removes a second product with the same id to the bill.")
  public void removeSameIdFromBill() throws IllegalParameterException {
    supermarketServerService.addProductToBill(1);
    supermarketServerService.addProductToBill(1);

    assertDoesNotThrow(() -> supermarketServerService.deleteProductFromBill(1));

    assertEquals(supermarketServerService.getBill().getEntry(1).getQuantity(), 1);
  }

  @Test
  @DisplayName("Try to add a product with a negative id to bill.")
  public void addProductWithNegativeId() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.addProductToBill(-1));
  }

  @Test
  @DisplayName("Try to get a none existing product.")
  public void addProductWithNonExistingProduct() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.getProduct(200));
  }

  @Test
  @DisplayName("Try to add a product with amount")
  public void addProductWithAmount() {
    assertDoesNotThrow(() -> supermarketServerService.addProductToBill(1, 3));

    assertEquals(3, supermarketServerService.getBill().getEntry(1).getQuantity());
  }

  @Test
  @DisplayName("Try to add a product with an invalid amount")
  public void addProductWithInvalidAmount() {
    assertThrows(IllegalArgumentException.class,
        () -> supermarketServerService.addProductToBill(1, -1));

    // Product should not be added to bill
    assertNull(supermarketServerService.getBill().getEntry(1));
  }

  @Test
  @DisplayName("Try to get a product with a negative id.")
  public void getProductWithNegativeProductId() {
    assertThrows(IllegalParameterException.class, () -> supermarketServerService.getProduct(-1));
  }

  @Test
  @DisplayName("Check if Bill is empty.")
  public void checkIfBillIsEmpty() {
    assertTrue(supermarketServerService.getBill().getList().isEmpty());
  }

  @Test
  @DisplayName("Check if Bill has values.")
  public void checkIfBillHasValues() throws IllegalParameterException {
    supermarketServerService.addProductToBill(1);
    assertFalse(supermarketServerService.getBill().getList().isEmpty());
  }

  @Test
  @DisplayName("Check if Products in bill are empty.")
  public void getProductsFromBillCheckIsEmpty() {
    assertTrue(supermarketServerService.getProductsFromBill().isEmpty());
  }

  @Test
  @DisplayName("Adds products to bill and checks if bill has values.")
  public void checkIfProductsFromBillHasValues() throws IllegalParameterException {
    supermarketServerService.addProductToBill(1);

    assertTrue(supermarketServerService.getProductsFromBill().size() > 0);
    assertNotNull(supermarketServerService.getProductsFromBill());
  }

  @Test
  @DisplayName("Return products from bill should not throw exception.")
  public void checkProductsFromBillNotThrowsException() {
    assertDoesNotThrow(() -> supermarketServerService.getProductsFromBill());
  }

  @Test
  @DisplayName("Delete a none existing product from the bill.")
  public void deleteProductFromBillWithNonExistingProduct() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.deleteProductFromBill(26));
  }

  @Test
  @DisplayName("Delete a product from the bill with negative id.")
  public void deleteProductFromBillWithNegativeProductId() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.deleteProductFromBill(-2));
  }

  @Test
  @DisplayName("Delete a product that is not in bill.")
  public void deleteProductFromBillWithProductNotInBill() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.deleteProductFromBill(1));
  }

  @Test
  @DisplayName("Try to delete a product twice from the bill.")
  public void deleteProductFromBillTwice() throws IllegalParameterException {
    final int productId = 3;

    supermarketServerService.addProductToBill(productId);
    supermarketServerService.deleteProductFromBill(productId);

    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.deleteProductFromBill(productId));
  }

  @Test
  @DisplayName("Try to delte a product with amount")
  public void deleteProductWithAmount() throws IllegalParameterException {
    final int productId = 1;

    supermarketServerService.addProductToBill(productId, 5);

    assertDoesNotThrow(() -> supermarketServerService.deleteProductFromBill(productId, 3));

    assertEquals(2, supermarketServerService.getBill().getEntry(productId).getQuantity());
  }

  @Test
  @DisplayName("Try to add a product with an invalid amount")
  public void deleteProductWithInvalidAmount() throws IllegalParameterException {
    final int productId = 3;

    supermarketServerService.addProductToBill(productId, 2);

    assertThrows(IllegalArgumentException.class,
        () -> supermarketServerService.deleteProductFromBill(productId, -1));

    // Product quantity should be unchanged
    assertEquals(2, supermarketServerService.getBill().getEntry(productId).getQuantity());
  }

  @Test
  @DisplayName("Calculate change with empty bill.")
  public void checkChangeEqualsGivenMoney() {
    assertEquals(500, supermarketServerService.calculateChange(500),
        "Bill is empty so full amount returned");
  }

  @Test
  @DisplayName("Tries to calculate change and compares to correct amount.")
  public void checkChangeEqualsCorrectAmount() throws IllegalParameterException {
    supermarketServerService.addProductToBill(1);

    Product product = supermarketServerService.getProduct(1);

    final float givenMoney = 30F;
    final float correctChange = givenMoney - product.getPrice();

    assertEquals(correctChange,
        supermarketServerService.calculateChange(givenMoney));
  }

  @Test
  @DisplayName("Calculates change with not enough given money.")
  public void checkChangeHowMuchIsLeft () throws IllegalParameterException {
    supermarketServerService.addProductToBill(1);

    Product product = supermarketServerService.getProduct(1);

    final float givenMoney = 1F;
    final float correctChange = givenMoney - product.getPrice();

    assertEquals(correctChange,
        supermarketServerService.calculateChange(givenMoney));
  }

  @Test
  @DisplayName("Register a listener, get states, remove listeners.")
  public void checkRegisterAndRemoveCallback() throws IllegalParameterException {
    SimplePosSystemListener listener = new SimplePosSystemListener();

    supermarketServerService.addCallback(listener);
    supermarketServerService.addProductToBill(1);
    supermarketServerService.removeCallback(listener);

    assertEquals(0, listener.states.size());
  }

  @Test
  @DisplayName("You cannot register the same listener twice.")
  public void addCallbackRegisterListenerTwice() throws IllegalParameterException {
    SimplePosSystemListener listener = new SimplePosSystemListener();
    supermarketServerService.addCallback(listener);

    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.addCallback(listener));
  }

  @Test
  @DisplayName("You cannot remove a listener which is not registered.")
  public void addCallbackRemoveListenerWhichIsNotRegistered() {
    SimplePosSystemListener listener = new SimplePosSystemListener();

    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.removeCallback(listener));
  }

  @Test
  @DisplayName("You cannot register a null reference as listener.")
  public void addCallbackRegisterWithNullReferenceAsListener() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.addCallback(null));
  }

  @Test
  @DisplayName("You cannot remove a null reference as listener.")
  public void removeCallbackWithNullReferenceAsListener() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.removeCallback(null));
  }

  /**
   * Inner class as a CoffeeMakerListener.
   */
  static class SimplePosSystemListener implements PosSystemListener {

    public List<PosSystemState> states;

    public SimplePosSystemListener() {
      states = new ArrayList<>();
    }

    @Override
    public void newState(PosSystemState state) {
      states.add(state);
    }
  }
}