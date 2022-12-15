package de.hhn.it.devtools.components.supermarketsystem;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.supermarketsystem.BillEntry;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import de.hhn.it.devtools.apis.supermarketsystem.ProductCategory;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

public class SupermarketServiceUsageDemo {

  static SupermarketServerService supermarketServerService;
  private static final org.slf4j.Logger logger
      = LoggerFactory.getLogger(SupermarketServiceUsageDemo.class);

  public static void main(String[] args) throws IllegalParameterException, IllegalStateException {
    // sets up the SupermarketServerService.
    addProductsToSystem();

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
  public static void addProductsToSystem() {
    supermarketServerService = new SupermarketServerService();

    System.out.println("Prepare Products");
    try {
      // Read File from System
      Path filePath = Paths.get("./components/src/test/java/de/hhn/it/devtools/components/supermarketsystem/Products.json");
      Reader reader = Files.newBufferedReader(filePath);
      JsonArray parser = JsonParser.parseReader(reader).getAsJsonArray();

      // Loop through entries
      for(JsonElement jsonElement : parser) {
        // Convert entry to json object
        JsonObject productData = jsonElement.getAsJsonObject();

        /* Get Values from entry */
        int id      = productData.get("id").getAsInt();
        String name = productData.get("name").getAsString();
        float price = productData.get("price").getAsFloat();
        String manufacturer = productData.get("manufacturer").getAsString();
        String category     = productData.get("category").getAsString();
        ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());

        // Add product to service
        supermarketServerService.addProduct(id, name, price, manufacturer, productCategory);

        System.out.println("Adding product: " + name);
      }

      System.out.println("All products added to the store");
    } catch (IOException ioException) {
      System.out.println("File not found");
      System.out.println(ioException.getMessage());
    }
  }
}


