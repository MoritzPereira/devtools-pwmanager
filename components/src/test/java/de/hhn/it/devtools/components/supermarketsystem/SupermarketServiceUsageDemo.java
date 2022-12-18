package de.hhn.it.devtools.components.supermarketsystem;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.supermarketsystem.BillEntry;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import java.util.List;
import java.util.Map;
import org.slf4j.LoggerFactory;

public class SupermarketServiceUsageDemo {

  static SupermarketServerService supermarketServerService;
  private static final org.slf4j.Logger logger
      = LoggerFactory.getLogger(SupermarketServiceUsageDemo.class);

  public static void main(String[] args) throws IllegalParameterException, IllegalStateException {
    // sets up the SupermarketServerService.
    supermarketServerService = new SupermarketServerService();

    List<Product> productList = ProductGenerator.getSampleProductList(true);
    supermarketServerService.addProducts(productList);


    //add Product to bill
    supermarketServerService.addProductToBill(1);
    supermarketServerService.addProductToBill(2);
    supermarketServerService.addProductToBill(3);
    supermarketServerService.addProductToBill(3);

    //all Products on the bill
    Map<Integer, BillEntry> scannedProducts = supermarketServerService.getBill().getList();

    logger.info("Scanned products:\n");
    for(BillEntry billEntry : scannedProducts.values()) {
      logger.info("" + billEntry + "\n");
    }

    // deletes a product from the bill.
    supermarketServerService.deleteProductFromBill(3);

    // shows a list of all scanned products after deletion
    supermarketServerService.getProductsFromBill();

    logger.info("Scanned products after delete:\n");
    for(BillEntry billEntry : scannedProducts.values()) {
      logger.info("" + billEntry + "\n");
    }

    // shows a list of all available products.
    Map<Integer, Product> availableProducts = supermarketServerService.getProducts();

    logger.info("Available products at supermarket:\n");
    for(Product product : availableProducts.values()) {
      logger.info("" + product + "\n");
    }

    // Shows information about a specific product.
    logger.info("Display product with id 6:\n");
    logger.info("" + supermarketServerService.getProduct(6) + "\n");

    // adds that Product to bill
    supermarketServerService.addProductToBill(6);

    // shows the total price of the bill.
    logger.info("Total price: \n");
    logger.info("" + supermarketServerService.getBill().getSummary() + "\n");

    // shows the change the customer gets.
    logger.info("Change for the customer:\n");
    logger.info("" + supermarketServerService.calculateChange(50.0F) + "\n");

  }
}


