package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ProductModel;
import com.example.demo.services.ProductService;

@RestController
@CrossOrigin(origins="http://127.0.0.1:5500")
@RequestMapping("/product")
public class ProductController {
	@Autowired
    ProductService productService;

    @GetMapping()
    public ArrayList<ProductModel> obtenerProducts(){
        return productService.obtenerProductos();
    }

    @GetMapping( path = "/{id}")
    public Optional<ProductModel> obtenerProductPorId(@PathVariable("id") Long id) {
        return this.productService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<ProductModel> obtenerStorePorName(@RequestParam("name") String name){
        return this.productService.obtenerPorName(name);
    }
}
