package com.example.api.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@AttributeOverrides({
	@AttributeOverride(name="id",column=@Column(name="id"))
})
@Table(name = "Usuario")

public class Usuario extends BaseEntity implements Serializable {

	@Column(name = "nombres")
	String nombres;
	@Column(name = "apellidos")
	String apellidos; 
	@Column(name = "cuenta")
	String cuenta;
	@Column(name = "contrasena")
	String contrasena;
	@Column(name = "correo")
	String correo;
  
}
