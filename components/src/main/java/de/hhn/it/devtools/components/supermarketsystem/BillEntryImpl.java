package de.hhn.it.devtools.components.supermarketsystem;

import de.hhn.it.devtools.apis.supermarketsystem.BillEntry;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import org.slf4j.LoggerFactory;

/** Implementation of a BillEntry.
 *
 */
public class BillEntryImpl implements BillEntry {

  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BillEntryImpl.class);

  private final float price;
  private int quantity;

  private final Product product;

  /** Constructor of BillEntry.
   *
   * @param product product
   */
  public BillEntryImpl(Product product) {
    logger.info("Constructor: ", product + "\n");
    this.product = product;
    this.price = product.getPrice();
    this.quantity = 1;
  }

  @Override
  public Product getProduct() {
    logger.info("getProduct\n");
    return product;
  }

  @Override
  public float getPrice() {
    logger.info("getPrice\n");
    return price;
  }

  @Override
  public int getQuantity() {
    logger.info("getQuantity\n");
    return quantity;
  }

  @Override
  public void addQuantity() {
    logger.info("addQuantity\n");
    this.quantity++;
  }

  @Override
  public void addQuantity(int quantity) {
    logger.info("addQuantity: ", quantity + "\n");
    this.quantity += quantity;
  }

  @Override
  public void reduceQuantity() {

    logger.info("reduceQuantity\n");

    if (this.quantity < 0) {
      throw new IllegalStateException("BillEntry quantity is lower then 0");
    }

    this.quantity--;
  }

  @Override
  public void reduceQuantity(int quantity) {
    logger.info("reduceQuantity: ", quantity +"\n");
    if (this.quantity - quantity <= 0) {
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
