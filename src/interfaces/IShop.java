package interfaces;

import common.BarCodeAlreadyInUseException;
import common.ProductNotRegisteredException;
import common.StockUnavailableException;

/**
 * Interface for a simple shop ADT.
 *
 */
public interface IShop {


    /**
     * Registers the specified product for sale in the shop.
     * @param product the product to register
     * @throws BarCodeAlreadyInUseException if a product is already registered in the shop with matching bar code
     */
    void registerProduct(IProduct product) throws BarCodeAlreadyInUseException;


    /**
     * Unregisters the specified product from the shop.
     * @param product the product to remove
     * @throws ProductNotRegisteredException if the product is not been registered for sale in the shop
     */
    void unregisterProduct(IProduct product) throws ProductNotRegisteredException;


    /**
     * Adds stock for the product with given bar code.
     * @param barCode the bar code of the product
     * @throws ProductNotRegisteredException if the product is not registered for sale in the shop
     */
    void addStock(String barCode) throws ProductNotRegisteredException;


    /**
     * Buys the product with given bar code from the shop.
     *
     * @param barCode the bar code of the product to be bought
     * @throws StockUnavailableException if the product is not currently in stock
     * @throws ProductNotRegisteredException if the product is not registered for sale in the shop
     */
    void buyProduct(String barCode) throws StockUnavailableException, ProductNotRegisteredException;


    /**
     * Gets the number of different products sold by the shop.
     * This does not take stock levels into account.
     *
     * @return the number of different products sold by the shop
     */
    int getNumberOfProducts();


    /**
     * Gets the total count of stock over all products in the shop. For example, returns 3 if
     * the shop has 2 cans of baked beans and 1 loaf of bread in stock.
     *
     * @return the total stock count over all products
     */
    int getTotalStockCount();


    /**
     * Gets the stock count for a particular product.
     *
     * @param barCode the bar code of the product
     * @return the stock count for a particular product
     * @throws ProductNotRegisteredException if the product is not registered for sale in the shop
     */
    int getStockCount(String barCode) throws ProductNotRegisteredException;


    /**
     * Gets the total number of times that a given product was bought.
     *
     * @param barCode the bar code of the product
     * @return the total number of times that the product has been bought
     * @throws ProductNotRegisteredException if the product is not registered for sale in the shop
     */
    int getNumberOfSales(String barCode) throws ProductNotRegisteredException;


    /**
     * Gets the product that has been bought the greatest number of times.
     * If there is not a single most popular product, then one of the most popular products will be returned.
     *
     * @return the product that has been bought the greatest number of times
     * @throws ProductNotRegisteredException if no products are registered for sale in the shop
     */
    IProduct getMostPopular() throws ProductNotRegisteredException;

}
