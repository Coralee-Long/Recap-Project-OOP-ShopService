import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShopService {
    private  ProductRepo productRepo;
    private  OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                throw new IllegalArgumentException("Product with id " + productId + " does not exist!");
            }
            products.add(productToOrder.get());
        }

        Order newOrder = new Order(
                UUID.randomUUID().toString(),
                products,
                OrderStatus.PROCESSING,
                Instant.now()
                );

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

    public Order updateOrderStatus(String orderId, OrderStatus newStatus) {
       // fetch existing order from repo
        Order order = orderRepo.getOrderById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with id " + orderId + " not found!");
        }
        // create a new Order with the updated status
        Order updatedOrder = order.withStatus(newStatus);

        // update repo with newly updated order
        orderRepo.addOrder(updatedOrder);

        return updatedOrder;
    }
}
