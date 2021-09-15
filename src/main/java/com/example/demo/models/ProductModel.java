package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "producto")
public class ProductModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
	private long idProduct;
	private String name;
	private String description;
	private double price;
	private String pic;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "FK_STORE", nullable = false, updatable = false)
	private StoreModel store;

	public ProductModel() {}

	public ProductModel(long idProduct,String name,String description,double price,String pic) {

		this.name=name;
		this.description=description;
		this.price=price;
		this.pic=pic;
	}

	public long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public StoreModel getStore() {
		return store;
	}

	public void setStore(StoreModel store) {
		this.store = store;
	}
	
	
	
}
