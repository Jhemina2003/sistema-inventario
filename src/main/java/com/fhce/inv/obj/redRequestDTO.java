package com.fhce.inv.obj;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class redRequestDTO {
    //@JsonIgnore
    private Long idEquipo;
    
	private String ip;
    private String segmento;
    private String dns;
    private String vlan;
    private String switchRed;
    private String puerto;    
    private LocalDate fechaRegistro;
    private int estado;
}
