package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {
	 @Autowired
	    ProductRepository productRepository;

	    public ArrayList<ProductModel> obtenerProductos(){
	        return (ArrayList<ProductModel>) productRepository.findAll();
	    }

	    public ProductModel guardarProduct(ProductModel product){
	        return productRepository.save(product);
	    }

	    public Optional<ProductModel> obtenerPorId(Long id){
	        return productRepository.findById(id);
	    }

	    public ArrayList<ProductModel> obtenerPorName(String name){
	    	return productRepository.findByName(name);
	    }

}
