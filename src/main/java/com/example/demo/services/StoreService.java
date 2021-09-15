package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.ProductModel;
import com.example.demo.models.StoreModel;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.StoreRepository;

@Service
public class StoreService {
	 @Autowired
	    StoreRepository storeRepository;
	 	ProductRepository productRepository;

	    public ArrayList<StoreModel> obtenerStore(){
	        return (ArrayList<StoreModel>) storeRepository.findAll();
	    }

	    public StoreModel guardarStore(StoreModel store){
	        return storeRepository.save(store);
	    }

	    public Optional<StoreModel> obtenerPorId(Long id){
	        return storeRepository.findById(id);
	    }

	    public ArrayList<StoreModel>  obtenerPorName(String name) {
	        return storeRepository.findByName(name);
	    }

	    public ArrayList<StoreModel>  obtenerProductosPorStore(long id) {
	        return storeRepository.findByProducts_idProduct(id);
	    }
	    //Distintos
	    public ArrayList<StoreModel>  obtenerPorNombreProducto(String name) {
	        return storeRepository.findDistinctByProducts_name(name);
	    }
	    @Transactional
	    public StoreModel addProducts(StoreModel store, ProductModel product) {
	    	//store.getProducts().add(product); cambiar a MODIFICAR STORE()
	    	store.setAddress("FAFAF");
	    	return storeRepository.save(store);
	    }
}
