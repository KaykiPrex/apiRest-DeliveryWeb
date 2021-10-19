package com.example.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @Getter @Setter private Long id;
    @Getter @Setter private String email;
    @Getter @Setter private String name;
    @Getter @Setter private String phone;
    @Getter @Setter private String address;
    @Getter @Setter private String password;
    @Getter @Setter private String rol;
    @Getter @Setter private boolean isBlocked;
    
    @OneToOne
    @JoinColumn(name = "FK_STORE")
	@Getter @Setter private StoreModel store;


}