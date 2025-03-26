package com.karan.product_service.service;

import com.karan.product_service.dto.ProductDTO;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ProductService {
    // create product
    public ProductDTO createProduct(ProductDTO productDTO);
    // find all product
    public List<ProductDTO> findAllProducts();
    // find product by id
    public ProductDTO findProductById(Long id);
    //update product
    public ProductDTO updateProducts(Long id,ProductDTO productDTO);
    // delete product by id
    public void deleteProductById(Long id);
}
