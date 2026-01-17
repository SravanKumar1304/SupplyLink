package com.edutech.progressive.service.impl;

import java.util.List;

import com.edutech.progressive.entity.Product;
import com.edutech.progressive.service.ProductService;

public class ProductServiceImplJdbc implements ProductService {

    @Override
    public List<Product> getAllProducts() {
      return List.of();
    }

    @Override
    public Product getProductById(int productId) {
        return null;
    }

    @Override
    public int addProduct(Product product) {
       return -1;
    }

    @Override
    public void updateProduct(Product product) {
       
    }

    @Override
    public void deleteProduct(int productId) {
      
    }

}