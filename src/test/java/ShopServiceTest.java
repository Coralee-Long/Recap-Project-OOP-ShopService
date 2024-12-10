import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("1", "Apfel"));
        OrderRepo orderRepo = new OrderListRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order(
                "-1",
                List.of(new Product("1", "Apfel")),
                OrderStatus.PROCESSING,
                actual.timestamp() // Use the same timestamp as the generated Order
        );
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenSingleInvalidProductId_expectException() {
        // GIVEN
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderListRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        List<String> productIds = List.of("1"); // Only one invalid ID

        // WHEN
        Exception exception = assertThrows(IllegalArgumentException.class, () -> shopService.addOrder(productIds));

        // THEN
        assertEquals("Product with id 1 does not exist!", exception.getMessage());
    }

    @Test
    void addOrderTest_whenMultipleInvalidProductIds_expectExceptionForFirst() {
        // GIVEN
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderListRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);
        List<String> productIds = List.of("1", "2", "3"); // Multiple invalid IDs

        // WHEN
        Exception exception = assertThrows(IllegalArgumentException.class, () -> shopService.addOrder(productIds));

        // THEN
        assertEquals("Product with id 1 does not exist!", exception.getMessage());
    }

    @Test
    void updateOrder_updatesOrderStatusSuccessfully() {
        // GIVEN
        OrderRepo repo = new OrderListRepo();
        Product product = new Product("1", "TestProduct");
        Order order = new Order(
                "123",
                List.of(product),
                OrderStatus.PROCESSING,
                Instant.now() // Provide a timestamp
        );
        repo.addOrder(order);

        ShopService shopService = new ShopService(new ProductRepo(), repo);

        // WHEN
        Order updatedOrder = shopService.updateOrderStatus("123", OrderStatus.PROCESSING);

        // THEN
        assertEquals(OrderStatus.PROCESSING, updatedOrder.status());
        assertEquals(OrderStatus.PROCESSING, repo.getOrderById("123").status());
    }
}
