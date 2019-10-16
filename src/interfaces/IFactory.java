package interfaces;


/**
 * Interface for a factory abstracting over instantiation of other interface types.
 *
 */
public interface IFactory {

    /**
     * Creates an instance of {@link IProduct}.
     * @param barCode the bar code of the product
     * @param description the description of the product
     * @return the product
     */
    IProduct makeProduct(String barCode, String description);


    /**
     * This method creates an instance of {@link IStockRecord} for a new product.
     * @param product the product to use for this stock record
     * @return the stock record
     */
    IStockRecord makeStockRecord(IProduct product);


    /**
     * Creates an instance of {@link IShop}.
     *
     * @return the shop
     */
    IShop makeShop();


}
