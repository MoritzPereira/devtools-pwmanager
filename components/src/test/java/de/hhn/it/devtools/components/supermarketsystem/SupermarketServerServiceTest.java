package de.hhn.it.devtools.components.supermarketsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.supermarketsystem.Bill;
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
  @DisplayName("Adds Products to System for Test")
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
  @DisplayName("Product from bill compared with product with same id")
  public void CheckIfProductFromBillEqualsProduct() throws IllegalParameterException {
    supermarketServerService.addProductToBill(1);

    assertEquals(supermarketServerService.getBill().getEntry(1).getProduct(),
        supermarketServerService.getProduct(1));
  }

  @Test
  @DisplayName("Add products to bill")
  public void CheckIfProductIsAddedToBill() throws IllegalParameterException {
    int previousBillSize = supermarketServerService.getBill().getList().size();

    supermarketServerService.addProductToBill(1);

    assertTrue(previousBillSize < supermarketServerService.getBill().getList().size());
    assertNotNull(supermarketServerService.getBill().getEntry(1));
  }

  @Test
  @DisplayName("Try to add a none existing Product to bill")
  public void CheckAddToBillWithNonExistingProduct() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.addProductToBill(26));
  }

  @Test
  @DisplayName("Try to add a product with a negative id to bill")
  public void CheckAddToBillWithNegativeProductId() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.addProductToBill(-1));
  }

  @Test
  @DisplayName("Try to get a none existing product")
  public void CheckProductWithNonExistingProduct(){
    assertThrows(IllegalParameterException.class, () -> supermarketServerService.getProduct(200));
  }

  @Test
  @DisplayName("Try to get a product with a negative id")
  public void CheckProductWithNegativeProductId(){
    assertThrows(IllegalParameterException.class, () -> supermarketServerService.getProduct(-1));
  }

  @Test
  public void CheckProductWithExistingProductAndInstanceOf() throws IllegalParameterException{
    assertDoesNotThrow(() -> supermarketServerService.getProduct(1));

    assertInstanceOf(Product.class, supermarketServerService.getProduct(1));
  }

  @Test
  public void CheckBillInstanceOfBill() {
    assertInstanceOf(Bill.class, supermarketServerService.getBill());
  }

  @Test
  public void CheckBillIsEmpty() {
    assertTrue(supermarketServerService.getBill().getList().isEmpty());
  }

  @Test
  public void getBillHasValues() throws IllegalParameterException{
    supermarketServerService.addProductToBill(1);
    assertFalse(supermarketServerService.getBill().getList().isEmpty());
  }

  @Test
  public void getProductsFromBillCheckIsEmpty() {
    assertTrue(supermarketServerService.getProductsFromBill().isEmpty());
  }

  @Test
  public void getProductsFromBillHasValues() throws IllegalParameterException{
    supermarketServerService.addProductToBill(1);

    assertTrue(supermarketServerService.getProductsFromBill().size() > 0);
    assertNotNull(supermarketServerService.getProductsFromBill());
  }

  @Test
  public void getProductsFromBillNotThrowsException()  {
    assertDoesNotThrow(() -> supermarketServerService.getProductsFromBill());
  }

  @Test
  public void deleteProductFromBillWithNonExistingProduct(){
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.deleteProductFromBill(26));
  }
  @Test
  public void deleteProductFromBillWithProductNotInBill(){
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.deleteProductFromBill(1));
  }

  @Test
  public void deleteProductFromBillDeleteTwiceFromBill() throws IllegalParameterException{
    supermarketServerService.addProductToBill(3);
    supermarketServerService.deleteProductFromBill(3);

    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.deleteProductFromBill(3));
  }

  @Test
  @DisplayName("Calculate change with empty bill")
  public void CheckChangeChangeEqualsGivenMoney() {
    assertEquals(500, supermarketServerService.calculateChange(500),
        "Bill is empty so full amount returned");
  }

  @Test
  @DisplayName("Calculate correct change")
  public void CheckChangeEqualsRightAmount() throws IllegalParameterException {
    supermarketServerService.addProductToBill(1);

    Product product = supermarketServerService.getProduct(1);

    final float givenMoney = 30F;
    final float correctChange = givenMoney - product.getPrice();

    assertEquals(correctChange,
        supermarketServerService.calculateChange(givenMoney));
  }

  @Test
  @DisplayName("Register a listener, get states, remove listeners")
  public void registerAndRemoveCallback() throws IllegalParameterException {
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
  @DisplayName("You cannot register a null reference as listener")
  public void addCallbackRegisterNullReferenceAsListener() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.addCallback(null));
  }

  @Test
  @DisplayName("You cannot remove a null reference as listener")
  public void removeCallbackRemoveNullReferenceAsListener() {
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