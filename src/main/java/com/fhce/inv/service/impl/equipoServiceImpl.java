package com.fhce.inv.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.equipoDao;
import com.fhce.inv.obj.equipoDtoResponce;
import com.fhce.inv.service.equipoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class equipoServiceImpl implements equipoService{
	
	private final equipoDao equipoDao;
	private final ModelMapper modelMapper;
	
	public List<equipoDtoResponce>getEquipoTipo(Long id){
		System.out.println("%%%%%%%%%%%%%%%%%%no se de donde sacas el cif");
		List<equipoDtoResponce>listaEquipo = this.equipoDao.getEquipoTipo(id).stream()
				.map(equipo->this.modelMapper.map(equipo, equipoDtoResponce.class))
				.collect(Collectors.toList());
		return (listaEquipo);
	}

}
