package org.nightfury.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.nightfury.entity.Product;
import org.nightfury.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Product with ID " + id + " not found"));
    }

    public Product getProductByTitle(String title) {
        return productRepository.findByTitle(title).orElseThrow(
            () -> new EntityNotFoundException("Product with Title " + title + " not found"));
    }

    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    public Page<Product> getProductsByCategoryId(long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByCategoryId(categoryId, pageable);
    }

    public Page<Product> getProductsByCategoryIdAndPriceBetween(Optional<Long> categoryId,
        Optional<Double> minPrice,
        Optional<Double> maxPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Double min = minPrice.orElse(0.0);
        Double max = maxPrice.orElse(Double.MAX_VALUE);
        if (categoryId.isPresent()) {
            return productRepository.findByCategoryIdAndPriceBetween(categoryId.get(), min, max,
                pageable);
        }
        return productRepository.findByPriceBetween(min, max, pageable);
    }
}
