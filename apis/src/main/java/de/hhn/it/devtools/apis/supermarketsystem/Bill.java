package de.hhn.it.devtools.apis.supermarketsystem;

import java.util.Map;

/** Represents the bill.
 *
 */
public interface Bill {

  Map<Integer, BillEntry> getList();

  float getSummary();

  /** Returns the product list of the bill.
   *
   * @return product list
   */
  Map<Integer, Product> getProducts();

  /** Adds a bill entry to the bill.
   *
   * @param id id of the product
   * @param billEntry bill entry
   */
  void addBillEntry(int id, BillEntry billEntry);

  /** Removes a bill entry by the product id.
   *
   * @param id id of the product
   */
  void removeEntry(int id);

  /** Checks if the product list contains the given key.
   *
   * @param id id of a product
   * @return true/false
   */
  boolean containsKey(int id);

  /** Returns an entry from the product list.
   *
   * @param id id of the product
   * @return bill entry
   */
  BillEntry getEntry(int id);

  /** Recalculates the bill summary.
   *
   */
  void recalculate();

  /** Clears the product list and sets the summary to zero.
   *
   */
  void clear();
}
