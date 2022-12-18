package de.hhn.it.devtools.apis.supermarketsystem;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.List;
import java.util.Map;
import org.slf4j.LoggerFactory;

/**
 * Demo class to test the POS System Service
 */
public class PosSystemServiceUsageDemo {

  private static final org.slf4j.Logger logger
      = LoggerFactory.getLogger(PosSystemServiceUsageDemo.class);
  public static void main (String[] args) throws IllegalParameterException, IllegalStateException {

    // sets up the PosSystemService.
    PosSystemListener posSystemListener = null;
    PosSystemService posSystemService = null;

    // adds a product to the bill.
    posSystemService.addProductToBill(1);
    posSystemService.addProductToBill(2);
    posSystemService.addProductToBill(3);

    // shows a list of all scanned products.
    Map<Integer, Product> scannedProducts = posSystemService.getProductsFromBill();

    logger.info("Scanned products:\n");
    for(int i = 0; i < scannedProducts.size(); i++){
      logger.info("" + scannedProducts.get(i) + "\n");
    }

    // deletes a product from the bill.
    posSystemService.deleteProductFromBill(3);

    // shows a list of all scanned products.
    posSystemService.getProductsFromBill();

    logger.info("Scanned products after delete:\n");
    for(int i = 0; i < scannedProducts.size(); i++){
      logger.info("" + scannedProducts.get(i) + "\n");
    }

    // shows a list of all available products.
    Map<Integer, Product> availableProducts = posSystemService.getProducts();

    logger.info("Available products at supermarket:\n");
    for(int i= 0; i < availableProducts.size(); i++){
      logger.info("" + availableProducts.get(i) + "\n");
    }

    // Shows information about a specific product.
    logger.info("" + posSystemService.getProduct(2) + "\n");

    // shows the total price of the bill.
    float price = posSystemService.getBill().getSummary();
    logger.info("Total price: " + price + "\n");

    // shows the change the customer gets.
    float change = posSystemService.calculateChange(50.0F);
    logger.info("Change for the customer: " + change + "\n");

  }
}
