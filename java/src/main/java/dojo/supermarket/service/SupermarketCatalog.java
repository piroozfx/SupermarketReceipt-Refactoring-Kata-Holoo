package dojo.supermarket.service;

import dojo.supermarket.model.Product;

public interface SupermarketCatalog {

    void addProduct(Product product, double price);

    double getUnitPrice(Product product);
}
