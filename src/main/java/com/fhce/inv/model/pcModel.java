package com.fhce.inv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="pc")
public class pcModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    protected Long id;
	
	@Column(name = "_01fuente")
	private String fuente;
	
	@Column(name = "_02memorias")
	private int memorias;
	
	@Column(name = "_03capacidad")
	private String capacidad;
	
	@Column(name = "_04micro")
	private String micro;
	
	@Column(name = "_05microcapacidad")
	private String microCapacidad;
	
	@Column(name = "_06disco")
	private String disco;
	
	@Column(name = "_07cortapico")
	private String cortapico;
	
	@Column(name = "_08detalle")
	private String detalle;
	
	@Column(name = "_09teclado")
	private String teclado;
	
	@Column(name = "_10mouse")
	private String mouse;
	
	@Column(name = "_11idequipo")
	private Long idEquipo;

}
