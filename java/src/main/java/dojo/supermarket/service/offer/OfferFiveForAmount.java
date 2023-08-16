package dojo.supermarket.service.offer;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.offer.SpecialOfferType;
import dojo.supermarket.model.product.Product;

public class OfferFiveForAmount implements OfferService {
    @Override
    public Discount applyOffer(double quantity, double unitPrice, Offer offer, Product p) {
        int quantityAsInt = (int) quantity;
        int numberOfXs = quantityAsInt / 5;
        if (offer.getOfferType() == SpecialOfferType.FIVE_FOR_AMOUNT && quantityAsInt >= 5) {
            double discountTotal = unitPrice * quantity - (offer.getArgument() * numberOfXs + quantityAsInt % 5 * unitPrice);
            Discount discount = new Discount(p, 5 + " for " + offer.getArgument(), -discountTotal);
            return discount;
        } else return null;
    }
}
