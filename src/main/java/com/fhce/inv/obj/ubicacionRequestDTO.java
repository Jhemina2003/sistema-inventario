package com.fhce.inv.obj;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ubicacionRequestDTO {
	
	//@JsonIgnore
	private Long idEquipo;
	
    private String ambiente;
    private String latitud;
    private String longitud;
    private LocalDate fecha;
    private int estado;
}
