package de.hhn.it.devtools.components.supermarketsystem;

import de.hhn.it.devtools.apis.supermarketsystem.Bill;
import de.hhn.it.devtools.apis.supermarketsystem.BillEntry;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

/** Implementation of a Bill.
 *
 */
public class BillImpl implements Bill {
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BillImpl.class);
  private final HashMap<Integer, BillEntry> productList;

  private float summary;

  /** Constructor for the Bill.
   *
   */
  public BillImpl() {
    logger.info("Constructor\n");
    this.productList = new HashMap<>();
    summary = 0F;
  }

  @Override
  public Map<Integer, BillEntry> getList() {
    logger.info("getList\n");
    return productList;
  }

  @Override
  public float getSummary() {
    logger.info("getSummary\n");
    return summary;
  }

  @Override
  public Map<Integer, Product> getProducts() {
    logger.info("getProducts\n");
    return ((HashMap<Integer, BillEntry>) productList.clone())
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey() >= 0)
        .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getProduct()));
  }

  @Override
  public void addBillEntry(int id, BillEntry billEntry) {
    logger.info("addBillEntry: ", id, billEntry + "\n");
    productList.put(id, billEntry);
  }

  @Override
  public void removeEntry(int id) {
    logger.info("removeEntry :", id + "\n");
    productList.remove(id);
  }

  @Override
  public boolean containsKey(int id) {
    logger.info("containsKey: ", id + "\n");
    return productList.containsKey(id);
  }

  @Override
  public BillEntry getEntry(int id) {
    logger.info("getEntry: ", id + "\n");
    return productList.get(id);
  }

  @Override
  public void recalculate() {
    logger.info("recalculate\n");
    summary = 0F;

    for (BillEntry entry : productList.values()) {
      summary += entry.getPrice() * entry.getQuantity();
    }
  }

  @Override
  public void clear() {
    logger.info("clear\n");
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
