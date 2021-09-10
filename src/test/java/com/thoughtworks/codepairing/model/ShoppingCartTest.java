package com.thoughtworks.codepairing.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    public static final int PRICE = 100;
    public static final String PRODUCT = "Product";

    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("test");
    }

    @Test
    public void shouldCalculatePriceWithNoDiscount() {
        List<Product> products = asList(new Product(PRICE, "", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(100.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsWithNoDiscount() {
        List<Product> products = asList(new Product(PRICE, "", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(20, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor10PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(90.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor10PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_10_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(10, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor15PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(85.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor15PercentDiscount() {
        List<Product> products = asList(new Product(PRICE, "DIS_15_ABCD", PRODUCT));
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(6, order.getLoyaltyPoints());
    }


//    实现如下功能，满足20%折扣的商品将以“ DIS_20”开头的产品代码作为标识。购买此类产品后，客户每消费$20可赚取1点会员积分。
//    实现一组产品的报价的功能，比如“买二送一”的特殊商品将以“BULK_BUY_2_GET_1”开头的产品代码作为标识（同一商品）。
//    实现如下功能，当购买总价超过$500，将给予5%的折扣。

//
//    问题上下文
//
//    实现一组产品的报价的功能，比如“买二送一”的特殊商品将以“BULK_BUY_2_GET_1”开头的产品代码作为标识（同一商品）。
//
//    这里需要注意的关键点：
//    需要是同一商品满足条件才能触发，同一商品是通过商品标识来区分的
//    买二送一不需要自动增加新的商品，比如：买两个，自动加一个
//    买二送一的理解是可以灵活改变的，面试官可以根据自己的理解或喜好设置，常见参考如下：
//    买三个触发折扣，减少一个的钱
//    买两个就可以触发折扣，是 33.3% 左右
//
//            Tasking
//    Should not apply discount when there is one buy_2_get_1 item (不需要改代码即可通过的测试)
//    Should apply discount when there are two buy_2_get_1 items (以买两个就可以触发折扣为例子，是 33.33%, 此处可灵活调整，product code 默认设置一样的，但不需要主动highlight 出来)
//    Should not apply discount when there are two buy_2_get_1 items with different product code (上一个默认使用一个product code，这里设置不同的）
//            Should apply discount when there are three buy_2_get_1 items with same product code
//            Should apply discount when there are two buy_2_get_1 items for each two different products
//            …… if needed

}
