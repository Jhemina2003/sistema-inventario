package com.fhce.inv.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class atencionDtoRequest {
	
	private Long idEquipo;
    private String fechasolicitud;
    private String horasolicitud;
    private String especificacion;
    private String error;
    private String solucion;
    private String fechaatencion;
    private String horaatencion;
    private int estado;
    private Long idPertenece;
    private Long idTipo;

}
