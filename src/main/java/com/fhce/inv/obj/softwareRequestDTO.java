package com.fhce.inv.obj;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class softwareRequestDTO {
	private String nombre;
    private String version;
    private String estadoLicencia;
    private String tipo;
    
    @JsonIgnore
    private Long idEquipo;
    
    private LocalDate fecha;
    private int estado;
}
