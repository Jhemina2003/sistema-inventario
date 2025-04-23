package com.fhce.inv.obj;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class monitorDtoRequest extends equipoDtoRequest{
	private String pulgadas;
	private String tipo;
}
