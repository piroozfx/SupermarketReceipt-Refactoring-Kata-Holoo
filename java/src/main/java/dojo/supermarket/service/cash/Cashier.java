package dojo.supermarket.service.cash;

import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.offer.SpecialOfferType;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductQuantity;
import dojo.supermarket.service.cart.ShoppingCart;
import dojo.supermarket.service.cart.SupermarketCatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cashier {

    private final SupermarketCatalog catalog;
    private final Map<Product, Offer> offers = new HashMap<>();

    public Cashier(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        offers.put(product, new Offer(offerType, product, argument));
    }

    public Receipt issueReceipt(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = theCart.getItems();
        for (ProductQuantity pq : productQuantities) {
            Product p = pq.getProduct();
            double quantity = pq.getQuantity();
            double unitPrice = catalog.getUnitPrice(p);
            double price = quantity * unitPrice;
            receipt.addProduct(p, quantity, unitPrice, price);
        }
        theCart.applyOffers(receipt, offers, catalog);

        return receipt;
    }
}
