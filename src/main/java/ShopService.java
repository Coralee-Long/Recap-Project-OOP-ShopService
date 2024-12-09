import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopService {
    private ProductRepo productRepo;
    private OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId);
            if (productToOrder == null) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

   //   Write a method that returns a list of all orders with a specific order status (parameter) using streams.
    public List<Order> getOrdersByOrderStatus(OrderStatus orderStatus) {
        // get all the orders from repo
        List<Order> orders = orderRepo.getOrders();

        // filter by orderStatus
        return orders.stream()
                .filter(order -> order.status().equals(orderStatus))
                .toList();
    }
}
