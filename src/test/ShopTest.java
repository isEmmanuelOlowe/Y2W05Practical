package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import common.AbstractFactoryClient;
import common.BarCodeAlreadyInUseException;
import common.ProductNotRegisteredException;
import common.StockUnavailableException;
import interfaces.IProduct;
import interfaces.IShop;

/**
* This is a junit class to test the Shop ADT.
*/
public class ShopTest extends AbstractFactoryClient {

  private IShop shop;
  private IProduct product;
  /**
  * Creates the Shop object that will be tested.
  */
  @BeforeEach
  public void createShop() {
    shop = getFactory().makeShop();
    product = getFactory().makeProduct("1234567", "Laptop");
  }
  /**
  * Checsk you are able to register a product.
  *
  * @throws BarCodeAlreadyInUseException If barcode is being used by another product
  */
  @Test
  public void shopTestRegister() throws BarCodeAlreadyInUseException {
    shop.registerProduct(product);
  }

  /**
  * Checks you cannot register two products with the same barcode.
  */
  @Test
  public void shopCannotRegister() {
    IShop shop = getFactory().makeShop();
    IProduct product2 = getFactory().makeProduct("1234567", "Laser Gun");

    assertThrows(BarCodeAlreadyInUseException.class, () -> {
      shop.registerProduct(product);
      shop.registerProduct(product2);
    });
  }
  /**
  * Checks the default number of products is zero.
  */
  @Test
  public void shopDefaultProducts() {
    final int initialZero = 0;
    IShop shop = getFactory().makeShop();
    assertEquals(initialZero, shop.getNumberOfProducts());
  }

  /**
  * Tests when you register a product the number of products increases.
  */
  public void shopRegisterNoProducts() {
    final int addedOne = 1;
    IShop shop = getFactory().makeShop();
    assertEquals(addedOne, shop.getNumberOfProducts());
  }

  /**
  * Checks you can unregister a registered product.
  *
  * @throws BarCodeAlreadyInUseException in the event the barcode is being used by another product.
  * @throws ProductNotRegisteredException in the event the product is not registered.
  */
  @Test
  public void shopUnregisterRegistered() throws BarCodeAlreadyInUseException, ProductNotRegisteredException {
    shop.registerProduct(product);
    shop.unregisterProduct(product);
  }

  /**
  * Checks when you unregister a product the number of products decreases.
  *
  * @throws BarCodeAlreadyInUseException in the event the barcode is being used by another product.
  * @throws ProductNotRegisteredException in the event the product is not registered.
  */
  @Test
  public void shopUnregisterDecreaseNoProducts() throws BarCodeAlreadyInUseException, ProductNotRegisteredException {
    final int noOfProductsIsZero = 0;
    shop.registerProduct(product);
    shop.unregisterProduct(product);
    assertEquals(noOfProductsIsZero, shop.getNumberOfProducts());
  }

  /**
  * Checks you cannot unregister a product which is not present in shop.
  */
  @Test
  public void shopCannotUnregister() {
    assertThrows(ProductNotRegisteredException.class, () -> {
      shop.unregisterProduct(product);
    });
  }

  /**
  * Checks you are able to add stock to a registered product.
  *
  * @throws BarCodeAlreadyInUseException in the event the barcode is being used by another product.
  * @throws ProductNotRegisteredException in the event the product is not registered.
  */
  @Test
  public void shopAddStock() throws ProductNotRegisteredException, BarCodeAlreadyInUseException {
    shop.registerProduct(product);
    shop.addStock(product.getBarCode());
  }

  /**
  * Checks that you cant add stock to a product which is not registered.
  *
  * @throws BarCodeAlreadyInUseException in the event the barcode is being used by another product.
  */
  @Test
  public void shopCannotAddStock() throws BarCodeAlreadyInUseException {
    assertThrows(ProductNotRegisteredException.class, () -> {
      shop.addStock(product.getBarCode());
    });
  }

