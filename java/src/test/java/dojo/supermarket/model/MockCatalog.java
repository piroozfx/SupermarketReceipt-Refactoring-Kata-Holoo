package dojo.supermarket.model;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.service.cart.SupermarketCatalog;
import java.util.HashMap;
import java.util.Map;

public class MockCatalog implements SupermarketCatalog {
    private Map<String, Product> products = new HashMap<>();
    private Map<String, Double> prices = new HashMap<>();

    @Override
    public void addProduct(Product product, double price) {
        this.products.put(product.getName(), product);
        this.prices.put(product.getName(), price);
    }

    @Override
    public double getUnitPrice(Product p) {
        return this.prices.get(p.getName());
    }
}
