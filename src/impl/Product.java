package impl;

import interfaces.IProduct;

/**
 * This class represents products that can be stocked and sold in a shop.
 *
 */
public class Product implements IProduct {

   private String barcode;
   private String descrption;

   /**
   * Creates a new product
   *
   * @param barcode the barcode of the product
   * @param descrption the descrption of the product
   */
   public Product(String barcode, String descrption) {
     this.barcode = barcode;
     this.descrption = descrption;
   }

   /**
   * Gets the barcode of the product
   *
   * @return the barcode
   */
   @Override
   public String getBarCode() {
       return barcode;
    }


   /**
   * Gets the desciption of the product
   *
   * @return the description of the product
   */
   @Override
    public String getDescription() {
       return descrption;
    }

}
