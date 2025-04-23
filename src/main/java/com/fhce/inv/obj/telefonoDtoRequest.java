package com.fhce.inv.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class telefonoDtoRequest extends equipoDtoRequest {

	private String interno;
	private String numero;
	

}
