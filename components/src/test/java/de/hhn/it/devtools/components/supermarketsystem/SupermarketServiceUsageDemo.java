package de.hhn.it.devtools.components.supermarketsystem;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import de.hhn.it.devtools.apis.supermarketsystem.ProductCategory;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class SupermarketServiceUsageDemo {
  public static void main(String[] args) throws IllegalParameterException, IllegalStateException {
    // sets up the SupermarketServerService.
    SupermarketServerService supermarketServerService = new SupermarketServerService();

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
    Map<Integer, Product> scannedProducts = supermarketServerService.getProductsFromBill();

    System.out.println("Scanned products: ");
    for(int i = 0; i < scannedProducts.size(); i++) {
      System.out.println(scannedProducts.get(i));
      System.out.println("---------");
    }

    // deletes a product from the bill.
    supermarketServerService.deleteProductFromBill(3);

    // shows a list of all scanned products after deletion
    supermarketServerService.getProductsFromBill();

    System.out.println("Scanned products after delete");
    for(int i = 0; i < scannedProducts.size(); i++){
      System.out.println(scannedProducts.get(i));
      System.out.println("---------");
    }

    // shows a list of all available products.
    Map<Integer, Product> availableProducts = supermarketServerService.getProducts();

    System.out.println("Available products at supermarket");
    for(int i= 0; i < availableProducts.size(); i++){
      System.out.println(availableProducts.get(i));
      System.out.println("--------");
    }

    // Shows information about a specific product.
    System.out.println(supermarketServerService.getProduct(6));

    // adds that Product to bill
    supermarketServerService.addProductToBill(6);

    // shows the total price of the bill.
    System.out.println("Total price: ");
    System.out.println(supermarketServerService.getBill().getSummary());

    // shows the change the customer gets.
    System.out.println("Change for the customer: ");
    System.out.println(supermarketServerService.calculateChange(50.0F));

  }
}

