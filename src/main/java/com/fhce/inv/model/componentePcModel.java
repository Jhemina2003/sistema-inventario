package com.fhce.inv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "componentespc")
public class componentePcModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long idComponentes;
    
    @Column(name = "_01fuente", nullable = false)
    private String fuente;
    
    @Column(name = "_02memorias", nullable = false)
    private int memorias;
    
    @Column(name = "_03capacidad", nullable = false)
    private String capacidad;
    
    @Column(name = "_04micro", nullable = false)
    private String micro;
    
    @Column(name = "_05microcapacidad", nullable = false)
    private String microCapacidad;
    
    @Column(name = "_06disco", nullable = false)
    private String disco;
    
    @Column(name = "_07cortapico")
    private String cortapico;
    
    @Column(name = "_08detalle", nullable = false)
    private String detalle;
    
    @Column(name = "_09teclado", nullable = false)
    private String teclado;
    
    @Column(name = "_10mouse", nullable = false)
    private String mouse;
    
    @Column(name = "_11versionamiento")
    private String versionamiento;
    
    @ManyToOne
    @JoinColumn(name = "_12idequipo", unique = true, nullable = false)
    private equipoModel equipo;
    
}
