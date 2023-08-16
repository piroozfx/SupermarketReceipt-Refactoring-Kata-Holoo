package dojo.supermarket.service.offer;

import dojo.supermarket.model.discount.Discount;
import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.product.Product;


public interface OfferService {

    Discount applyOffer(double quantity, double unitPrice, Offer offer, Product p);
}
