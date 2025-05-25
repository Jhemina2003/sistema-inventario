package com.fhce.inv.obj;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class perteneceRequestDTO {
	private Long cif;
	
	@JsonIgnore
    private Long idEquipo;
	
    private LocalDate fechaAdd;
    private LocalDate fechaDel;
    private String estado;
}
