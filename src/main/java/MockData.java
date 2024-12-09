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
                productRepo.getProductById("1"),
                productRepo.getProductById("2")), OrderStatus.PROCESSING));
        orderRepo.addOrder(new Order("102", List.of(
                productRepo.getProductById("3")), OrderStatus.IN_DELIVERY));
        orderRepo.addOrder(new Order("103", List.of(
                productRepo.getProductById("4"),
                productRepo.getProductById("2")), OrderStatus.COMPLETED));
        orderRepo.addOrder(new Order("104", List.of(
                productRepo.getProductById("1"),
                productRepo.getProductById("3"),
                productRepo.getProductById("4")), OrderStatus.PROCESSING));
        return orderRepo;
    }
}

