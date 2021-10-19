package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioStoreDTO {
	
	@Getter @Setter private String email;
    @Getter @Setter private String password;
    @Getter @Setter private String rol;
    @Getter @Setter private String name;
	@Getter @Setter private String phone;
	@Getter @Setter private String address;
	@Getter @Setter private double latitude;
	@Getter @Setter private double longitude;
	@Getter @Setter private double distance;

}
