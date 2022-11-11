package de.hhn.it.devtools.apis.supermarketsystem;

import de.hhn.it.devtools.apis.supermarketsystem.Product;

/** Represents a single entry of the bill.
 *
 */
public class BillEntry {

  private final float price;
  private int quantity;

  private final Product product;

  /** Creates a new instance of a bill entry.
   *
   * @param product linked product
   */
  public BillEntry(Product product) {
    this.product = product;
    this.quantity = product.getQuantity();
    this.price = product.getPrice();
  }

  /** Returns the product from the BillEntry.
   *
   * @return product
   */
  public Product getProduct() {
    return product;
  }

  /** Returns the calculated price of the entry.
   *
   * @return price
   */
  public float getPrice() {
    return price;
  }

  /** Returns the quantity of the entry.
   *
   * @return quantity
   */
  public int getQuantity() {
    return quantity;
  }

  /** Raises the quantity by once.
   *
   */
  public void addQuantity() {
    this.quantity++;
  }

  /** Raises the quantity by the given amount.
   *
   * @param quantity quantity amount
   */
  public void addQuantity(int quantity) {
    this.quantity += quantity;
  }

  /** Reduces the quantity by once.
   *
   */
  public void reduceQuantity() {

    if (this.quantity < 0) {
      throw new IllegalStateException("BillEntry quantity is lower then 0");
    }

    this.quantity--;
  }

  /** Reduces the quantity by the given amount.
   *
   * @param quantity quantity amount
   */
  public void reduceQuantity(int quantity) {

    if (this.quantity - quantity < 0) {
      throw new IllegalStateException("BillEntry quantity is lower then 0");
    }

    this.quantity -= quantity;
  }

  @Override
  public String toString() {

    return String.format(
        "\nBillEntry:\nName: %s\nPrice: %.2f\nQuantity: %d\n",
        product == null ? "Summe" : product.getName(),
        price,
        quantity
    );
  }
}
