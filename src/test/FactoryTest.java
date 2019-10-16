package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IProduct;
import interfaces.IStockRecord;
import interfaces.IShop;

/**
* This is a JUnit test class for Factory.
*/
public class FactoryTest extends AbstractFactoryClient {

  /**
   * This checks that the factory was able to call a sensible constructor to get a non-null instance of IProduct.
   */
  @Test
  public void productCreationNonNull() {
      IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
      assertNotNull(product);
  }

  /**
   * This checks that the factory was able to call a sensible constructor to get a non-null instance of IStockRecord.
   */
  @Test
  public void stockRecordCreationNonNull() {
      IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
      IStockRecord stockRecord = getFactory().makeStockRecord(product);
      assertNotNull(stockRecord);
  }

  /**
  * Checks the creation of a shop is an object.
  */
  @Test
  public void shopCreationNonNull() {
    IShop shop = getFactory().makeShop();
    assertNotNull(shop);
  }
}
