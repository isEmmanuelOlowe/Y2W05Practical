package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IProduct;

/**
* This is a a JUnirt test class for the Product ADT
*/
public class Tests extends AbstractFactoryClient {

  /**
  * This checks that barcode is returned correctly from the product
  */
  @Test
  public void productBarcode() {
    IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
    //Checks that the barcode is the same
    assertEquals("1234567", product.getBarCode());
  }

  /**
  * This checks that description of the product is returned correctly
  */
  @Test
  public void productDescription() {
    IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
    //Checks the description is the same
    assertEquals("Laptop Computer", product.getDescription());
  }
}
