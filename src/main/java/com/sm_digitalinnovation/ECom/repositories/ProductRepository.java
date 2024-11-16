package com.sm_digitalinnovation.ECom.repositories;

import com.sm_digitalinnovation.ECom.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Products,Integer> {
}
