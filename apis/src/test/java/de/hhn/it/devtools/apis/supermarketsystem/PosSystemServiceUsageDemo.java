package de.hhn.it.devtools.apis.supermarketsystem;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.util.List;
import java.util.Map;

/**
 * Demo class to test the POS System Service
 */
public class PosSystemServiceUsageDemo {
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

    System.out.println("Scanned products: ");
    for(int i = 0; i < scannedProducts.size(); i++){
      System.out.println(scannedProducts.get(i));
      System.out.println("---------");
    }

    // deletes a product from the bill.
    posSystemService.deleteProductFromBill(3);

    // shows a list of all scanned products.
    posSystemService.getProductsFromBill();

    System.out.println("Scanned products after delete");
    for(int i = 0; i < scannedProducts.size(); i++){
      System.out.println(scannedProducts.get(i));
      System.out.println("---------");
    }

    // shows a list of all available products.
    Map<Integer, Product> availableProducts = posSystemService.getProducts();

    System.out.println("Available products at supermarket");
    for(int i= 0; i < availableProducts.size(); i++){
      System.out.println(availableProducts.get(i));
      System.out.println("--------");
    }

    // Shows information about a specific product.
    System.out.println(posSystemService.getProduct(2));

    // shows the total price of the bill.
    System.out.println("Total price: ");
    System.out.println(posSystemService.getBill().getSummary());

    // shows the change the customer gets.
    System.out.println("Change for the customer: ");
    System.out.println(posSystemService.calculateChange(50.0F));

  }
}
