package dojo.supermarket.service.cart;

import dojo.supermarket.model.ReceiptItem;
import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.offer.SpecialOfferType;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductQuantity;
import dojo.supermarket.service.cash.Receipt;
import dojo.supermarket.service.offer.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ShoppingCart {
    private OfferService offerService;
    private final List<ProductQuantity> items = new ArrayList<>();
    private final Map<Product, Double> productQuantities = new HashMap<>();

    public List<ProductQuantity> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(Product product) {
        addItemQuantity(product, 1.0);
    }

    private Map<Product, Double> productQuantities() {
        return Collections.unmodifiableMap(productQuantities);
    }

    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    public void applyOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {

        List<ReceiptItem> receiptItems = receipt.getItems();
        for (ReceiptItem item : receiptItems) {
            Product product = item.getProduct();
            double quantity = item.getQuantity();
            double unitPrice = catalog.getUnitPrice(product);
            if (offers.containsKey(product)) {
                Offer offer = offers.get(product);
                switch (offer.getOfferType()) {
                    case FIVE_FOR_AMOUNT:
                        offerService = new OfferFiveForAmount();
                    case TEN_PERCENT_DISCOUNT:
                        offerService = new OfferTenPercentDiscount();
                    case THREE_FOR_TWO:
                        offerService = new OfferThreeForTwo();
                    case TWO_FOR_AMOUNT:
                        offerService = new OfferTwoForAmount();
                }
                Discount discount = offerService.applyOffer(quantity, unitPrice, offer, product);
                if (discount != null)
                    receipt.addDiscount(discount);
            }
        }
    }
}
