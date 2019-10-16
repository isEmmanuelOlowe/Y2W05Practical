package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import common.StockUnavailableException;
import interfaces.IProduct;
import interfaces.IStockRecord;

public class StockRecordTest extends AbstractFactoryClient {

  /**
  * Checks the initial stock amount is zero.
  */
  @Test
  public void stockRecordInitiallyZero() {
    IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
    IStockRecord stockRecord = getFactory().makeStockRecord(product);
    assertEquals(0, stockRecord.getStockCount());
  }

  /**
  * Checks that we can add stock to the StockRecord.
  */
  @Test
  public void stockRecordAddSingleStock() {
    IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
    IStockRecord stockRecord = getFactory().makeStockRecord(product);
    stockRecord.addStock();
    assertEquals(1, stockRecord.getStockCount());
  }

  /**
  * Checks that we can add multiple stock items.
  */
  @Test
  public void stockRecordAddMultipleStock() {
    IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
    IStockRecord stockRecord = getFactory().makeStockRecord(product);
    //adds 50 items
    for (int i = 0; i < 50; i++ ){
      stockRecord.addStock();
    }
    assertEquals(50, stockRecord.getStockCount());
  }

  /**
  * Checks when a product is bought stock is descreased.
  */
  @Test
  public void StockRecordBuyReducesStock() throws StockUnavailableException {
    IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
    IStockRecord stockRecord = getFactory().makeStockRecord(product);
    //adds 50 items
    for (int i = 0; i < 50; i++ ){
      stockRecord.addStock();
    }
    //buys 29 of the product
    for(int i = 0; i < 29; i++) {
      stockRecord.buyProduct();
    }
    assertEquals(21, stockRecord.getStockCount());
  }

  /**
  * Checks when a product is bought sales is increased.
  */
  public void StockRecordBuyIncreasesSales() throws StockUnavailableException {
    IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
    IStockRecord stockRecord = getFactory().makeStockRecord(product);
    //adds 50 items
    for (int i = 0; i < 50; i++ ){
      stockRecord.addStock();
    }
    //buys 11 of the product
    for(int i = 0; i < 11; i++) {
      stockRecord.buyProduct();
    }
    assertEquals(11, stockRecord.getStockCount());
  }

  /**
  * Checks exception is thrown if you try to buy a product which a no stock
  */
  @Test
  public void StockRecordCantBuyFromZeroStock() {
    IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
    IStockRecord stockRecord = getFactory().makeStockRecord(product);
    assertThrows(StockUnavailableException.class, () -> {
      stockRecord.buyProduct();
    });
  }

  /**
  * Checks that product is stored in the StockRecord
  */
  @Test
  public void StockRecordProductStored() {
    IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
    IStockRecord stockRecord = getFactory().makeStockRecord(product);
    assertEquals(product, stockRecord.getProduct());
  }
}
