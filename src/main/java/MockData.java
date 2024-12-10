import java.time.Instant;
import java.util.List;

public class MockData {

    public static ProductRepo setupProductRepo() {
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("1", "Apfel"));
        productRepo.addProduct(new Product("2", "Milch"));
        productRepo.addProduct(new Product("3", "Wasser"));
        productRepo.addProduct(new Product("4", "Brot"));
        return productRepo;
    }

    public static OrderRepo setupOrderRepo(ProductRepo productRepo) {
        OrderRepo orderRepo = new OrderListRepo();
        orderRepo.addOrder(new Order("101", List.of(
                productRepo.getProductById("1").get(), // Extract Product from Optional
                productRepo.getProductById("2").get()), OrderStatus.PROCESSING, Instant.parse("2024-01-01T10:15:30Z")));
        orderRepo.addOrder(new Order("102", List.of(
                productRepo.getProductById("3").get()), OrderStatus.IN_DELIVERY,  Instant.parse("2024-01-02T12:00:00Z")));
        orderRepo.addOrder(new Order("103", List.of(
                productRepo.getProductById("4").get(),
                productRepo.getProductById("2").get()), OrderStatus.COMPLETED, Instant.parse("2024-01-03T14:30:00Z")));
        orderRepo.addOrder(new Order("104", List.of(
                productRepo.getProductById("1").get(),
                productRepo.getProductById("3").get(),
                productRepo.getProductById("4").get()), OrderStatus.PROCESSING, Instant.parse("2024-01-04T08:45:00Z")));
        return orderRepo;
    }
}

