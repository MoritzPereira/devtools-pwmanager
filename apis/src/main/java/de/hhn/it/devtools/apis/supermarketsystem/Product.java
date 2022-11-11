package de.hhn.it.devtools.apis.supermarketsystem;

/**
 * Represents a single Product which can be bought in the supermarket.
 *
 */
public class Product {

  private final int id;
  private String name;
  private String manufacturer;
  private float price;
  private int quantity;
  private boolean isWeighed;
  private ProductCategory category;

  // TODO: 20.10.2022 Replace idCount with real unique id generator
  private static int idCount = 0;

  /**
   * Constructor for a new product.
   *
   * @param name     name of the product
   * @param price    price of the product
   * @param quantity quantity of the product
   */
  public Product(String name, float price, int quantity) {
    this.id = idCount++;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  /**
   * Constructor for a new product.
   *
   * @param name         name of the product
   * @param price        price of the product
   * @param quantity     quantity of the product
   * @param manufacturer manufacturer of the product
   * @param isWeighed    boolean if the product will be weighed
   */
  public Product(String name, float price, int quantity, String manufacturer, boolean isWeighed) {
    this.id = idCount++;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.manufacturer = manufacturer;
  }

  /**
   * Returns the id of the product.
   *
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the name of the product.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Changes the name of a product.
   *
   * @param name name of product
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the manufacturer of the product.
   *
   * @return manufacturer
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Changes the manufacturer of a product.
   *
   * @param manufacturer manufacturer of product
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * Returns the price of the product.
   *
   * @return price
   */
  public float getPrice() {
    return price;
  }

  /**
   * Changes the price of a product.
   *
   * @param price price of product
   */
  public void setPrice(float price) {
    this.price = price;
  }

  /**
   * Returns the quantity of the product.
   *
   * @return quantity
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Changes the quantity of a product.
   *
   * @param quantity quantity of product
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Returns the product category of the product.
   *
   * @return product category
   */
  public ProductCategory getCategory() {
    return category;
  }

  /**
   * Changes the product category of a product.
   *
   * @param category product category of product
   */
  public void setCategory(ProductCategory category) {
    this.category = category;
  }

  /**
   * Returns if the product will be weighed or not.
   *
   * @return isWeighed
   */
  public boolean getIsWeighed() {
    return isWeighed;
  }

  /**
   * Changes if the product will be weighed.
   *
   * @param isWeighed boolean if the product will be weighed
   */
  public void setIsWeighed(boolean isWeighed) {
    this.isWeighed = isWeighed;
  }
  @Override
  public String toString() {
    return "Product{"
        + "id=" + id
        + ", name='" + name
        + '\''
        + ", manufacturer='" + manufacturer
        + '\''
        + ", price=" + price
        + ", quantity=" + quantity
        + ", category=" + category
        + ", isWeighed=" + isWeighed
        + '}';
  }
}
