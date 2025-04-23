package com.fhce.inv.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.pcDao;
import com.fhce.inv.dao.perteneceDao;
import com.fhce.inv.dao.redDao;
import com.fhce.inv.model.pcModel;
import com.fhce.inv.model.perteneceModel;
import com.fhce.inv.model.redModel;
import com.fhce.inv.obj.pcDtoRequest;
import com.fhce.inv.obj.pcDtoResponce;
import com.fhce.inv.service.pcService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class pcServiceImpl implements pcService{
	private final perteneceDao perteneceDao;
	private final ModelMapper modelMapper;
	//private final pcDao pcDao;
	private final redDao redDao;
	
	@Transactional
	public pcDtoResponce addPc (pcDtoRequest pcDtoRequest ) {
		
		
		pcModel pcModel =new pcModel();
		//pcModel.setCodigo(pcDtoRequest.getCodigo());
		//pcModel.setMacSerie(pcDtoRequest.getMacSerie());
		//pcModel.setMarca(pcDtoRequest.getMarca());
		//pcModel.setIdTipo(pcDtoRequest.getIdTipo());
		
		/*pcModel.setFuente(pcDtoRequest.getFuente());
		pcModel.setMemorias(pcDtoRequest.getMemorias());
		pcModel.setCapacidad(pcDtoRequest.getCapacidad());
		pcModel.setMicro(pcDtoRequest.getMicro());
		pcModel.setMicroCapacidad(pcDtoRequest.getMicroCapacidad());
		pcModel.setDisco(pcDtoRequest.getDisco());
		pcModel.setCortapico(pcDtoRequest.getCortapico());
		pcModel.setDetalle(pcDtoRequest.getDetalle());
		pcModel.setTeclado(pcDtoRequest.getTeclado());
		pcModel.setMouse(pcDtoRequest.getMouse());
		this.pcDao.save(pcModel);
		*/
		
		
		redModel redModel = new redModel();
		
		redModel.setIp(pcDtoRequest.getRedModel().getIp());
		redModel.setSegmento(pcDtoRequest.getRedModel().getSegmento());
		redModel.setDns(pcDtoRequest.getRedModel().getDns());
		redModel.setVlan(pcDtoRequest.getRedModel().getVlan());
		redModel.setSwitchRed(pcDtoRequest.getRedModel().getSwitchRed());
		redModel.setPuerto(pcDtoRequest.getRedModel().getVlan());
		redModel.setFechaRegistro(pcDtoRequest.getRedModel().getFechaRegistro());
		//redModel.setIdEquipo(pcModel.getId());
		
		this.redDao.save(redModel);
		
		
		/*perteneceModel perteneceModel = new perteneceModel();
		perteneceModel.setCif(pcDtoRequest.getCif());
		perteneceModel.setIdEquipo(pcModel.getId());
		perteneceModel.setFechaAdd(pcDtoRequest.getFechaAdd());
		perteneceModel.setFechaDel(pcDtoRequest.getFechaDel());
		perteneceModel.setEstado(pcDtoRequest.getEstado());
		
		this.perteneceDao.save(perteneceModel);
		*/
		//return this.modelMapper.map(pcModel, pcDtoResponce.class);
		return(null);
	}

}