  /**
  * Checks that you cna get the number of products.
  */
  @Test
  public void shopGetTotalStock() {
    final int initiallyZero = 0;
    assertEquals(initiallyZero, shop.getTotalStockCount());
  }

  /**
  * Checks that adding stock to a product increase the total stock count.
  *
  * @throws BarCodeAlreadyInUseException in the event the barcode is being used by another product.
  * @throws ProductNotRegisteredException in the event the product is not registered.
  */
  @Test
  public void shopAddStockIncreasesTotalStock() throws ProductNotRegisteredException, BarCodeAlreadyInUseException {
    final int addedOne = 1;
    shop.registerProduct(product);
    shop.addStock(product.getBarCode());
    assertEquals(addedOne, shop.getTotalStockCount());
  }

  /**
  * Checks you are able to buy a registered Product.
  *
  * @throws BarCodeAlreadyInUseException in the event the barcode is being used by another product.
  * @throws ProductNotRegisteredException in the event the product is not registered.
  * @throws StockUnavailableException if there is no stock avaliable to be bought.
  */
  @Test
  public void shopBuyProudct() throws BarCodeAlreadyInUseException, ProductNotRegisteredException, StockUnavailableException {
    shop.registerProduct(product);
    shop.addStock(product.getBarCode());
    shop.buyProduct(product.getBarCode());
  }

  /**
  * Checks you cant buy a product from a product which is not registered.
  *
  * @throws StockUnavailableException in the event there is no stuck of the product
  */
  @Test
  public void shopCanntBuyUnregisteredProduct() throws StockUnavailableException {
    assertThrows(ProductNotRegisteredException.class, () -> {
      shop.buyProduct(product.getBarCode());
    });
  }

  /**
  * Checks you cannot buy a product with no stock.
  *
  * @throws BarCodeAlreadyInUseException in event barcode is being used by another product
  * @throws ProductNotRegisteredException in event product is not registered.
  */
  @Test
  public void shopCannotBuyFromZeroStock() throws BarCodeAlreadyInUseException, ProductNotRegisteredException {
    shop.registerProduct(product);
    assertThrows(StockUnavailableException.class, () -> {
      shop.buyProduct(product.getBarCode());
    });
  }

  /**
  * Checks that the total stock is decreased by buying a product.
  *
  * @throws BarCodeAlreadyInUseException in the event the barcode is being used by another product.
  * @throws ProductNotRegisteredException in the event the product is not registered.
  * @throws StockUnavailableException if there is no stock avaliable to be bought.
  */
  @Test
  public void shopBuyingReducesTotalStock() throws BarCodeAlreadyInUseException, ProductNotRegisteredException, StockUnavailableException {
    final int noStockLeft = 0;
    shop.registerProduct(product);
    shop.addStock(product.getBarCode());
    shop.buyProduct(product.getBarCode());
    assertEquals(noStockLeft, shop.getTotalStockCount());
  }

  /**
  * Checks that you can obtain the total stock for a specific product.
  *
  * @throws BarCodeAlreadyInUseException in the event the barcode is being used by another product.
  * @throws ProductNotRegisteredException in the event the product is not registered.
  * @throws StockUnavailableException if there is no stock avaliable to be bought.
  */
  @Test
  public void shopGetStock() throws BarCodeAlreadyInUseException, ProductNotRegisteredException, StockUnavailableException {
    final int addedTwo = 2;
    shop.registerProduct(product);
    shop.addStock(product.getBarCode());
    shop.addStock(product.getBarCode());
    assertEquals(addedTwo, shop.getStockCount(product.getBarCode()));
  }

