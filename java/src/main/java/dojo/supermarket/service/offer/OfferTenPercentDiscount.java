package dojo.supermarket.service.offer;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.offer.SpecialOfferType;
import dojo.supermarket.model.product.Product;

public class OfferTenPercentDiscount implements OfferService {

    @Override
    public Discount applyOffer(double quantity, double unitPrice, Offer offer, Product p) {
        if (offer.getOfferType() == SpecialOfferType.TEN_PERCENT_DISCOUNT) {
            Discount discount = new Discount(p, offer.getArgument() + "% off", -quantity * unitPrice * offer.getArgument() / 100.0);
            return discount;
        } else return null;
    }
}
