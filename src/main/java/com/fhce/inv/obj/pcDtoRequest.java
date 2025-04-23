package com.fhce.inv.obj;

import com.fhce.inv.model.redModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class pcDtoRequest extends equipoDtoRequest{
	
	private String fuente;
	private int memorias;
	private String capacidad;
	private String micro;
	private String microCapacidad;
	private String disco;
	private String cortapico;
	private String detalle;
	private String teclado;
	private String mouse;
	private redModel redModel;

}
