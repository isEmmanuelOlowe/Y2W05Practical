package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import common.BarCodeAlreadyInUseException;
import common.ProductNotRegisteredException;
import common.StockUnavailableException;
import interfaces.IProduct;

/**
* This is a junit class to test the Shop ADT.
*/
public class ShopTest extends AbstractFactoryClient {

  /**
  * Creates the Shop object that will be tested.
  */
  @BeforeEach
  public void createShop() {
    IShop shop = getFactory().makeShop();
    IProduct product = getFactory().makeProduct("1234567", "Laptop");
  }
  /**
  * Checsk you are able to register a product.
  *
  * @throws BarCodeAlreadyInUseException If barcode is being used by another product
  */
  @Test
  public void shopTestRegister() throws BarCodeAlreadyInUseException {
    shop.registerProduct(product);
  }

  /**
  * Checks you cannot register two products with the same barcode.
  */
  @Test
  public void shopCannotRegister() {
    IShop shop = getFactory().makeShop();
    IProduct product2 = getFactory().makeProduct("1234567", "Laser Gun");

    assertThrows(BarCodeAlreadyInUseException.class, () -> {
      shop.register(product);
      shop.register(product2);
    });
  }
  /**
  * Checks the default number of products is zero.
  */
  @Test
  public void shopDefaultProducts() {
    final int initialZero = 0;
    IShop shop = getFactory().makeShop();
    assertEquals(initialZero, shop.getNumberOfProducts());
  }

  /**
  * Tests when you register a product the number of products increases
  */
  public void shopRegisterNoProducts() {
    final int addedOne = 1;
    IShop shop = getFactory().makeShop();
    assertEquals(addedOne, shop.getNumberOfProducts());
  }

  /**
  * Checks you can unregister a registered product.
  */
  public void shopUnregisterRegistered() {
    final int noOfProductsIsZero = 0;
    shop.registerProduct(product);
    shop.unregisterProduct(product);
    assertEquals(0, noOfProductsIsZero);
  }

  /**
  * Checks when you unregister a product the number of products decreases.
  */
  public void shopUnregisterDecreaseNoProducts() {

  }
}
