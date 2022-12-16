package de.hhn.it.devtools.apis.supermarketsystem;

/**
 * Represents a single Product which can be bought in the supermarket.
 *
 */
public class Product {
  private int id;
  private String name;
  private String manufacturer;
  private float price;
  private boolean isWeighed;
  private ProductCategory category;

  // TODO: 20.10.2022 Replace idCount with real unique id generator

  /**
   * Constructor for a new product.
   *
   * @param name     name of the product
   * @param price    price of the product
   */
  public Product(String name, float price) {
    this.name = name;
    this.price = price;
  }

  /**
   * Constructor for a new product.
   *
   * @param name         name of the product
   * @param price        price of the product
   * @param manufacturer manufacturer of the product
   * @param isWeighed    boolean if the product will be weighed
   */
  public Product(final int id, String name, float price, String manufacturer, boolean isWeighed) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.manufacturer = manufacturer;
    this.isWeighed = isWeighed;
  }

  /** Returns the name of the product.
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
        + "name='" + name
        + '\''
        + ", manufacturer='" + manufacturer
        + '\''
        + ", price=" + price
        + ", category=" + category
        + ", isWeighed=" + isWeighed
        + '}';
  }
}
