import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @org.junit.jupiter.api.Test
    void getProducts() {
        // GIVEN
        ProductRepo repo = new ProductRepo();
        repo.addProduct(new Product("1", "Apfel")); // Add a product

        // WHEN
        List<Product> actual = repo.getProducts();

        // THEN
        List<Product> expected = List.of(new Product("1", "Apfel")); // Expect the added product
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getProductById() {
        // GIVEN
        ProductRepo repo = new ProductRepo();
        repo.addProduct(new Product("1", "Apfel")); // Add a product to the repo

        // WHEN
        Optional<Product> actual = repo.getProductById("1");

        // THEN
        assertTrue(actual.isPresent()); // Ensure the Optional is present
        assertEquals(new Product("1", "Apfel"), actual.get()); // Check the product inside the Optional
    }

    @org.junit.jupiter.api.Test
    void addProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        Product newProduct = new Product("2", "Banane");

        //WHEN
        Product actual = repo.addProduct(newProduct);

        //THEN
        Product expected = new Product("2", "Banane");

        assertEquals(expected, actual); // Check the returned Product
        Optional<Product> retrievedProduct = repo.getProductById("2"); // Retrieve using getProductById
        assertTrue(retrievedProduct.isPresent()); // Ensure the Optional is not empty
        assertEquals(expected, retrievedProduct.get()); // Compare the actual Product inside the Optional
    }

    @org.junit.jupiter.api.Test
    void removeProduct() {
        // GIVEN
        ProductRepo repo = new ProductRepo();
        repo.addProduct(new Product("1", "Apfel")); // Add a product to the repo

        // WHEN
        repo.removeProduct("1");

        // THEN
        Optional<Product> actual = repo.getProductById("1");
        assertTrue(actual.isEmpty()); // Ensure the product is removed
    }
}
