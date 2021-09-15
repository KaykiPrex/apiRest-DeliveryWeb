package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ProductModel;
import com.example.demo.models.StoreModel;
import com.example.demo.services.ProductService;
import com.example.demo.services.StoreService;



@RestController
@CrossOrigin(origins="http://127.0.0.1:5500")
@RequestMapping("/store")
public class StoreController {
	@Autowired
    StoreService storeService;
	@Autowired
	ProductService productService;

    @GetMapping()
    public ArrayList<StoreModel> obtenerStores(){
        return storeService.obtenerStore();
    }

    @GetMapping( path = "/{id}")
    public Optional<StoreModel> obtenerStorePorId(@PathVariable("id") Long id) {
        return this.storeService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<StoreModel> obtenerStorePorName(@RequestParam("name") String name){
        return this.storeService.obtenerPorName(name);
    }

    @GetMapping("/product")
    public ArrayList<StoreModel> obtenerStorePorProducto(@RequestParam("id") Long id){
        return this.storeService.obtenerProductosPorStore(id);
    }

    @GetMapping("/productname")
    public ArrayList<StoreModel> obtenerStorePorNombreProducto(@RequestParam("name") String name){
        return this.storeService.obtenerPorNombreProducto(name);
    }
    
    @PostMapping(path = "/save")
    public StoreModel createStore(@RequestBody StoreModel store) {
        return storeService.guardarStore(store);
    }
    
	/*
	 * @PutMapping(path = "/{storeID}") public StoreModel
	 * createProductforStore(@PathVariable String storeID ,@RequestBody ProductModel
	 * product) { Optional<StoreModel> store =
	 * storeService.obtenerPorId(Long.parseLong(storeID)); Set<ProductModel> p =
	 * store.get().getProducts(); p.add(product); store.get().setProducts(p); return
	 * storeService.guardarStore(store.get());
	 * 
	 * }
	 */
	/*
	 * @PutMapping(path = "/{storeID}") public StoreModel
	 * createProductforStore(@PathVariable String storeID ,@RequestBody ProductModel
	 * product) { StoreModel store =
	 * storeService.obtenerPorId(Long.parseLong(storeID)).orElse(null);
	 * Set<ProductModel> p = store.getProducts(); p.add(product);
	 * store.setProducts(p); System.out.println(storeID);
	 * System.out.println(store.getIdStore()); System.out.println(store.getName());
	 * return storeService.guardarStore(store);
	 * 
	 * }
	 */
    @Transactional
    @PutMapping(path = "/{storeID}") 
    public ProductModel createProductforStore(@PathVariable String storeID ,@RequestBody ProductModel product) {
    	StoreModel store = storeService.obtenerPorId(Long.parseLong(storeID)).orElse(null);
    
    	return productService.addProduct(store, product);
    	
    }

}
