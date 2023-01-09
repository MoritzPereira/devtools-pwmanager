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

  @Override
  public float getSummary() {
    return summary;
  }

  @Override
  public Map<Integer, Product> getProducts() {
    return ((HashMap<Integer, BillEntry>) productList.clone())
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey() >= 0)
        .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getProduct()));
  }

  @Override
  public void addBillEntry(int id, BillEntry billEntry) {
    productList.put(id, billEntry);
  }

  @Override
  public void removeEntry(int id) {
    productList.remove(id);
  }

  @Override
  public boolean containsKey(int id) {
    return productList.containsKey(id);
  }

  @Override
  public BillEntry getEntry(int id) {
    return productList.get(id);
  }

  @Override
  public void recalculate() {
    summary = 0F;

    for (BillEntry entry : productList.values()) {
      summary += entry.getPrice() * entry.getQuantity();
    }
  }

  @Override
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
