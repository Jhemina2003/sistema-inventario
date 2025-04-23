package com.fhce.inv.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class equipoDtoResponce {
	protected Long id;
    protected String codigo;
    protected String macSerie;
    protected String marca;
    protected Long idTipo;
    protected String modelo;
    protected String detalle;

}
