package com.fhce.inv.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class equipoRequestDTO {
	private String codigo;
    private String macSerie;
    private String marca;
    
    ///@JsonIgnore
    private Long idTipo;
    
    private String modelo;
    private String detalle;
}
