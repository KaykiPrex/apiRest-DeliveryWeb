package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UsuarioStoreDTO;
import com.example.demo.models.ProductModel;
import com.example.demo.models.StoreModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.services.ProductService;
import com.example.demo.services.StoreService;
import com.example.demo.services.UsuarioService;



@RestController
@CrossOrigin(origins="http://127.0.0.1:5500" ,methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/store")
public class StoreController {
	@Autowired
    StoreService storeService;
	@Autowired
	ProductService productService;
	@Autowired
	UsuarioService usuarioService;
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
    //Borrar este endpoint save Store controller
    @PostMapping(path = "/save")
    public UsuarioModel createStore(@RequestBody UsuarioStoreDTO dto) {
    	
    	StoreModel store = new StoreModel();
    	store.setAddress(dto.getAddress());
    	store.setDistance(dto.getDistance());
    	store.setLatitude(dto.getLatitude());
    	store.setLongitude(dto.getLongitude());
    	store.setName(dto.getName());
    	store.setPhone(dto.getPhone());
    	//store.setUsuario(usuario);
        StoreModel storeSave= storeService.guardarStore(store);
        
        UsuarioModel u = new UsuarioModel();
    	u.setEmail(dto.getEmail());
    	u.setPassword(dto.getPassword());
    	u.setRol(dto.getRol());
    	u.setStore(storeSave);
    	//UsuarioModel usuario = usuarioService.guardarUsuario(u);
    	return usuarioService.guardarUsuario(u); 
    }
    
    @Transactional
    @PutMapping(path = "/{storeID}") 
    public ProductModel createProductforStore(@PathVariable String storeID ,@RequestBody ProductModel product) {
    	StoreModel store = storeService.obtenerPorId(Long.parseLong(storeID)).orElse(null);
    
    	return productService.addProduct(store, product);
    	
    }

}
