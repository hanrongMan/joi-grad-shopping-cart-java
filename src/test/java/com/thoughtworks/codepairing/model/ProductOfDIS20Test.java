package com.thoughtworks.codepairing.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * 满足20%折扣的商品将以“DIS_20”开头的产品代码作为标识。购买此类产品后，客户每消费$20可赚取1点会员积分。
 */
public class ProductOfDIS20Test {
    public static final int PRICE = 20;
    public static final String PRODUCT = "Product_DIS_20";

    Customer customer;
    Product productDis20;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("test");
        productDis20=new  Product(PRICE, "DIS_20", PRODUCT);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor20PercentDiscount() {
        List<Product> products = asList(productDis20);
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(1, order.getLoyaltyPoints(), 0);
    }

    @Test
    public void shouldCalculatePriceFor20PercentDiscount() {
        List<Product> products = asList(productDis20,productDis20);
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(32, order.getTotalPrice(), 0);
    }
}
