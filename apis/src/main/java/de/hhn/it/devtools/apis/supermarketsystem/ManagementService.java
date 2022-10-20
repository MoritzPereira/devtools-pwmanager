package de.hhn.it.devtools.apis.supermarketsystem;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * Management interface to the PosSystemService to add and remove Products to / from the service.
 */
public interface ManagementService {

  /**
   * Adds a new Product to the system.
   *
   * @param product the new product
   * @throws IllegalParameterException if the descriptor is a null reference or incomplete.
   */
  void addProduct(Product product) throws IllegalParameterException;

  /**
   * Removes a product from the system.
   *
   * @param productId id of the product to be removed
   * @throws IllegalParameterException if the id of the coffee maker does not exist.
   */
  void removeProduct(int productId) throws IllegalParameterException;
}
