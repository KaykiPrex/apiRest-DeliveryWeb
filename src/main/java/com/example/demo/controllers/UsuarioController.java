package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UsuarioStoreDTO;
import com.example.demo.models.StoreModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.services.StoreService;
import com.example.demo.services.UsuarioService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	StoreService storeService;

	@GetMapping()
	public ArrayList<UsuarioModel> obtenerUsuarios() {
		return usuarioService.obtenerUsuarios();
	}

	@PostMapping()
	public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
		return this.usuarioService.guardarUsuario(usuario);
	}

	@GetMapping(path = "/{id}")
	public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {
		return this.usuarioService.obtenerPorId(id);
	}

	@DeleteMapping(path = "/{id}")
	public String eliminarPorId(@PathVariable("id") Long id) {
		boolean ok = this.usuarioService.eliminarUsuario(id);
		if (ok) {
			return "Se eliminó el usuario con id " + id;
		} else {
			return "No pudo eliminar el usuario con id" + id;
		}
	}

	@GetMapping("/login") //Devolver usuario y adentro store o cliente
	public Optional<StoreModel> obtenerUsuarioPorEmail(@RequestParam("name") String email) {
		Optional<StoreModel> response = null;
		try {

			UsuarioModel user = this.usuarioService.obtenerPorEmail(email).get();
			if (user.getEmail().equals(email))
				response = this.storeService.obtenerPorId(user.getId());
		} catch (Exception e) {
			System.out.println("System Error: " + e.getMessage());
		}
		return response;
	}

	@PostMapping("/login")
	public Optional<UsuarioModel> autenticarUsuario(@RequestBody UsuarioModel request) {
		Optional<UsuarioModel> response = Optional.empty(); // responder json null o json con todos los atributos en null?
		UsuarioModel user = null;
		try {
			Optional<UsuarioModel> userEmail = this.usuarioService.obtenerPorEmail(request.getEmail());	
			if (userEmail.isPresent()) {
				user = userEmail.get();
				if (user.getPassword().equals(request.getPassword())) {
					response = userEmail;
				}					
			}
		} catch (Exception e) {
			System.out.println("System Error: " + e.getMessage());
		}
			
		return response;
	}
	@PostMapping(path = "/save")
    public UsuarioModel createUsuario(@RequestBody UsuarioStoreDTO dto) {
		UsuarioModel u = new UsuarioModel();
		if (dto.getRol().equals("store")){
			StoreModel store = new StoreModel();
	    	store.setDistance(dto.getDistance());
	    	store.setLatitude(dto.getLatitude());
	    	store.setLongitude(dto.getLongitude());
	    	//replica para facilitar la vista
	    	store.setAddress(dto.getAddress());
	    	store.setName(dto.getName());
	    	store.setPhone(dto.getPhone());
	        StoreModel storeSave= storeService.guardarStore(store);
	        u.setStore(storeSave);
		}
        u.setAddress(dto.getAddress());
        u.setName(dto.getName());
        u.setPhone(dto.getPhone());
    	u.setEmail(dto.getEmail());
    	u.setPassword(dto.getPassword());
    	u.setRol(dto.getRol());
    	
    	return usuarioService.guardarUsuario(u); 
	}

}
