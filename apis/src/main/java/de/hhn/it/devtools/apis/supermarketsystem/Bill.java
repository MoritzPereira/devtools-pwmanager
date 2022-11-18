package de.hhn.it.devtools.apis.supermarketsystem;

import de.hhn.it.devtools.apis.supermarketsystem.BillEntry;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/** Represents the bill.
 *
 */
public interface Bill {

  public Map<Integer, BillEntry> getList();

  public float getSummary();

  /** Returns the product list of the bill.
   *
   * @return product list
   */
  public Map<Integer, Product> getProducts();

  /** Adds a bill entry to the bill.
   *
   * @param id id of the product
   * @param billEntry bill entry
   */
  public void addBillEntry(int id, BillEntry billEntry);

  /** Removes a bill entry by the product id.
   *
   * @param id id of the product
   */
  public void removeEntry(int id);

  /** Checks if the product list contains the given key.
   *
   * @param id id of a product
   * @return true/false
   */
  public boolean containsKey(int id);

  /** Returns a entry from the product list.
   *
   * @param id id of the product
   * @return bill entry
   */
  public BillEntry getEntry(int id);

  /** Recalculates the bill summary.
   *
   */
  public void recalculate();

}
