package com.fhce.inv.obj;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class componentePcRequestDTO {
	private String fuente;
    private Integer memorias;
    private String capacidad;
    private String micro;
    private String microCapacidad;
    private String disco;
    private String cortapico;
    private String detalle;
    private String teclado;
    private String mouse;
    private String versionamiento;
    
    @JsonIgnore
    private Long idEquipo;
    
    private LocalDate fecha;
}
