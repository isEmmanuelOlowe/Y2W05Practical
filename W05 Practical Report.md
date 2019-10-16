# W05 Practical Report

## Overview

The specification has required that Test-Driven Development be used to implement a selection of interfaces which have been provided. Suitable tests much be write which test the functionality of the implementation of the interfaces. Only areas with `//TODO` comments are to be implemented, but it is assumed that additional methods can be added to add the development of the implemented classes. it is also assumed that all the tests must appear in the 1 test class that exists. Interfaces are not to be changed. Test cases to be implemented:

* Normal : Expected input.
* Edge Case (or Extreme) : empty collections, duplicate bar codes, no stock.
* Exceptional cases: dealing with null values.

## Design

### `IFactory`

It can be seen for the specification that to implement this class fully all that is required is that the methods return objects which implement the interface of the return of the methods.

### `IProduct`

To implement this method all that is required is some way to store a barcode assumed to be string and description which is also a string for the object. It has been defined that if two products have the same barcode and same description then they must be the same object. So essential `equals()` must be override to implement the objects to check if two objects are the same. 

### `IStockRecord`

To fully implement this interface; there must be a method of storing the `IProduct` that this stock record is for, a way to store and get the total stock count, get the number of sales which have occurred, add stock (assumed only 1 added at a time) and buy products (assumed only 1 bought at a time). It can also be seen that when an attempt to buy a product when there is no stock an exception is thrown so a way to determine if there is no stock and throw this exception must be made.

### `IShop`

To fully implement this interface;

* There must be a method which registers product; so when this method is called the total stock count must increase by 1. Also this method must throw an exception if a product with the same bar code is attempted to be added.
* There must be a method which 

### `FactoryTest`

This class was designed to Test the factory method. Only methods present in the `IFactory`  interface were tested.  Essential the point of the factory here is to return object which implements the given interface so tests were created to see if factory completed its function.

#### `productCreationNonNull`

Checks that the factory returns an `IProduct` object which implements the interface and not null.

#### `stockRecordCreationNonNull`

Checks that factory returns an `IStockRecord` object which implements the interface and not null.

#### `shopCreationNonNull`

Checks that the creation of a shop is an `IShop` object which implements the interface and not null.

### `ProductTest`

This class class was designed to Test the factory method. Only methods present in the `IProduct` interface will be tested.

`Product`

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