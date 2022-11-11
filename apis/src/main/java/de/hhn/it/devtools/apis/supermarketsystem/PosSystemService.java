package de.hhn.it.devtools.apis.supermarketsystem;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.Map;

/**
 * This PosSystemService is an interface to a bunch of Pos Systems in a supermarket.
 */
public interface PosSystemService {

  /**
   * Returns a list of all products.
   *
   * @return List of all Products
   */
  Map<Integer, Product> getProducts();

  /**
   * Returns a list of all scanned products.
   *
   * @return All scanned products
   */
  Map<Integer, Product> getProductsFromBill();

  /**
   * Returns a single product with the given id.
   *
   * @param id id of the product
   * @return The product
   * @throws IllegalParameterException if the id of the product does not exist
   */
  Product getProduct(int id) throws IllegalParameterException;

  /**
   * Adds a listener to get updates on the state of the POS system.
   *
   * @param listener object implementing the listener interface
   * @throws IllegalParameterException if the listener is a null reference.
   */
  void addCallback(PosSystemListener listener) throws IllegalParameterException;

  /**
   * Removes a POS system listener.
   *
   * @param listener listener to be removed
   * @throws IllegalParameterException if the listener is a null reference
   */
  void removeCallback(PosSystemListener listener) throws IllegalParameterException;

  /**
   * Adds a product to the bill.
   *
   * @param id id of the product
   * @throws IllegalParameterException if the id of the product does not exist
   * @throws IllegalStateException if the POS system is in an error state or out of service
   */
  void addProductToBill(int id) throws IllegalParameterException, IllegalStateException;

  /**
   * Deletes a Product from the bill.
   *
   * @param id id of the product
   * @throws IllegalParameterException if the id of the product does not exist
   * @throws IllegalStateException if the POS system is in an error state or out of service
   */
  void deleteProductFromBill(int id) throws IllegalParameterException, IllegalStateException;

  /**
   * Summarizes the prices of the scanned products.
   *
   * @return Total price of scanned products
   */
  float sumUpPrices();

  /**
   * Calculates the change for the customer.
   *
   * @param givenMoney amount of money the customer gives the cashier.
   * @return Change for customer
   */
  float calculateChange(float givenMoney) throws IllegalParameterException;

}