  /**
  * Checks that the total stock of product is decreased by buying a product.
  *
  * @throws BarCodeAlreadyInUseException in the event the barcode is being used by another product.
  * @throws ProductNotRegisteredException in the event the product is not registered.
  * @throws StockUnavailableException if there is no stock avaliable to be bought.
  */
  @Test
  public void shopBuyingReducesProductStock() throws BarCodeAlreadyInUseException, ProductNotRegisteredException, StockUnavailableException {
    final int noStockLeft = 0;
    shop.registerProduct(product);
    shop.addStock(product.getBarCode());
    shop.buyProduct(product.getBarCode());
    assertEquals(noStockLeft, shop.getStockCount(product.getBarCode()));
  }

  /**
  * Checks that you cannt check the stock of an unregistered product.
  */
  @Test
  public void shopCannotGetStockForUnregisteredProduct() {
    assertThrows(ProductNotRegisteredException.class, () -> {
      shop.getStockCount(product.getBarCode());
    });
  }

  /**
  * Checks you can obtain the sales of a specific product.
  *
  * @throws BarCodeAlreadyInUseException in the event the barcode is being used by another product.
  * @throws ProductNotRegisteredException in the event the product is not registered.
  * @throws StockUnavailableException if there is no stock avaliable to be bought.
  */
  @Test
  public void shopGetSales() throws BarCodeAlreadyInUseException, ProductNotRegisteredException, StockUnavailableException {
    final int boughtTwo = 2;
    shop.registerProduct(product);
    shop.addStock(product.getBarCode());
    shop.addStock(product.getBarCode());
    shop.buyProduct(product.getBarCode());
    shop.buyProduct(product.getBarCode());
    assertEquals(boughtTwo, shop.getNumberOfSales(product.getBarCode()));
  }

  /**
  * Checks you can get sales of unregistered products.
  */
  @Test
  public void shopCannotGetSales() {
    assertThrows(ProductNotRegisteredException.class, () -> {
      shop.getNumberOfSales(product.getBarCode());
    });
  }

  /**
  * Checks you can get the most popular item from a shop.
  *
  * @throws BarCodeAlreadyInUseException in the event the barcode is being used by another product.
  * @throws ProductNotRegisteredException in the event the product is not registered.
  * @throws StockUnavailableException in the event there is no stock to be bought
  */
  @Test
  public void shopMostPopular() throws BarCodeAlreadyInUseException, ProductNotRegisteredException, StockUnavailableException {
    IProduct product2 = getFactory().makeProduct("21", "Laser Robot Machine of Some Sort");
    shop.registerProduct(product);
    shop.registerProduct(product2);
    shop.addStock(product.getBarCode());
    shop.addStock(product2.getBarCode());
    shop.addStock(product2.getBarCode());
    shop.buyProduct(product2.getBarCode());
    assertEquals(product2, shop.getMostPopular());
  }

  /**
  * Checks you can get the most popular item from a shop when no sales for any items.
  *
  * @throws BarCodeAlreadyInUseException in the event the barcode is being used by another product.
  * @throws ProductNotRegisteredException in the event the product is not registered.
  */
  @Test
  public void shopMostPopularEvenWithNoSales() throws BarCodeAlreadyInUseException, ProductNotRegisteredException {
    shop.registerProduct(product);
    assertEquals(product, shop.getMostPopular());
  }

  /**
  * Ensures you cant get the most popular item if there are no items in the shop.
  */
  @Test
  public void shopCantGetMostPopular() {
    assertThrows(ProductNotRegisteredException.class, () -> {
      shop.getMostPopular();
    });
  }


  /**
  * Checks when a prodyuct is unregistered all of its stock is removed from the total stock.
  *
  * @throws ProductNotRegisteredException in the event the product is not registered.
  * @throws BarCodeAlreadyInUseException in the event the barcode of the product is in use.
  */
  @Test
  public void shopStockIsRemoved() throws ProductNotRegisteredException, BarCodeAlreadyInUseException {
    final int stockIsZero = 0;
    shop.registerProduct(product);
    shop.addStock(product.getBarCode());
    shop.unregisterProduct(product);
    assertEquals(stockIsZero, shop.getTotalStockCount());
  }
}
