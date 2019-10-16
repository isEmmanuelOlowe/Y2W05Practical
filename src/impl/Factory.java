package impl;

import interfaces.IFactory;
import interfaces.IProduct;
import interfaces.IShop;
import interfaces.IStockRecord;


/**
 * This class implements a singleton factory.
 *
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    @Override
    public IProduct makeProduct(String barCode, String description) {
        return new Product(barCode, description);
    }

    @Override
    public IStockRecord makeStockRecord(IProduct product) {
        return new StockRecord(product);
    }

    @Override
    public IShop makeShop() {
        return new Shop();
    }

}
