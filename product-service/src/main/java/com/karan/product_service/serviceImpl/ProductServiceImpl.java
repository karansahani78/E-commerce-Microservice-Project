package com.karan.product_service.serviceImpl;

import com.karan.product_service.customException.ProductNotFoundException;
import com.karan.product_service.dto.ProductDTO;
import com.karan.product_service.model.Product;
import com.karan.product_service.repository.ProductRepository;
import com.karan.product_service.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
        // save it to the database
       Product savedProduct = productRepository.save(product);
        // now convert entity into dto  and return it using builder pattern
        return ProductDTO.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .build();
    }

    @Override
    public List<ProductDTO> findAllProducts() {
     return productRepository.findAll().stream()
             .map(product -> ProductDTO.builder()
                     .id(product.getId())
                     .name(product.getName())
                     .description(product.getDescription())
                     .price(product.getPrice())
                     .build())
             .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product Not found for given id"+id));
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

    }

    @Override
    public ProductDTO updateProducts(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product Not found for given id"+id));
        existingProduct.setId(productDTO.getId());
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        // save the existing user in database
        productRepository.save(existingProduct);
        // now converting updatedproduct(existing product ) entity into dto and then return it
        return ProductDTO.builder()
                .id(existingProduct.getId())
                .name(existingProduct.getName())
                .description(existingProduct.getDescription())
                .price(existingProduct.getPrice())
                .build();
    }

    @Override
    public void deleteProductById(Long id) {
        if(!productRepository.existsById(id)){
            log.warn("Attempted to delete non-existing product with ID: {}", id);
            throw new ProductNotFoundException("Product not found for given id"+id);
        }
        productRepository.deleteById(id);
        log.info("Successfully deleted product with ID: {}", id);

    }
}
