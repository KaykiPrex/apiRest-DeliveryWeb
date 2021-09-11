package com.example.demo.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ProductModel;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Long> {
	public abstract ArrayList<ProductModel> findByName(String name);

}
