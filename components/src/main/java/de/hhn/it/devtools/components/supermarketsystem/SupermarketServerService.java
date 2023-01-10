package de.hhn.it.devtools.components.supermarketsystem;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.supermarketsystem.Bill;
import de.hhn.it.devtools.apis.supermarketsystem.BillEntry;
import de.hhn.it.devtools.apis.supermarketsystem.PosSystemListener;
import de.hhn.it.devtools.apis.supermarketsystem.PosSystemService;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the PosSystemService interface.
 */
public class SupermarketServerService implements PosSystemService {

  private static final org.slf4j.Logger logger
      = LoggerFactory.getLogger(SupermarketServerService.class);
  private final HashMap<Integer, Product> products;
  private final List<PosSystemListener> listeners;
  private final Bill bill;

  /**
   * Constructor for the SupermarketServerService.
   */
  public SupermarketServerService() {
    logger.info("Constructor\n");
    this.products = new HashMap<>();
    this.bill = new BillImpl();
    this.listeners = new ArrayList<>();
  }

  /**
   * Adds a list of products to the service.
   *
   * @param productList List of products
   */
  public void addProducts(List<Product> productList) throws IllegalParameterException {
    logger.info("addProducts: ", productList + "\n");
    if (productList.isEmpty()) {
      throw new IllegalParameterException("Given productList is empty");
    }

    for (Product product : productList) {
      products.put(product.getId(), product);
    }
  }

  @Override
  public Map<Integer, Product> getProducts() {
    logger.info("getProducts");
    return products;
  }

  @Override
  public Bill getBill() {
    logger.info("getBill");
    return bill;
  }

  @Override
  public Map<Integer, Product> getProductsFromBill() {
    logger.info("getProductsFromBill\n");
    return bill.getProducts();
  }

  @Override
  public Product getProduct(int id) throws IllegalParameterException {

    logger.info("getProduct: ", id);

    if (!products.containsKey(id)) {
      throw new IllegalParameterException("Product with id '" + id + "' not found");
    }

    return products.get(id);
  }

  @Override
  public void addCallback(PosSystemListener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (listeners.contains(listener)) {
      throw new IllegalParameterException("Listener already registered.");
    }

    listeners.add(listener);
  }

  @Override
  public void removeCallback(PosSystemListener listener) throws IllegalParameterException {
    if (listener == null) {
      throw new IllegalParameterException("Listener was null reference.");
    }

    if (!listeners.contains(listener)) {
      throw new IllegalParameterException("Listener is not registered:" + listener);
    }

    listeners.remove(listener);
  }

  @Override
  public void addProductToBill(int id) throws IllegalParameterException, IllegalStateException {
    logger.info("addProductToBill: ", id + "\n");
    Product product = getProduct(id);

    if (bill.containsKey(id)) {
      BillEntry entry = bill.getEntry(id);

      entry.addQuantity();

      bill.recalculate();
    } else {
      BillEntry billEntry = new BillEntryImpl(product);

      bill.addBillEntry(id, billEntry);

      bill.recalculate();
    }
  }

  @Override
  public void addProductToBill(int id, int amount)
      throws IllegalParameterException, IllegalStateException, IllegalArgumentException {

    logger.info("addProductToBill: ", id, amount + "\n");

    if (amount < 1) {
      throw new IllegalArgumentException("The given amount is less than one.");
    }

    addProductToBill(id);

    // Reduce amount by once, due to previous deletion of product
    amount = amount - 1;

    BillEntry entry = bill.getEntry(id);

    if (amount > 0) {
      entry.addQuantity(amount);

      bill.recalculate();
    }
  }

  @Override
  public void deleteProductFromBill(int id)
      throws IllegalParameterException, IllegalStateException {
    logger.info("deleteProductFromBill: ", id + "\n");

    // Check if product list contains product id
    if (!products.containsKey(id)) {
      throw new IllegalParameterException("Product not found");
    }

    // Check if bill contains product
    if (!bill.containsKey(id)) {
      throw new IllegalParameterException("Product not found in bill list");
    }

    BillEntry entry = bill.getEntry(id);

    if (entry.getQuantity() > 1) {
      // Reduce quantity by once
      entry.reduceQuantity();

      // Recalculate bill summary price
      bill.recalculate();
    } else {

      // Remove entry from bill
      bill.removeEntry(id);

      bill.recalculate();
    }
  }

  @Override
  public void deleteProductFromBill(int id, int amount)
      throws IllegalParameterException, IllegalStateException, IllegalArgumentException {

    logger.info("deleteProductFromBill: ", id, amount + "\n");

    if (amount < 1) {
      throw new IllegalArgumentException("The given amount is less than one.");
    }

    deleteProductFromBill(id);

    // Reduce amount by once, due to previous deletion of product
    amount = amount - 1;

    BillEntry entry = bill.getEntry(id);

    if (amount > 0) {
      entry.reduceQuantity(amount);

      bill.recalculate();
    }
  }

  public void clearBill() {
    logger.info("clearBill");
    bill.clear();
  }

  @Override
  public float calculateChange(float givenMoney) {
    logger.info("calculateChange: %f", givenMoney);

    if (givenMoney < 0) {
      throw new IllegalArgumentException("The given Change is negative");
    }

    return givenMoney - bill.getSummary();
  }
}
