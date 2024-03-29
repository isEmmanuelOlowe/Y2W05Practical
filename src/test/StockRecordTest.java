package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import common.StockUnavailableException;
import interfaces.IProduct;
import interfaces.IStockRecord;

/**
* This is a JUnit test class for StockRecord ADT.
*/
public class StockRecordTest extends AbstractFactoryClient {

  private IProduct product;
  private IStockRecord stockRecord;

  /**
  * Creates the StockRecord and Product that will be used to test the StockRecord.
  */
  @BeforeEach
  public void createStockRecord() {
    product = getFactory().makeProduct("1234567", "Laptop Computer");
    stockRecord = getFactory().makeStockRecord(product);
  }


  /**
  * Checks that you can get stock count.
  */
  @Test
  public void stockRecordStockCount() {
    stockRecord.getStockCount();
  }

  /**
  * Checks the initial stock amount is zero.
  */
  @Test
  public void stockRecordInitiallyZero() {
    final int initialState = 0;
    assertEquals(initialState, stockRecord.getStockCount());
  }

  /**
  * Checks that we can add stock to the StockRecord.
  */
  @Test
  public void stockRecordAddSingleStock() {
    final int stockAfterOneAdded = 1;
    stockRecord.addStock();
    assertEquals(stockAfterOneAdded, stockRecord.getStockCount());
  }

  /**
  * Checks that we can add multiple stock items.
  */
  @Test
  public void stockRecordAddMultipleStock() {
    final int addedFiftyStock = 50;
    //adds 50 items
    for (int i = 0; i < addedFiftyStock; i++) {
      stockRecord.addStock();
    }
    assertEquals(addedFiftyStock, stockRecord.getStockCount());
  }

  /**
  * Checks you are able to buy a product.
  *
  * @throws StockUnavailableException in the event there is no stock
  */
  @Test
  public void stockRecordBuyProduct() throws StockUnavailableException {
    stockRecord.addStock();
    stockRecord.buyProduct();
  }

  /**
  * Checks when a product is bought stock is descreased.
  *
  * @throws StockUnavailableException the exception is not checked by this test but thrown by buyProduct method
  */
  @Test
  public void stockRecordBuyReducesStock() throws StockUnavailableException {
    final int remainingTwentyOneStock = 21;
    //adds 50 items
    final int addFiftyStock = 50;
    for (int i = 0; i < addFiftyStock; i++) {
      stockRecord.addStock();
    }
    //buys 29 of the product
    final int buyTwentyNineProducts = 29;
    for (int i = 0; i < buyTwentyNineProducts; i++) {
      stockRecord.buyProduct();
    }
    assertEquals(remainingTwentyOneStock, stockRecord.getStockCount());
  }

  /**
  * Checks when a product is bought sales is increased.
  *
  * @throws StockUnavailableException checked exception thrown from BuyProduct
  */
  public void stockRecordBuyIncreasesSales() throws StockUnavailableException {
    final int elevenSales = 11;
    //adds 50 items
    final int addFiftyStock = 50;
    for (int i = 0; i < addFiftyStock; i++) {
      stockRecord.addStock();
    }
    //buys 11 of the product
    for (int i = 0; i < elevenSales; i++) {
      stockRecord.buyProduct();
    }
    assertEquals(elevenSales, stockRecord.getStockCount());
  }

  /**
  * Checks exception is thrown if you try to buy a product which a no stock.
  */
  @Test
  public void stockRecordCantBuyFromZeroStock() {
    assertThrows(StockUnavailableException.class, () -> {
      stockRecord.buyProduct();
    });
  }

  /**
  * Checks that product is stored in the StockRecord.
  */
  @Test
  public void stockRecordProductStored() {
    assertEquals(product, stockRecord.getProduct());
  }
}
