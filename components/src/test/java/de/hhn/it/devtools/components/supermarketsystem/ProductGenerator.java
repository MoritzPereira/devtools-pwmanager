package de.hhn.it.devtools.components.supermarketsystem;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProductGenerator {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SupermarketServerServiceTest.class);

  private final static String sampleProductFilePath =
      "src/test/java/de/hhn/it/devtools/components/supermarketsystem/Products.json";

  private static Path filePath;

  private ProductGenerator() { }

  private static JsonArray getSampleProducts(Path filePath)
      throws IllegalStateException, IOException {
    // Read File from System
    Reader reader = Files.newBufferedReader(filePath);
    JsonArray productArray = JsonParser.parseReader(reader).getAsJsonArray();

    return productArray;
  }

  public static List<Product> getSampleProductList(boolean calledFromDemo) {
    logger.info("Prepare Products");

    List<Product> productList = new ArrayList<>();

    Path currentRelativePath = Paths.get("");
    String s = currentRelativePath.toAbsolutePath().toString();
    logger.info("Current absolute path is: " + s);

    try {
      // Workaround cause Java thinks the UsageDemo and the JUnit tests are in different folders...
      if(calledFromDemo) {
        filePath = Path.of("components", sampleProductFilePath);
      } else {
        filePath = Path.of(sampleProductFilePath);
      }

      JsonArray products = getSampleProducts(filePath);

      for (JsonElement jsonElement : products) {
        Gson gson = new Gson();

        /* Get Values from entry */
        Product product = gson.fromJson(jsonElement, Product.class);

        productList.add(product);
      }
    } catch (Exception exception) {
      logger.warn(exception.getMessage());
    }

    return productList;
  }
}
