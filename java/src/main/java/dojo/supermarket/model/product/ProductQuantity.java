package dojo.supermarket.model.product;

public record ProductQuantity(Product product, double quantity) {

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }
}
