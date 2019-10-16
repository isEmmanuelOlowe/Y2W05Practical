# W05 Practical Report

## Overview

The specification has required that Test-Driven Development be used to implement a selection of interfaces which have been provided. Suitable tests much be write which test the functionality of the implementation of the interfaces. Only areas with `//TODO` comments are to be implemented, but it is assumed that additional methods can be added to add the development of the implemented classes. it is also assumed that all the tests must appear in the 1 test class that exists. Interfaces are not to be changed. Test cases to be implemented:

* Normal : Expected input.
* Edge Case (or Extreme) : empty collections, duplicate bar codes, no stock.
* Exceptional cases: dealing with null values.

## Design

### `Product`

To tests if the product class fulfils its functionality. To the following tests were developed.

#### `productCreationNonNull`

Checks that the instantiation of the product class is not null and returns an object.

#### `productBarcode`

Tests that when a product is created that its barcode can be retrieved from the object.

#### `productDescription`

Tests that when a product is created that its description can be retrieved from the object.

### `StockRecord`

#### `stockRecordCreationNonNull`

Checks that the instantiation of the product class is not null and returns an object.

#### `stockRecordInitallyZero`

Checks that the initial stock is zero.

#### `stockRecordAddSingleStock`

Checks that the amount of stock is incremented when the `addStock()` method is called

#### `stocRecordAddMultipleStock`

Checks that the amount of incrementation works for add more than one items to. Further checking if the `addStock()` works properly. 

#### `stockRecordBuyRecducesStock`

Checks when a product is bought that stock is decreased.

#### `stockRecordBuyIncreasesSales`

Checks that the number of sales is incremented when a `buyProduct()` is calleld

#### `stockRecordCantBuyFromZeroStock`

Checks that an exception is called when an attempt to `buyProduct()` if current stock is 0.

#### `stockRecordProductStored`

Checks that the object is stored in the `stockRecord` object.



## Ambiguity

* unregistering a product is checking just the barcode enough. Do they have to be the same object or just have the same name and description.

* Assumes that most popular means the product with the most sales