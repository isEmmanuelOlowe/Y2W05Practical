package impl;

import java.util.HashMap;
import common.AbstractFactoryClient;
import common.BarCodeAlreadyInUseException;
import common.ProductNotRegisteredException;
import common.StockUnavailableException;
import interfaces.IProduct;
import interfaces.IStockRecord;
import interfaces.IShop;
/**
 * This class represents a simple shop which can stok and sell products.
 *
 */
public class Shop extends AbstractFactoryClient implements IShop {

    //Will store the stock record for a specified bar code.
    private HashMap<String, IStockRecord> stockRecords;

    /**
    * Creates a new Shop
    */
    public Shop() {
      stockRecords = new HashMap<String, IStockRecord>();
    }

    /**
    * Registers a new product.
    *
    * @param product the product being Registered
    * @throws BarCodeAlreadyInUseException if the barcode of new product is being used by another
    */
    @Override
    public void registerProduct(IProduct product) throws BarCodeAlreadyInUseException {

        if (stockRecords.containsKey(product.getBarCode())) {
          throw new BarCodeAlreadyInUseException();
        }
        else {
          stockRecords.put(product.getBarCode(), getFactory().makeStockRecord(product));
        }
    }

    /**
    * removes a product for the registered products
    *
    * @param product the product being unregistered.
    * @throws ProductNotRegisteredException if the product attempting to be unregistered is not a registered product
    */
    @Override
    public void unregisterProduct(IProduct product) throws ProductNotRegisteredException {
        if (!stockRecords.containsKey(product.getBarCode())) {
          throw new ProductNotRegisteredException();
        }
        else {
          stockRecords.remove(product.getBarCode());
        }
    }

    /**
    * Adds stock for a specific barcode of product
    *
    * @param barCode the barcode of the product which stock is being added.
    * @throws ProductNotRegisteredException if the barcode does not correspond to that of any registered product.
    */
    @Override
    public void addStock(String barCode) throws ProductNotRegisteredException {
        if (!stockRecords.containsKey(barCode)) {
          throw new ProductNotRegisteredException();
        }
        else {
          IStockRecord stockRecord = stockRecords.get(barCode);
          stockRecord.addStock();
        }
    }

    /**
    * Buys a specificed product from avaliable stock
    *
    * @param barCode the barcode of product being bought.
    * @throws ProductNotRegisteredException If the barcode does not correspond to that of any registered product.
    */
    @Override
    public void buyProduct(String barCode) throws StockUnavailableException, ProductNotRegisteredException {
      if (!stockRecords.containsKey(barCode)) {
        throw new ProductNotRegisteredException();
      }
      else {
        IStockRecord stockRecord = stockRecords.get(barCode);
        stockRecord.buyProduct();
      }
    }

    /**
    * Gets the total number of products in shop
    *
    * @return the number of products
    */
    @Override
    public int getNumberOfProducts() {
        return stockRecords.size();
    }


    @Override
    public int getTotalStockCount() {
      int total = 0;
      for (String barCode: stockRecords.keySet()) {
        try {
          total += getStockCount(barCode);
        }
        //cant happen as we are iterating over hashset of registered products.
        catch (ProductNotRegisteredException e) {
          e.printStackTrace();
        }
      }
      return total;
    }


    /**
    * Gets the total amount of a specified product.
    *
    * @param barCode the barcode of the product wanted to get the total stock
    * @throws ProductNotRegisteredException in the event.
    */
    @Override
    public int getStockCount(String barCode) throws ProductNotRegisteredException {
      if (!stockRecords.containsKey(barCode)) {
        throw new ProductNotRegisteredException();
      }
      else {
        IStockRecord stockRecord = stockRecords.get(barCode);
        return stockRecord.getStockCount();
      }
    }

    /**
    * Gets the total amount of sales for a specific product
    *
    * @param barCode the barcode of the product which total sales are being recieved.
    * @throws ProductNotRegisteredException in the event the barcode does not correspond to a registered product.
    */
    @Override
    public int getNumberOfSales(String barCode) throws ProductNotRegisteredException {
      if (!stockRecords.containsKey(barCode)) {
        throw new ProductNotRegisteredException();
      }
      else {
        IStockRecord stockRecord = stockRecords.get(barCode);
        return stockRecord.getNumberOfSales();
      }
    }

    /**
     * Gets the product that has been bought the greatest number of times.
     * If there is not a single most popular product, then one of the most popular products will be returned.
     *
     * @return the product that has been bought the greatest number of times
     * @throws ProductNotRegisteredException if no products are registered for sale in the shop
     */
    @Override
    public IProduct getMostPopular() throws ProductNotRegisteredException {
      IProduct mostPopular = null;
      int mostPopularSale = -1;
      if (stockRecords.size() == 0) {
        throw new ProductNotRegisteredException();
      }
      else {
        for (String barCode: stockRecords.keySet()) {
          if (getNumberOfSales(barCode) > mostPopularSale) {
            mostPopular = stockRecords.get(barCode).getProduct();
            mostPopularSale = getNumberOfSales(barCode);
          }
        }
      }
      return mostPopular;
    }

}
