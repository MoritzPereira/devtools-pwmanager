package de.hhn.it.devtools.components.supermarketsystem;

import static org.junit.jupiter.api.Assertions.*;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.supermarketsystem.Bill;
import de.hhn.it.devtools.apis.supermarketsystem.PosSystemListener;
import de.hhn.it.devtools.apis.supermarketsystem.Product;
import de.hhn.it.devtools.apis.supermarketsystem.ProductCategory;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupermarketServerServiceTest {

  @BeforeEach
  void setup(){
    var supermarketServerService = new SupermarketServerService();
    final HashMap<Integer, Product> products;
    final List<PosSystemListener> listeners;
    final Bill bill;
  }

  @Test
  public void shoulAddProduct(){
    var supermarketServerService = new SupermarketServerService();
    assertEquals(supermarketServerService.getProduct(26) ,supermarketServerService.addProduct(
        26, "FluffBalls", 560, "Fluffs´n FLuffs", ProductCategory.FOOD ));


  }

  @Test
  public void shouldGetProducts(){
    var supermarketServerService = new SupermarketServerService();
    //assertA(supermarketServerService.getProducts(), ;
    //wie vergleiche ich Hashmaps?
  }

  @Test
  public void shouldGetBill(){
    var supermarketServerService = new SupermarketServerService();
    supermarketServerService.getBill();
    //wie soll ich die Bill vergleichen?
  }

  @Test
  public void shouldGetProductsFromBill(){
    var supermarketServerService = new SupermarketServerService();
    assertNull(supermarketServerService.getProductsFromBill(), "Bill ist leer also null");
  }

  @Test
  public void shouldGetProduct() throws IllegalParameterException {
    var supermarketServerService = new SupermarketServerService();
    supermarketServerService.getProduct(25);
        //what id does this method get/ what does this mehtod do?
  }

  @Test
  public void shouldAddCallback(){
    var supermarketServerService = new SupermarketServerService();
    //supermarketServerService.addCallback(new PosSystemListener());
    //assertEquals(1, listeners.size());

    //Keine Ahnung wie ich überprüfen soll dass der listener da ist
  }

  @Test
  public void shouldRemoveCallack(){
    var supermarketServerService = new SupermarketServerService();

    //selbes Problem
  }

  @Test
  public void shouldAddPruductToBill() throws IllegalParameterException {
    var supermarketServerService = new SupermarketServerService();
    supermarketServerService.addProductToBill(25);
    //help

  }

  @Test
  public void shouldDeleteProductFromBill(){
    var supermarketServerService = new SupermarketServerService();
    //pls more help
  }

  @Test
  public void shouldCalculateChange(){
    var supermarketServerService = new SupermarketServerService();
    assertEquals(500, supermarketServerService.calculateChange(500),
        "Bill is empty so full amount returned");
  }


}