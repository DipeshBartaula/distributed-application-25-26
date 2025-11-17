package com.hsfulda.demo.products.services;

import com.hsfulda.demo.products.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    //Hardcoded data
    public static List<Product> productList = new ArrayList<>();
    static {
        productList.addAll(List.of(
                new Product(1, "T-Shirt", 19.99, "M", "Blue","T-shirt"),
                new Product(2, "Jeans", 49.99, "32", "Black","Pants"),
                new Product(3, "Sneakers", 79.99, "42", "White","Shoes"),
                new Product(4, "Jacket", 99.99, "L", "Red","Jacket"),
                new Product(5, "Cap", 14.99, "Free Size", "Green","Accessories")
        ));
    }
    //Method to get all hardcoded product list
    public List<Product> getProductList() {
        return productList;
    }
    // Utility method to get product by id
    public Product getProductById(int id) {
        return getProductList().stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Product> getProductByColor(String color) {
        return getProductList().stream()
                .filter(product -> product.getColor().equalsIgnoreCase(color))
                .toList();
    }

    public List<Product> getProductByCategoryAndSize(String category, String size) {
        return getProductList().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category) && product.getSize().equalsIgnoreCase(size))
                .toList();
    }

    @Override
    public List<Product> addNewProduct(Product product) {
        List<Product> existedProduct = productList.stream().filter(p-> p.getName().equals(product.getName())).toList();
        if(!existedProduct.isEmpty()) {
            return productList;
        } else {
            productList.add(product);
            return productList;
        }
    }

    @Override
    public List<Product> deleteProduct(Long id) {
        productList.removeIf(p->p.getId()==id);
        return productList;
    }

    @Override
    public Optional<Product> updateProduct(Product updatedProduct) {
        int productId = updatedProduct.getId();

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == productId) {
                productList.set(i, updatedProduct);
                return Optional.of(updatedProduct);
            }
        }
        return Optional.empty();
    }
}
