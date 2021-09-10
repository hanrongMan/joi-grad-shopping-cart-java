package com.thoughtworks.codepairing.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * 实现一组产品的报价的功能，比如“买二送一”的特殊商品将以“BULK_BUY_2_GET_1”开头的产品代码作为标识（同一商品）。
 * <p>
 * 这里需要注意的关键点：
 * 需要是同一商品满足条件才能触发，同一商品是通过商品标识来区分的
 * 买二送一不需要自动增加新的商品，比如：买两个，自动加一个
 * 买二送一的理解是可以灵活改变的，面试官可以根据自己的理解或喜好设置，常见参考如下：
 * 买三个触发折扣，减少一个的钱(买三免一)
 * 买两个就可以触发折扣，是 33.3% 左右
 * <p>
 * Tasking
 * Should apply discount when there are two buy_2_get_1 items (以买两个就可以触发折扣为例子，是 33.33%, 此处可灵活调整，product code 默认设置一样的，但不需要主动highlight 出来)
 * Should not apply discount when there are two buy_2_get_1 items with different product code (上一个默认使用一个product code，这里设置不同的）
 * Should apply discount when there are three buy_2_get_1 items with same product code
 * Should apply discount when there are two buy_2_get_1 items for each two different products
 * …… if needed
 */
public class ProductOfBulkBuy2Get1Test {
    public static final int PRICE = 100;
    public static final String PRODUCT = "Product_BULK_BUY_2_GET_1";
    public static final String PRODUCT_CODE = "BULK_BUY_2_GET_1";

    Customer customer;
    Product product;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("test");
        product = new Product(PRICE, PRODUCT_CODE, PRODUCT);
    }

    /**
     * 不需要修改代码即可通过
     */
    @Test
    public void ShouldNotApplyDiscountWhenThereIsOneBuy2get1Item() {
        List<Product> products = asList(product);
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(100, order.getTotalPrice(), 0);
    }

    /**
     * (以买两个就可以触发折扣为例子，是 33.33%, 此处可灵活调整，product code 默认设置一样的，但不需要主动highlight 出来)
     */
    @Test
    public void ShouldApplyDiscountWhenThereAreTwoBuy2get1Items() {
        List<Product> products = asList(product, product);
        ShoppingCart cart = new ShoppingCart(customer, products);
        Order order = cart.checkout();

        assertEquals(100, order.getTotalPrice(), 0);
    }


}
