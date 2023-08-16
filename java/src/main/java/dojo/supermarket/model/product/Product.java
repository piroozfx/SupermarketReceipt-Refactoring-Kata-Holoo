package dojo.supermarket.model.product;

import java.util.Objects;

public record Product(String name, ProductUnit unit) {

    public String getName() {
        return name;
    }

    public ProductUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                unit == product.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit);
    }
}
