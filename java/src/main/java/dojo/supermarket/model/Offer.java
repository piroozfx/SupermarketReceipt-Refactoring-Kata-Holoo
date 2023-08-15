package dojo.supermarket.model;

public class Offer {

    private SpecialOfferType offerType;
    private final Product product;
    private double argument;

    public Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    public SpecialOfferType getOfferType() {
        return offerType;
    }

    public double getArgument() {
        return argument;
    }

    Product getProduct() {
        return product;
    }
}
