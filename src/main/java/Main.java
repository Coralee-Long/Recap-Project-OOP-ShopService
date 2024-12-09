import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Step 1: Set up repositories using MockData
        ProductRepo productRepo = MockData.setupProductRepo();
        OrderRepo orderRepo = MockData.setupOrderRepo(productRepo);

        // Step 2: Create ShopService
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // Step 3: Call the WIP function to test it
        List<Order> filteredOrders = shopService.getOrdersByOrderStatus(OrderStatus.PROCESSING);
        System.out.println("Filtered Orders: " + filteredOrders);
    }
}
