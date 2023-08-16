package dojo.supermarket.model.discount;

import dojo.supermarket.model.product.Product;

public record Discount(Product product, String description,
                       double discountAmount) {

    public String getDescription() {
        return description;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public Product getProduct() {
        return product;
    }
}
