package com.hsfulda.demo.products;

import com.hsfulda.demo.products.model.Product;
import com.hsfulda.demo.products.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadProductDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadProductDatabase.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            long count = repository.count();
            if (count >= 20) {
                log.info("Databse already contains {} products. Skipping preload.", count);
            } else {
                log.info("Product count is {}. Preloading sample products....", count);

                log.info("Preloading " + repository.save(new Product("T-Shirt", 19.99, "M", "Blue", "T-shirt")));
                log.info("Preloading " + repository.save(new Product("Jeans", 49.99, "32x32", "Black", "Pants")));
                log.info("Preloading " + repository.save(new Product("Sneakers", 79.99, "10", "White", "Shoes")));
                log.info("Preloading " + repository.save(new Product("Hoodie", 35.50, "L", "Grey", "Sweater")));
                log.info("Preloading " + repository.save(new Product("Watch", 199.99, "One Size", "Silver", "Accessory")));
                log.info("Preloading " + repository.save(new Product("Dress Shirt", 45.00, "16.5", "Red", "Shirt")));
                log.info("Preloading " + repository.save(new Product("Socks (3-Pack)", 12.00, "One Size", "Multi", "Undergarment")));
                log.info("Preloading " + repository.save(new Product("Backpack", 59.95, "One Size", "Green", "Bag")));
                log.info("Preloading " + repository.save(new Product("Beanie", 15.00, "One Size", "Yellow", "Headwear")));
                log.info("Preloading " + repository.save(new Product("Shorts", 25.99, "M", "Khaki", "Pants")));

                log.info("Preloading completed.");
            };

        };
    }
}
