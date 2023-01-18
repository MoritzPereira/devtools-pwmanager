package de.hhn.it.devtools.apis.supermarketsystem;

/** Represents a single entry of the bill.
 *
 */
public interface BillEntry {

  /** Returns the product from the BillEntry.
   *
   * @return product
   */
  Product getProduct();

  /** Returns the calculated price of the entry.
   *
   * @return price
   */
  float getPrice();

  /** Returns the quantity of the entry.
   *
   * @return quantity
   */
  int getQuantity();

  /** Raises the quantity by once.
   *
   */
  void addQuantity();

  /** Raises the quantity by the given amount.
   *
   * @param quantity quantity amount
   */
  void addQuantity(int quantity);

  /** Reduces the quantity by once.
   *
   */
  void reduceQuantity();

  /** Reduces the quantity by the given amount.
   *
   * @param quantity quantity amount
   */
  void reduceQuantity(int quantity);

}
