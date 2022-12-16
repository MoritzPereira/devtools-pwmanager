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

    /*
    -> Produkte werden zum Kassenzettel hinzugefügt
    -> Kunde möchte ein Produkt doch nicht haben => Produkt wird entfernt
    -> Kunde will vlt noch Preis von einem Produkt wissen => Produkt wird abgefragt
    -> Kunde entschließt sich das Produkt zu kaufen => Produkt wird hinzugefügt
    -> Kunde hat keine Ware mehr im Einkaufkorb und will nichts mehr haben => Kassenzettel ausgeben/
       anzeigen
    -> Kunde sieht Betrag von x,xx€ (Bsp. 23,86€) und bezahlt x,xx€ (Bsp. 50€) => Wechselgeld wird
       berechnet und ausgegeben
     */

    //add Product to bill
    supermarketServerService.addProductToBill(1);
    supermarketServerService.addProductToBill(2);
    supermarketServerService.addProductToBill(3);
    supermarketServerService.addProductToBill(3);

    //all Products on the bill
    Map<Integer, BillEntry> scannedProducts = supermarketServerService.getBill().getList();

    System.out.println();

    System.out.println("Scanned products: ");
    for(BillEntry billEntry : scannedProducts.values()) {
      System.out.println(billEntry);
      System.out.println("---------");
    }

    System.out.println();

    // deletes a product from the bill.
    supermarketServerService.deleteProductFromBill(3);

    // shows a list of all scanned products after deletion
    supermarketServerService.getProductsFromBill();

    System.out.println("Scanned products after delete");
    for(BillEntry billEntry : scannedProducts.values()) {
      System.out.println(billEntry);
      System.out.println("---------");
    }

    System.out.println();

    // shows a list of all available products.
    Map<Integer, Product> availableProducts = supermarketServerService.getProducts();

    System.out.println("Available products at supermarket");
    for(Product product : availableProducts.values()) {
      System.out.println(product);
      System.out.println("---------");
    }

    System.out.println();

    // Shows information about a specific product.
    System.out.println("Display product with id 6");
    System.out.println(supermarketServerService.getProduct(6));

    // adds that Product to bill
    supermarketServerService.addProductToBill(6);

    System.out.println();

    // shows the total price of the bill.
    System.out.println("Total price: ");
    System.out.println(supermarketServerService.getBill().getSummary());

    // shows the change the customer gets.
    System.out.println("Change for the customer: ");
    System.out.println(supermarketServerService.calculateChange(50.0F));

  }
}


