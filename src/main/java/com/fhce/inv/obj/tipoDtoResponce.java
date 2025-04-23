package com.fhce.inv.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tipoDtoResponce {
	private Long id;
	private String sigla;
	private String nombre;
	private String icono;
	private String detalle;

}
