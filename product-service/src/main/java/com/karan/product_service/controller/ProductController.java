package com.karan.product_service.controller;

import com.karan.product_service.dto.ProductDTO;
import com.karan.product_service.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
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
    public ProductDTO updateProducts(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProducts(id,productDTO);
    }
    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }
}
