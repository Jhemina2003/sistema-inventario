package com.fhce.inv.model;

import java.time.LocalDate;
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
@Table(name = "pertenece")
public class perteneceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long idPertenece;
	
	@Column(name = "_01cif", nullable = false)
	private Long cif;
	
	@ManyToOne
    @JoinColumn(name = "_02idequipo", unique = true, nullable = false)
    private equipoModel equipo;
	
	@Column(name = "_03fechaadd", nullable = false)
	private LocalDate fechaAdd;
	
	@Column(name = "_04fechadel", nullable = false)
	private LocalDate fechaDel;
	
	@Column(name = "_05estado")
	private String estado;
	
	@OneToMany(mappedBy = "pertenece")
    private List<atencionModel> atenciones;
	
}