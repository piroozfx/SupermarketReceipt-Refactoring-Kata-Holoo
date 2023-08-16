package dojo.supermarket.model;

import dojo.supermarket.model.offer.SpecialOfferType;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductUnit;
import dojo.supermarket.service.cash.Cashier;
import dojo.supermarket.service.cash.Receipt;
import dojo.supermarket.service.cart.ShoppingCart;
import dojo.supermarket.service.cart.SupermarketCatalog;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupermarketTest {

    // Todo: test all kinds of discounts are applied properly

    @Test
    public void tenPercentDiscount() {
        SupermarketCatalog catalog = new MockCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.KILO);
        catalog.addProduct(apples, 1.99);

        Cashier cashier = new Cashier(catalog);
        cashier.addSpecialOffer(SpecialOfferType.TEN_PERCENT_DISCOUNT, toothbrush, 10.0);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);

        // ACT
        Receipt receipt = cashier.issueReceipt(cart);

        // ASSERT
        assertEquals(4.975, receipt.getTotalPrice(), 0.01);
        assertEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(apples, receiptItem.getProduct());
        assertEquals(1.99, receiptItem.getPrice());
        assertEquals(2.5 * 1.99, receiptItem.getTotalPrice());
        assertEquals(2.5, receiptItem.getQuantity());

    }


}
