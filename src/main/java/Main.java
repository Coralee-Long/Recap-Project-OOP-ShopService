import java.time.Instant;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Set up ProductRepo and OrderRepo using MockData
        ProductRepo productRepo = MockData.setupProductRepo();
        OrderRepo orderRepo = MockData.setupOrderRepo(productRepo);

        // Create an instance of ShopService
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // Define three concrete orders and add them to the ShopService
        Order order1 = new Order(
                "201",
                List.of(productRepo.getProductById("1").get()), // Apfel
                OrderStatus.PROCESSING,
                Instant.now()
        );

        Order order2 = new Order(
                "202",
                List.of(productRepo.getProductById("2").get(), productRepo.getProductById("3").get()), // Milch, Wasser
                OrderStatus.IN_DELIVERY,
                Instant.now()
        );

        Order order3 = new Order(
                "203",
                List.of(productRepo.getProductById("4").get()), // Brot
                OrderStatus.COMPLETED,
                Instant.now()
        );

        // Add orders to the ShopService
        shopService.addOrder(List.of("1")); // Adds order with Apfel
        orderRepo.addOrder(order1);
        orderRepo.addOrder(order2);
        orderRepo.addOrder(order3);


        // Filter and display orders with status PROCESSING
        List<Order> filteredOrders = shopService.getOrdersByOrderStatus(OrderStatus.PROCESSING);
        System.out.println("Filtered Orders: " + filteredOrders);
    }
}
