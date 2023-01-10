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
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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
      List<Product> productList = ProductGenerator.getSampleProductList();

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

  @ParameterizedTest
  @DisplayName("Product from bill compared with product with same id.")
  @ValueSource(ints = {1, 3, 5, 7, 12, 13, 16, 20, 23, 25})
  public void checkIfProductFromBillEqualsProduct(int id) throws IllegalParameterException {
    supermarketServerService.addProductToBill(id);

    assertEquals(supermarketServerService.getBill().getEntry(id).getProduct(),
        supermarketServerService.getProduct(id));
  }

  @ParameterizedTest
  @DisplayName("Try to add products to bill.")
  @ValueSource(ints = {1, 3, 5, 7, 12, 13, 16, 20, 23, 25})
  public void checkIfProductIsAddedToBill(int id) throws IllegalParameterException {
    int previousBillSize = supermarketServerService.getBill().getList().size();

    supermarketServerService.addProductToBill(id);

    assertTrue(previousBillSize < supermarketServerService.getBill().getList().size());
    assertNotNull(supermarketServerService.getBill().getEntry(id));
  }

  @ParameterizedTest
  @DisplayName("Try to add a none existing Product to bill.")
  @ValueSource(ints = {26, 30, 200, Integer.MAX_VALUE})
  public void addToBillWithNonExistingProduct(int id) {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.addProductToBill(id));
  }

  @ParameterizedTest
  @DisplayName("Adds a second product with the same id to the bill.")
  @ValueSource(ints = {1, 3, 5, 7, 12, 13, 16, 20, 23, 25})
  public void addSameIdToBill(int id) throws IllegalParameterException {
    supermarketServerService.addProductToBill(id);

    assertDoesNotThrow(() -> supermarketServerService.addProductToBill(id));

    assertEquals(supermarketServerService.getBill().getEntry(id).getQuantity(), 2);
  }

  @ParameterizedTest
  @DisplayName("Removes a second product with the same id to the bill.")
  @ValueSource(ints = {1, 3, 5, 7, 12, 13, 16, 20, 23, 25})
  public void removeSameIdFromBill(int id) throws IllegalParameterException {
    supermarketServerService.addProductToBill(id);
    supermarketServerService.addProductToBill(id);

    assertDoesNotThrow(() -> supermarketServerService.deleteProductFromBill(id));

    assertEquals(supermarketServerService.getBill().getEntry(id).getQuantity(), 1);
  }

  @ParameterizedTest
  @DisplayName("Try to add a product with a negative id to bill.")
  @ValueSource(ints = {-1, -3, -5, -40, -300, -1000, Integer.MIN_VALUE})
  public void addProductWithNegativeId(int id) {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.addProductToBill(id));
  }

  @ParameterizedTest
  @DisplayName("Try to get a none existing product.")
  @ValueSource(ints = {30, 40, 41, 93, 100, Integer.MAX_VALUE})
  public void addProductWithNonExistingProduct(int id) {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.getProduct(id));
  }

  @ParameterizedTest
  @DisplayName("Try to add a product with amount")
  @ValueSource(ints = {1, 3, 5, 7, 12, 13, 16, 20, 23, 25})
  public void addProductWithAmount(int id) {
    assertDoesNotThrow(() -> supermarketServerService.addProductToBill(id, 3));

    assertEquals(3, supermarketServerService.getBill().getEntry(id).getQuantity());
  }

  @ParameterizedTest
  @DisplayName("Try to add a product with an invalid amount")
  @ValueSource(ints = {1, 3, 5, 7, 12, 13, 16, 20, 23, 25})
  public void addProductWithInvalidAmount(int id) {
    assertThrows(IllegalArgumentException.class,
        () -> supermarketServerService.addProductToBill(id, -1));

    // Product should not be added to bill
    assertNull(supermarketServerService.getBill().getEntry(id));
  }

  @ParameterizedTest
  @DisplayName("Try to get a product with a negative id.")
  @ValueSource(ints = {-1, -3, -5, -40, -300, -1000, Integer.MIN_VALUE})
  public void getProductWithNegativeProductId(int id) {
    assertThrows(IllegalParameterException.class, () -> supermarketServerService.getProduct(id));
  }

  @Test
  @DisplayName("Check if Bill is empty.")
  public void checkIfBillIsEmpty() {
    assertTrue(supermarketServerService.getBill().getList().isEmpty());
  }

  @ParameterizedTest
  @DisplayName("Check if Bill has values.")
  @ValueSource(ints = {1, 3, 5, 7, 12, 13, 16, 20, 23, 25})
  public void checkIfBillHasValues(int id) throws IllegalParameterException {
    supermarketServerService.addProductToBill(id);
    assertFalse(supermarketServerService.getBill().getList().isEmpty());
  }

  @Test
  @DisplayName("Check if Products in bill are empty.")
  public void getProductsFromBillCheckIsEmpty() {
    assertTrue(supermarketServerService.getProductsFromBill().isEmpty());
  }

  @ParameterizedTest
  @DisplayName("Adds products to bill and checks if bill has values.")
  @ValueSource(ints = {1, 3, 5, 7, 12, 13, 16, 20, 23, 25})
  public void checkIfProductsFromBillHasValues(int id) throws IllegalParameterException {
    supermarketServerService.addProductToBill(id);

    assertTrue(supermarketServerService.getProductsFromBill().size() > 0);
    assertNotNull(supermarketServerService.getProductsFromBill());
  }

  @Test
  @DisplayName("Return products from bill should not throw exception.")
  public void checkProductsFromBillNotThrowsException() {
    assertDoesNotThrow(() -> supermarketServerService.getProductsFromBill());
  }

  @ParameterizedTest
  @DisplayName("Delete a none existing product from the bill.")
  @ValueSource(ints = {30, 40, 41, 93, 100, Integer.MAX_VALUE})
  public void deleteProductFromBillWithNonExistingProduct(int id) {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.deleteProductFromBill(id));
  }

  @ParameterizedTest
  @DisplayName("Delete a product from the bill with negative id.")
  @ValueSource(ints = {-1, -3, -5, -40, -300, -1000, Integer.MIN_VALUE})
  public void deleteProductFromBillWithNegativeProductId(int id) {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.deleteProductFromBill(id));
  }

  @ParameterizedTest
  @DisplayName("Delete a product that is not in bill.")
  @ValueSource(ints = {1, 3, 5, 7, 12, 13, 16, 20, 23, 25})
  public void deleteProductFromBillWithProductNotInBill(int id) {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.deleteProductFromBill(id));
  }

  @ParameterizedTest
  @DisplayName("Try to delete a product twice from the bill.")
  @ValueSource(ints = {1, 3, 5, 7, 12, 13, 16, 20, 23, 25})
  public void deleteProductFromBillTwice(int id) throws IllegalParameterException {

    supermarketServerService.addProductToBill(id);
    supermarketServerService.deleteProductFromBill(id);

    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.deleteProductFromBill(id));
  }

  @ParameterizedTest
  @DisplayName("Try to delete a product with amount")
  @MethodSource("provideProductIdsAndAmount")
  public void deleteProductWithAmount(int id, int addAmount, int removeAmount) throws IllegalParameterException {

    supermarketServerService.addProductToBill(id, addAmount);

    assertDoesNotThrow(() -> supermarketServerService.deleteProductFromBill(id, removeAmount));

    assertEquals((addAmount-removeAmount), supermarketServerService.getBill().getEntry(id).getQuantity());
  }

  @ParameterizedTest
  @DisplayName("Try to add a product with an invalid amount")
  @ValueSource(ints = {1, 3, 5, 7, 12, 13, 16, 20, 23, 25})
  public void deleteProductWithInvalidAmount(int id) throws IllegalParameterException {

    supermarketServerService.addProductToBill(id, 2);

    assertThrows(IllegalArgumentException.class,
        () -> supermarketServerService.deleteProductFromBill(id, -1));

    // Product quantity should be unchanged
    assertEquals(2, supermarketServerService.getBill().getEntry(id).getQuantity());
  }

  @ParameterizedTest
  @DisplayName("Calculate change with empty bill.")
  @ValueSource(floats = {20F, 43F, 102F, 200F, 1000F, Float.MAX_VALUE})
  public void checkChangeEqualsGivenMoney(float change) {
    assertEquals(change, supermarketServerService.calculateChange(change),
        "Bill is empty so full amount returned");
  }

  @ParameterizedTest
  @DisplayName("Tries to calculate change and compares to correct amount.")
  @MethodSource("provideProductIdsAndChange")
  public void checkChangeEqualsCorrectAmount(int id, float givenMoney) throws IllegalParameterException {
    supermarketServerService.addProductToBill(id);

    Product product = supermarketServerService.getProduct(id);

    final float correctChange = givenMoney - product.getPrice();

    assertEquals(correctChange,
        supermarketServerService.calculateChange(givenMoney));
  }

  @ParameterizedTest
  @DisplayName("Calculates change with not enough given money.")
  @MethodSource("provideProductIdsAndNegativeChange")
  public void checkChangeWithNegativeChange (int id, float givenMoney) throws IllegalParameterException {
    supermarketServerService.addProductToBill(id);

    assertThrows(IllegalArgumentException.class,
        () -> supermarketServerService.calculateChange(givenMoney));
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

  private static Stream<Arguments> provideProductIdsAndChange() {
    return Stream.of(
      Arguments.of(1, 10F),
        Arguments.of(3, 35F),
        Arguments.of(5, 50F),
        Arguments.of(7, 6F),
        Arguments.of(12, 350F),
        Arguments.of(13, 100F),
        Arguments.of(16, 60F),
        Arguments.of(20, 75F),
        Arguments.of(25, 500F)
    );
  }

  private static Stream<Arguments> provideProductIdsAndNegativeChange() {
    return Stream.of(
        Arguments.of(1, -1F),
        Arguments.of(3, -35F),
        Arguments.of(5, -50F),
        Arguments.of(7, -6F),
        Arguments.of(12, -350F),
        Arguments.of(13, -100F),
        Arguments.of(16, -60F),
        Arguments.of(20, -75F),
        Arguments.of(25, -500F)
    );
  }

  private static Stream<Arguments> provideProductIdsAndAmount() {
    return Stream.of(
        Arguments.of(1, 5, 3),
        Arguments.of(3, 10, 1),
        Arguments.of(5, 8, 7),
        Arguments.of(7, 3, 1),
        Arguments.of(12, 5, 1),
        Arguments.of(13, 15, 6),
        Arguments.of(16, 8, 3),
        Arguments.of(20, 8, 7),
        Arguments.of(25, 20, 3)
    );
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