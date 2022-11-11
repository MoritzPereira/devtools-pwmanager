package de.hhn.it.devtools.components.provider.supermarketsystem;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.supermarketsystem.Bill;
import de.hhn.it.devtools.apis.supermarketsystem.BillEntry;
import de.hhn.it.devtools.apis.supermarketsystem.PosSystemListener;
import de.hhn.it.devtools.apis.supermarketsystem.PosSystemService;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import de.hhn.it.devtools.apis.supermarketsystem.ProductCategory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the PosSystemService interface.
 */
public class SupermarketServerService implements PosSystemService {

  private final HashMap<Integer, Product> products;
  private final List<PosSystemListener> listeners;
  private final Bill bill;

  /** Constructor for the SupermarketServerService.
   *
   */
  SupermarketServerService() {
    this.products = new HashMap<>();
    this.bill = new Bill();
    this.listeners = new ArrayList<>();
  }

  /** Adds a product to the product list.
   *
   * @param id id of the product
   * @param name name of the product
   * @param price price of the product
   * @param quantity quantity of the product
   * @param manufacturer manufacturer of the product
   * @param category category of the product
   */
  public void addProduct(int id, String name, float price,
                         int quantity, String manufacturer, ProductCategory category) {
    Product product = new Product(name, price, quantity);

    if (!manufacturer.isBlank()) {
      product.setManufacturer(manufacturer);
    }

    if (category != null) {
      product.setCategory(category);
    }

    products.put(id, product);
  }

  @Override
  public Map<Integer, Product> getProducts() {
    return products;
  }

  /** Returns the bill.
   *
   * @return bill
   */
  public Bill getBill() {
    return bill;
  }

  @Override
  public Map<Integer, Product> getProductsFromBill() {
    return bill.getProducts();
  }

  @Override
  public Product getProduct(int id) throws IllegalParameterException {

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
    Product product = getProduct(id);

    if (bill.containsKey(id)) {
      BillEntry entry = bill.getEntry(id);

      entry.addQuantity();

      bill.recalculate();
    } else {
      BillEntry billEntry = new BillEntry(product);

      bill.addBillEntry(id, billEntry);
    }
  }

  @Override
  public void deleteProductFromBill(int id)
      throws IllegalParameterException, IllegalStateException {

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
    }
  }

  @Override
  public float sumUpPrices() {
    return bill.getSummary();
  }

  @Override
  public float calculateChange(float givenMoney) {
    return givenMoney - bill.getSummary();
  }
}
