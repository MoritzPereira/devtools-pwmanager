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

    logger.info("Scanned Products" + scannedProducts);
    for(BillEntry billEntry : scannedProducts.values()) {
      logger.info("" + billEntry + "\n");
      logger.info("---------\n");
    }

    // deletes a product from the bill.
    supermarketServerService.deleteProductFromBill(3);

    // shows a list of all scanned products after deletion
    supermarketServerService.getProductsFromBill();

    logger.info("Scanned products after delete" + scannedProducts);
    for(BillEntry billEntry : scannedProducts.values()) {
      logger.info("" + billEntry + "\n");
      logger.info("---------\n");
    }


    // shows a list of all available products.
    Map<Integer, Product> availableProducts = supermarketServerService.getProducts();

    logger.info("Available products at supermarket");
    for(Product product : availableProducts.values()) {
      logger.info("" + product + "\n");
      logger.info("---------\n");
    }

    // Shows information about a specific product.
    logger.info("Display product with id: 6 \n" + supermarketServerService.getProduct(6) + "\n");

    // adds that Product to bill
    supermarketServerService.addProductToBill(6);


    // shows the total price of the bill.
    float price = supermarketServerService.getBill().getSummary();
    logger.info("Total price: " + price + "\n");

    // shows the change the customer gets.
    float change = supermarketServerService.calculateChange(50.0F);
    System.out.println("Change for the customer: " + change + "\n");

  }
  public static void addProductsToSystem() {
    supermarketServerService = new SupermarketServerService();

    logger.info("Prepare Products\n");
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
        logger.info("Adding product: " + name + "\n");
      }

      logger.info("All products added to the store.\n");
    } catch (IOException ioException) {
      logger.warn("File not found!\n");
      logger.warn("" + ioException.getMessage());
    }
  }
}


