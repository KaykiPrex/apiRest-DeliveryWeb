
package com.example.demo.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "store")
@NoArgsConstructor
@AllArgsConstructor
public class StoreModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	@Getter @Setter private long idStore;
	
	@Getter @Setter private String name;
	@Getter @Setter private String phone;
	@Getter @Setter private String address;
	@Getter @Setter private double latitude;
	@Getter @Setter private double longitude;
	@Getter @Setter private double distance;
	
	/*
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "FK_USUARIO")
	 * 
	 * @Getter @Setter private UsuarioModel usuario;
	 */

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	@Getter @Setter private Set<ProductModel> products = new HashSet<>();
	
	/*
	 * public void addProduct(ProductModel productModel) {
	 * products.add(productModel); productModel.setStore(this); }
	 */

}
