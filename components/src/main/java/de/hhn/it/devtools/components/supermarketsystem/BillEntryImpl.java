package de.hhn.it.devtools.components.supermarketsystem;

import de.hhn.it.devtools.apis.supermarketsystem.BillEntry;
import de.hhn.it.devtools.apis.supermarketsystem.Product;

/** Implementation of a BillEntry.
 *
 */
public class BillEntryImpl implements BillEntry {

  private final float price;
  private int quantity;

  private final Product product;

  /** Constructor of BillEntry.
   *
   * @param product product
   */
  public BillEntryImpl(Product product) {
    this.product = product;
    this.price = product.getPrice();
    this.quantity = 1;
  }

  @Override
  public Product getProduct() {
    return product;
  }

  @Override
  public float getPrice() {
    return price;
  }

  @Override
  public int getQuantity() {
    return quantity;
  }

  public void addQuantity() {
    this.quantity++;
  }

  @Override
  public void addQuantity(int quantity) {
    this.quantity += quantity;
  }

  public void reduceQuantity() {

    if (this.quantity < 0) {
      throw new IllegalStateException("BillEntry quantity is lower then 0");
    }

    this.quantity--;
  }

  @Override
  public void reduceQuantity(int quantity) {

    if (this.quantity - quantity <= 0) {
      throw new IllegalStateException("BillEntry quantity is lower then or equal to 0");
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
