package com.fhce.inv.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "equipo")
public class equipoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long idequipo;
	
	@Column(name = "_01codigo", unique = true, nullable = false)
	private String codigo;
	
	@Column(name = "_02macSerie", nullable = false)
	private String macSerie;
	
	@Column(name = "_03marca", nullable = false)
	private String marca;
	
	@ManyToOne//(fetch = FetchType.EAGER)
	@JoinColumn(name = "_04idTipo", nullable = false)
	private tipoModel tipo;
	
	@Column(name = "_05modelo", nullable = false)
	private String modelo;
	
	@Column(name = "_06detalle", nullable = false, length = 5000)
	private String detalle;
	
	@OneToMany(mappedBy = "equipo")
    private List<componentePcModel> componentes;
    
    @OneToMany(mappedBy = "equipo")
    private List<redModel> redes;
    
    @OneToMany(mappedBy = "equipo")
    private List<softwareModel> softwares;
    
    @OneToMany(mappedBy = "equipo")
    private List<ubicacionModel> ubicaciones;
    
    @OneToMany(mappedBy = "equipo")
    private List<atencionModel> atenciones;
    
    @OneToMany(mappedBy = "equipo")
    private List<perteneceModel> asignaciones;
}
