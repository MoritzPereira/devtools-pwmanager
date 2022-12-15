package de.hhn.it.devtools.components.supermarketsystem;

import de.hhn.it.devtools.apis.supermarketsystem.Bill;
import de.hhn.it.devtools.apis.supermarketsystem.BillEntry;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/** Implementation of a Bill.
 *
 */
public class BillImpl implements Bill {
  private final HashMap<Integer, BillEntry> productList;

  private float summary;

  public BillImpl() {
    this.productList = new HashMap<>();
    summary = 0F;
  }

  public Map<Integer, BillEntry> getList() {
    return productList;
  }

  public float getSummary() {
    return summary;
  }

  public Map<Integer, Product> getProducts() {
    return ((HashMap<Integer, BillEntry>) productList.clone())
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey() >= 0)
        .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getProduct()));
  }

  public void addBillEntry(int id, BillEntry billEntry) {
    productList.put(id, billEntry);
  }

  public void removeEntry(int id) {
    productList.remove(id);
  }

  public boolean containsKey(int id) {
    return productList.containsKey(id);
  }

  public BillEntry getEntry(int id) {
    return productList.get(id);
  }

  public void recalculate() {
    summary = 0F;

    for (BillEntry entry : productList.values()) {
      summary += entry.getPrice() * entry.getQuantity();
    }
  }

  public void clear() {
    productList.clear();
    summary = 0F;
  }

  @Override
  public String toString() {
    return "Bill:\n"
        + "Products:\n" + productList
        + "\nSummary\n" + summary;
  }
}
