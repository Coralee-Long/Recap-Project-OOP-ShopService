import java.util.List;

public class Main {
    public static void main(String[] args) {

        ProductRepo productRepo = MockData.setupProductRepo();
        OrderRepo orderRepo = MockData.setupOrderRepo(productRepo);


        ShopService shopService = new ShopService(productRepo, orderRepo);


        List<Order> filteredOrders = shopService.getOrdersByOrderStatus(OrderStatus.PROCESSING);
        System.out.println("Filtered Orders: " + filteredOrders);
    }
}
