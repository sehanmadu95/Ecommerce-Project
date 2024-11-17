package com.sm_digitalinnovation.ECom.controllers;


import com.sm_digitalinnovation.ECom.model.Products;
import com.sm_digitalinnovation.ECom.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Products> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/saveAll")
    public List<Products> saveAllProducts(@RequestBody List<Products> products){
        return productService.saveAllProducts(products);
    }

    @PostMapping("/save")
    public Products saveProduct(@RequestBody Products products){

        return productService.saveProduct(products);
    }

    @GetMapping("/getById/{id}")
    public Products getProductsById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PutMapping("/update/{id}")
    public Products updateProducts(@PathVariable int id,@RequestBody Products product){
        return productService.updateProducts(id,product);
    }

    @DeleteMapping("/delete/{id}")
    public Products deleteProducts(@PathVariable int id){
        return productService.deleteProducts(id);
    }

    @PostMapping(value = "/addProduct")
    public ResponseEntity<?> addProduct(@RequestPart Products products, @RequestPart MultipartFile imgFile){
        try{
           Products p1=  productService.addProduct(products,imgFile);
           return  new ResponseEntity<>(p1, HttpStatus.CREATED);
        }
        catch (Exception ex){
            return  new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }

    }


}
