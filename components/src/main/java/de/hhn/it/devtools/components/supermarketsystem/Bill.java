package de.hhn.it.devtools.components.supermarketsystem;

import de.hhn.it.devtools.apis.supermarketsystem.BillEntry;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/** Represents the bill.
 *
 */
public class Bill {
  private final HashMap<Integer, BillEntry> productList;

  private float summary;

  public Bill() {
    productList = new HashMap<>();
    summary = 0F;
  }

  public Map<Integer, BillEntry> getList() {
    return productList;
  }

  public float getSummary() {
    return summary;
  }

  /** Returns the product list of the bill.
   *
   * @return product list
   */
  public Map<Integer, Product> getProducts() {
    return ((HashMap<Integer, BillEntry>) productList.clone())
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey() >= 0)
        .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getProduct()));
  }

  /** Adds a bill entry to the bill.
   *
   * @param id id of the product
   * @param billEntry bill entry
   */
  public void addBillEntry(int id, BillEntry billEntry) {
    productList.put(id, billEntry);
  }

  /** Removes a bill entry by the product id.
   *
   * @param id id of the product
   */
  public void removeEntry(int id) {
    productList.remove(id);
  }

  /** Checks if the product list contains the given key.
   *
   * @param id id of a product
   * @return true/false
   */
  public boolean containsKey(int id) {
    return productList.containsKey(id);
  }

  /** Returns a entry from the product list.
   *
   * @param id id of the product
   * @return bill entry
   */
  public BillEntry getEntry(int id) {
    return productList.get(id);
  }

  /** Recalculates the bill summary.
   *
   */
  public void recalculate() {
    summary = 0F;

    for (BillEntry entry : productList.values()) {
      summary += entry.getPrice() * entry.getQuantity();
    }
  }

  @Override
  public String toString() {
    return "Bill:\n"
        + "Products:\n" + productList
        + "\nSummary\n" + summary;
  }
}
