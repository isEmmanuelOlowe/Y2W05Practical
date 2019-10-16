package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import interfaces.IProduct;
import interfaces.IStockRecord;
import interfaces.IShop;

/**
 * This is a JUnit test class for the Shop ADT.
 */
public class Tests extends AbstractFactoryClient {

    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of IProduct.
     */
    @Test
    public void productCreationNonNull() {
        IProduct product = getFactory().makeProduct("1234567", "Laptop Computer");
        assertNotNull(product);
    }

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
    public void StockRecordBuyReducesStock() {
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
    public void StockRecordBuyIncreasesSales() {
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

    /**
    * Checks the creation of a shop is an object
    */
    @Test
    public void ShopCreationNonNull() {
      IShop shop = getFactory().makeShop();
      assertNotNull(shop);
    }
}
