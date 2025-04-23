package com.fhce.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.tipoDao;
import com.fhce.inv.model.tipoModel;
import com.fhce.inv.obj.tipoDtoRequest;
import com.fhce.inv.obj.tipoDtoResponce;
import com.fhce.inv.service.tipoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class tipoServiceImpl implements tipoService{
	
	private final tipoDao tipoDao;
	private final ModelMapper modelMapper;
	
	@Transactional
	public List<tipoDtoResponce>getTipo(){
		
		List<tipoDtoResponce>listaTipo = this.tipoDao.findAll().stream()
				.map(tipo -> this.modelMapper.map(tipo, tipoDtoResponce.class))
				.collect(Collectors.toList());
		return(listaTipo);
	}
	
	@Transactional
	public tipoDtoResponce addTipo(tipoDtoRequest tipoDtoRequest) {
		tipoModel tipoModel = new tipoModel();
		tipoModel.setNombre(tipoDtoRequest.getNombre());
		tipoModel.setDetalle(tipoDtoRequest.getDetalle());
		tipoModel.setIcono(tipoDtoRequest.getIcono());
		tipoModel.setSigla(tipoDtoRequest.getSigla());
		
		this.tipoDao.save(tipoModel);
		
		return (this.modelMapper.map(tipoModel, tipoDtoResponce.class));
	}

}
