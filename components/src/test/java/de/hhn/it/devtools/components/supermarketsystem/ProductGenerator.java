package de.hhn.it.devtools.components.supermarketsystem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProductGenerator {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SupermarketServerServiceTest.class);

  private ProductGenerator() { }

  public static List<Product> getSampleProductList() throws FileNotFoundException {
    Gson gson = new Gson();

    URL productsFileUrl = ProductGenerator.class.getClassLoader()
        .getResource("supermarketsystem/Products.json");

    FileReader fileReader = new FileReader(productsFileUrl.getPath());
    List<Product> products = gson.fromJson(fileReader,
        new TypeToken<ArrayList<Product>>() {}.getType());

    return products;
  }
}
