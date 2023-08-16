package dojo.supermarket.service.offer;

import dojo.supermarket.model.ReceiptItem;
import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.offer.SpecialOfferType;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.service.cart.SupermarketCatalog;
import dojo.supermarket.service.cash.Receipt;

import java.util.List;
import java.util.Map;

public class OfferThreeForTwo implements OfferService {
    @Override
    public Discount applyOffer(double quantity, double unitPrice, Offer offer, Product p) {
        int numberOfXs = (int) quantity / 3;
        int quantityAsInt = (int) quantity;

        if (offer.getOfferType() == SpecialOfferType.THREE_FOR_TWO && quantityAsInt > 2) {

            double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + (int) quantity % 3 * unitPrice);
            Discount discount = new Discount(p, "3 for 2", -discountAmount);
            return discount;
        } else return null;
    }
}

