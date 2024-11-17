package com.sm_digitalinnovation.ECom.services;

import com.sm_digitalinnovation.ECom.exceptions.ProuctsNotFoundException;
import com.sm_digitalinnovation.ECom.model.Products;
import com.sm_digitalinnovation.ECom.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Products> saveAllProducts(List<Products> products) {
        return productRepository.saveAll(products);
    }

    public Products getProductById(int id) {
        return  productRepository.findById(id).orElse(null);
    }

    public Products saveProduct(Products products) {
        return productRepository.save(products);
    }

    public Products updateProducts(int id, Products product) {
        Optional<Products> getProduct=productRepository.findById(id);

        if (getProduct.isPresent()){
            return productRepository.save(product);
        }
        else {
            throw new ProuctsNotFoundException("Product Not found...");
        }
    }

    public Products deleteProducts(int id) {
        Optional<Products> getProduct=productRepository.findById(id);

        if (getProduct.isPresent()){
            productRepository.deleteById(id);
            return getProduct.get();
        }
        else {
            return null;
        }
    }

    public Products addProduct(Products products, MultipartFile imgFile) throws IOException {
        products.setImageName(imgFile.getOriginalFilename());
        products.setImageType(imgFile.getContentType());
        products.setImage(imgFile.getBytes());
        return  productRepository.save(products);
    }
}
