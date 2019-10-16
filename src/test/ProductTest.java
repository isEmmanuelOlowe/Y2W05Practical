package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IProduct;

/**
* This is a JUnirt test class for the Product ADT.
*/
public class ProductTest extends AbstractFactoryClient {

  /**
  * Creates the product object that will be tested.
  */
  @BeforeEach
  public void createProduct() {
    IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
  }
  /**
  * This checks that barcode is returned correctly from the product.
  */
  @Test
  public void productBarcode() {
    //Checks that the barcode is the same
    assertEquals("1234567", product.getBarCode());
  }

  /**
  * This checks that description of the product is returned correctly.
  */
  @Test
  public void productDescription() {
    //Checks the description is the same
    assertEquals("Laptop Computer", product.getDescription());
  }

  /**
  * Tests if the definition of equals has been changed for product object
  */
  public void productEquals() {
    IProduct product2 = getFactory().makeProduct("1234567", "Laptop Computer");
    //Even though they are different objects they should be the same product.
    assertEquals(product, product2);
  }
}
