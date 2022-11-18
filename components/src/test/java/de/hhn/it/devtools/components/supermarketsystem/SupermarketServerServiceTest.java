package de.hhn.it.devtools.components.supermarketsystem;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.supermarketsystem.PosSystemListener;
import de.hhn.it.devtools.apis.supermarketsystem.PosSystemState;
import de.hhn.it.devtools.apis.supermarketsystem.ProductCategory;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SupermarketServerServiceTest {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SupermarketServerServiceTest.class);

  private SupermarketServerService supermarketServerService;

  @BeforeAll
  @Description("Adds Products to System for Test")
  public void addProductsToSystem() {
    supermarketServerService = new SupermarketServerService();

    logger.info("Prepare Products");
    try {
      // Read File from System
      Path filePath = Paths.get("./src/test/java/de/hhn/it/devtools/components/supermarketsystem/Products.json");
      Reader reader = Files.newBufferedReader(filePath);
      JsonArray parser = JsonParser.parseReader(reader).getAsJsonArray();

      // Loop through entries
      for(JsonElement jsonElement : parser) {
        // Convert entry to json object
        JsonObject productData = jsonElement.getAsJsonObject();

        /* Get Values from entry */
        int id      = productData.get("id").getAsInt();
        String name = productData.get("name").getAsString();
        float price = productData.get("price").getAsFloat();
        String manufacturer = productData.get("manufacturer").getAsString();
        String category     = productData.get("category").getAsString();
        ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());

        // Add product to service
        supermarketServerService.addProduct(id, name, price, manufacturer, productCategory);

        logger.info("Adding product: " + name);
      }

      logger.info("All products added to the store");
    } catch (IOException ioException) {
      logger.warn(ioException.getMessage());
    }
  }

  @Test
  public void getProduct_CompareTwoProducts() throws IllegalParameterException {
    // get two products with different id
    // assertNotEquals product1, product2  + add throws to method
  }

  @Test
  public void getProduct_InstanceOfProduct() {
    // assertInstanceOf Product -> getProduct (id has to be valid) + add throws to method
  }

  @Test
  public void addProductToBill_CheckIfAddedToBill() throws IllegalParameterException {
    // save size of supermarket bill list

    // addProductToBill + add throws to method

    // assertTrue size before < size of bill now

    // assertNotNull getBill().getEntry with same id
  }

  @Test
  public void addProductToBill_WithNonExistingProduct() {
    // assertThrows  IllegalParameterException -> add Product to Bill id nicht im Bereich von 1 - 25
  }

  @Test
  public void getProduct_WithNonExistingProduct() {
    // assertThrows IllegalParameterException -> getProduct(-1)
  }

  @Test
  public void getProduct_WithExistingProductAndInstanceOf() {
    // assertDoesNotThrow getProduct(1)

    // assertInstanceOf Product -> getProduct(1)
  }

  @Test
  public void getBill_InstanceOfBill() {
    // assertInstanceOf Bill class -> getBill
  }

  @Test
  public void getBill_IsEmpty() {
    // assertTrue -> check if getBill().getList is empty
  }

  @Test
  public void getBill_HasValues() {
    // add products to bill
    // assertTrue -> check if getBill.getList has entries
  }

  @Test
  public void getProductsFromBill_CheckIsEmpty() {
    // assertTrue getProductsFromBill is empty
  }

  @Test
  public void getProductsFromBill_HasValues() {
    // addProductToBill - add throw to method

    // assertTrue getProdcutsFromBill size > 0 or !is empty
  }

  @Test
  public void getProductsFromBill_NotThrowsException() {
    // assertDoesNotThrow -> getProductsFromBill
  }

  @Test
  public void deleteProductToBill_WithNonExistingProduct() {
    // assertThrows IllegalParameterException -> deleteProductFromBill with non-existing product
  }
  @Test
  public void deleteProductFromBill_WithProductNotInBill() {
    // assertThrows IllegalParameterException -> deleteProductFromBill with existing product but not in bill
  }

  @Test
  public void deleteProductFromBill_DeleteTwiceFromBill() {
    // addProductToBill 3

    // assertDoesNotThrow deleteProductFromBill(3)

    // assertThrows IllegalParameterException -> deleteProductFromBill with same id
  }

  @Test
  public void calculateChange_CheckIfChangeEqualsGivenMoney() {
    assertEquals(500, supermarketServerService.calculateChange(500),
        "Bill is empty so full amount returned");
  }

  @Test
  public void calculateChange_CheckIfChangeNotEqualsGivenMoney() throws IllegalParameterException {
    // addProductToBill + add throws to method

    // assertNotEquals 30, calculateChange 30
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
  public void addCallback_RegisterListenerTwice() throws IllegalParameterException {
    SimplePosSystemListener listener = new SimplePosSystemListener();
    supermarketServerService.addCallback(listener);

    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.addCallback(listener));
  }

  @Test
  @DisplayName("You cannot remove a listener which is not registered.")
  public void addCallback_RemoveListenerWhichIsNotRegistered() {
    SimplePosSystemListener listener = new SimplePosSystemListener();

    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.removeCallback(listener));
  }

  @Test
  @DisplayName("You cannot register a null reference as listener")
  public void addCallback_RegisterNullReferenceAsListener() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.addCallback(null));
  }

  @Test
  @DisplayName("You cannot remove a null reference as listener")
  public void removeCallback_RemoveNullReferenceAsListener() {
    assertThrows(IllegalParameterException.class,
        () -> supermarketServerService.removeCallback(null));
  }

  /**
   * Inner class as a CoffeeMakerListener.
   */
  class SimplePosSystemListener implements PosSystemListener {

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