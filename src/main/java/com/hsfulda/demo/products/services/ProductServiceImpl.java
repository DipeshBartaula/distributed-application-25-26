package com.hsfulda.demo.products.services;

import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getProductList() {
        return repository.findAll();
    }
    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Product with product id:"+id+" not found"));
    }

    public List<Product> getProductByColor(String color) {
        return repository.findByColor(color);
    }

    public List<Product> getProductByCategoryAndSize(String category, String size) {
        return getProductList().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category) && product.getSize().equalsIgnoreCase(size))
                .toList();
    }

    @Override
    public Optional<Product> addNewProduct(Product product) {
        Optional<Product> existingProductOptional = repository.findById(product.getId());
        if(!existingProductOptional.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(repository.save(product));
        }
    }

    @Override
    public String deleteProduct(Long id) {
        repository.deleteById(id);
        return "Product with product id :" + id +" deleted";
    }

    @Override
    public Optional<Product> updateProduct(Product updatedProduct) {
        Optional<Product> existingProductOptional = repository.findById(updatedProduct.getId());
        if(existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setSize(updatedProduct.getSize());
            existingProduct.setColor(updatedProduct.getColor());
            existingProduct.setCategory(updatedProduct.getCategory());

            Product savedProduct = repository.save(existingProduct);
            return Optional.of(savedProduct);
        } else {
            return Optional.empty();
        }
    }
}
