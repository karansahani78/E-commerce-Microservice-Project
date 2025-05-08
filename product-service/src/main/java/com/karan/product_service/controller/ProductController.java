package com.karan.product_service.controller;

import com.karan.product_service.dto.ProductDTO;
import com.karan.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Tag(name = "Product Management", description = "Operations for managing and controlling product information.")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new product", description = "Creates a new product with the given name, description, and price.")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Get all products", description = "Returns a list of all products.")
    public List<ProductDTO>findAllProducts(){
        return productService.findAllProducts();
    }
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductDTO findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }
    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update product information", description = "Updates the product information for the given product ID.")
    public ProductDTO updateProducts(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProducts(id,productDTO);
    }
    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete product by ID", description = "Deletes the product with the given product ID.")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }
}
