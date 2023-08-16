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

public class OfferTwoForAmount implements OfferService {
    @Override
    public Discount applyOffer(double quantity, double unitPrice, Offer offer, Product p) {
        if (offer.getOfferType() == SpecialOfferType.TWO_FOR_AMOUNT) {
            double total = offer.getArgument() * ((int) quantity / 2) + (int) quantity % 2 * unitPrice;
            double discountN = unitPrice * quantity - total;
            Discount discount = new Discount(p, "2 for " + offer.getArgument(), -discountN);
            return discount;
        } else return null;

    }
}
