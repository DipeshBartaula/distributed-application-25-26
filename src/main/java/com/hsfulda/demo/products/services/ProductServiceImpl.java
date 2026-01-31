package com.hsfulda.demo.products.services;

import com.hsfulda.demo.config.TenantConfig;
import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.repository.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final TenantConfig tenantConfig;

    public ProductServiceImpl(ProductRepository repository, TenantConfig tenantConfig) {
        this.repository = repository;
        this.tenantConfig = tenantConfig;
    }

    public List<Product> getProductList() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product with product id:" + id + " not found"));
    }

    public List<Product> getProductByColor(String color) {
        return repository.findByColor(color);
    }

    public List<Product> getProductByCategoryAndSize(String category, String size) {
        return getProductList().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category)
                        && product.getSize().equalsIgnoreCase(size))
                .toList();
    }

    @Override
    public Optional<Product> addNewProduct(Product product) {
        Optional<Product> existingProductOptional = repository.findById(product.getId());
        if (!existingProductOptional.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(repository.save(product));
        }
    }

    @Override
    public String deleteProduct(Long id) {
        repository.deleteById(id);
        return "Product with product id :" + id + " deleted";
    }

    @Override
    public Optional<Product> updateProduct(Product updatedProduct) {
        Optional<Product> existingProductOptional = repository.findById(updatedProduct.getId());
        if (existingProductOptional.isPresent()) {
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

    public Page<Product> getPaginatedProducts(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Product> getProductsForTenant(String tenantId) {
        if (tenantId != null && tenantConfig.getMapping() != null) {
            String category = tenantConfig.getMapping().get(tenantId);
            if (category != null) {
                return repository.findByCategory(category);
            }
        }
        // Fallback: return strictly filtered list or all?
        // Instructions imply filtering based on mapping. If no mapping, maybe return
        // all or nothing?
        // "One tenant is interested only in SALE... The other... STANDARD"
        // Let's assume if no tenant ID or no mapping, return all for now (backward
        // compatibility),
        // OR follow explicit mappings only. Let's return all if no mapping is found to
        // avoid breaking
        // the general app usage if accessed without tenant headers logic in other
        // parts.
        return repository.findAll();
    }
};