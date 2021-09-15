package com.example.demo.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.StoreModel;

@Repository
public interface StoreRepository extends CrudRepository<StoreModel, Long> {
	public abstract ArrayList<StoreModel> findByAddress(String address);

	public abstract ArrayList<StoreModel> findByName(String name);

	//se mapea segun los atributos del model
	public ArrayList<StoreModel> findByProducts_idProduct(long id);

	public ArrayList<StoreModel> findByProducts_name(String name);
	
	public ArrayList<StoreModel> findDistinctByProducts_name(String name);
}
