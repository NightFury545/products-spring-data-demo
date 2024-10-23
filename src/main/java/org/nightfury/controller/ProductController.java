package org.nightfury.controller;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.nightfury.entity.Product;
import org.nightfury.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public String getAllProducts(@RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size, Model model) {
        Page<Product> products = productService.getAllProducts(page, size);
        model.addAttribute("products", products);
        return "products-list";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-detail";
    }

    @GetMapping("/category/{categoryId}")
    public String getProductsByCategoryId(@PathVariable long categoryId,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
        Model model) {
        Page<Product> products = productService.getProductsByCategoryId(categoryId, page, size);
        model.addAttribute("products", products);
        return "products-list";
    }

    @GetMapping("/filter")
    public String getProductsByCategoryIdAndPriceBetween(@RequestParam Optional<Long> categoryId,
        @RequestParam Optional<Double> minPrice, @RequestParam Optional<Double> maxPrice,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size,
        Model model) {
        Page<Product> products = productService.getProductsByCategoryIdAndPriceBetween(categoryId,
            minPrice, maxPrice, page, size);
        model.addAttribute("products", products);
        return "products-list";
    }
}
