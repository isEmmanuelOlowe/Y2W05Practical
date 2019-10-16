package impl;

import common.StockUnavailableException;
import interfaces.IProduct;
import interfaces.IStockRecord;

/**
 * This class represents a record held by the shop for a particular product.
 *
 */
public class StockRecord implements IStockRecord {

    private int currentStock = 0;
    private IProduct product;
    private int sales = 0;

    /**
    * Creates a new StockRecord instance.
    *
    * @param product the product the stock record is being created for
    */
    public StockRecord(IProduct product) {
      this.product = product;
    }

    /**
    * Gets the product for which this stock record exists.
    *
    * @return the object which represents the product
    */
    @Override
    public IProduct getProduct() {
        return product;
    }

    /**
    * The current stock of a product.
    *
    * @return the current stock present for product
    */
    @Override
    public int getStockCount() {
        return currentStock;
    }


    /**
    * The number of sales made for the product.
    *
    * @return the number of sales
    */
    @Override
    public int getNumberOfSales() {
        return sales;
    }


    /**
    * Adds a single product to the stock.
    */
    @Override
    public void addStock() {
        currentStock++;
    }

    /**
    * Buys a product from stock avaliable.
    *
    * @throws StockUnavailableException if no stock avaliable
    */
    @Override
    public void buyProduct() throws StockUnavailableException {
        if (currentStock == 0) {
          throw new StockUnavailableException();
        }
        else {
          sales++;
          currentStock--;
        }
    }

}
