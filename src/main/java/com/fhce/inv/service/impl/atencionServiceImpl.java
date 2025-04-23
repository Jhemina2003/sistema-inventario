package com.fhce.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.atencionDao;
import com.fhce.inv.dao.pcDao;
import com.fhce.inv.dao.perteneceDao;
import com.fhce.inv.dao.redDao;
import com.fhce.inv.dao.softwareDao;
import com.fhce.inv.dao.tipoDao;
import com.fhce.inv.model.atencionModel;

import com.fhce.inv.model.pcModel;
import com.fhce.inv.model.perteneceModel;
import com.fhce.inv.model.redModel;
import com.fhce.inv.model.softwareModel;

import com.fhce.inv.model.tipoModel;
import com.fhce.inv.obj.atencionDtoObjResponce;
import com.fhce.inv.obj.atencionDtoRequest;
import com.fhce.inv.obj.atencionDtoResponce;
import com.fhce.inv.service.atencionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class atencionServiceImpl implements atencionService{
	private final atencionDao atencionDao;
	private final tipoDao tipoDao;
	private final softwareDao softwareDao;
	private final redDao redDao;
	//private final pcDao pcDao;
	private final perteneceDao perteneceDao;
	private final ModelMapper modelMapper;
	
	@Transactional
	public atencionDtoResponce addAtencion(atencionDtoRequest atencionDtoRequest) {
		
		atencionModel atencionModel = new atencionModel();
		atencionModel.setIdEquipo(atencionDtoRequest.getIdEquipo());
	    atencionModel.setFechasolicitud(atencionDtoRequest.getFechasolicitud());
	    atencionModel.setHorasolicitud(atencionDtoRequest.getHorasolicitud());
	    atencionModel.setEspecificacion(atencionDtoRequest.getEspecificacion());
	    atencionModel.setError(atencionDtoRequest.getError()); 
	    atencionModel.setSolucion(atencionDtoRequest.getSolucion()); 
	    atencionModel.setFechaatencion(atencionDtoRequest.getFechaatencion());
	    atencionModel.setHoraatencion(atencionDtoRequest.getHoraatencion());
	    atencionModel.setEstado(atencionDtoRequest.getEstado());
	    atencionModel.setIdPertenece(atencionDtoRequest.getIdPertenece());
	    atencionModel.setIdTipo(atencionDtoRequest.getIdTipo());
		this.atencionDao.save(atencionModel);
		
		return this.modelMapper.map(atencionModel, atencionDtoResponce.class);
	}
	
	@Transactional
	public atencionDtoResponce updateAtencion(atencionDtoResponce atencionDtoResponce) {
		
		atencionModel atencionModel = new atencionModel();
		
		atencionModel.setId(atencionDtoResponce.getId());
		atencionModel.setIdEquipo(atencionDtoResponce.getIdEquipo());
	    atencionModel.setFechasolicitud(atencionDtoResponce.getFechasolicitud());
	    atencionModel.setHorasolicitud(atencionDtoResponce.getHorasolicitud());
	    atencionModel.setEspecificacion(atencionDtoResponce.getEspecificacion());
	    atencionModel.setError(atencionDtoResponce.getError()); 
	    atencionModel.setSolucion(atencionDtoResponce.getSolucion()); 
	    atencionModel.setFechaatencion(atencionDtoResponce.getFechaatencion());
	    atencionModel.setHoraatencion(atencionDtoResponce.getHoraatencion());
	    atencionModel.setEstado(atencionDtoResponce.getEstado());
	    atencionModel.setIdPertenece(atencionDtoResponce.getIdPertenece());
		atencionModel.setIdTipo(atencionDtoResponce.getIdTipo());
	    this.atencionDao.save(atencionModel);
		
		return this.modelMapper.map(atencionModel, atencionDtoResponce.class);
	}
	@Transactional
	public List<atencionDtoResponce> getListaEspera() {
		List<atencionDtoResponce> atenicnoDtoRepsonce = this.atencionDao.getAtencionEspera().stream()
				.map(atencion -> this.modelMapper.map(atencion, atencionDtoResponce.class))
				.collect(Collectors.toList());
				
		return (atenicnoDtoRepsonce);
	}
	
	/*
	@Transactional
	public List<atencionDtoObjResponce> getAtencionCif(Long cif) {
		List<perteneceModel> perteneceModel = this.perteneceDao.getPerteneceCif(cif);
		

		List<atencionModel> atencionModel = this.atencionDao.getAtencionCif(cif);
		List<tipoModel> tipoModel = this.tipoDao.findAll();
		List<Caracteristica> Caracteristica = this.caracteristicaDAO.findAll();
		List<softwareModel> softwareModel = this.softwareDao.findAll();
		//List<redModel> redModel = this.redDao.findAll();
		List<atencionDtoObjResponce> atencionDtoObjResponce = new ArrayList<atencionDtoObjResponce>();
		List<String> resumen = new ArrayList<String>();
		String equipo = "";
		String caracteristica = "";
		Long idcarac = Long.valueOf("0");
		for (int i = 0; i < atencionModel.size(); i++) {

			int c = atencionModel.get(i).getIdTipo().intValue();
			switch (c) {
			case (1):
				List<pcModel> pcModel = this.pcDao.getCpuCif(cif);
				for (int j = 0; j < pcModel.size(); j++) {
					if (atencionModel.get(i).getIdEquipo().longValue() == pcModel.get(j).getId().longValue()) {
						resumen = new ArrayList<String>();
						
						for (int k = 0; k < softwareModel.size(); k++) {
							System.out.print("IDPC: "+pcModel.get(i).getId().longValue()+" - IDSOF: "+softwareModel.get(k).getIdEquipo().longValue());
							if (pcModel.get(i).getId().longValue() == softwareModel.get(k).getIdEquipo().longValue() && softwareModel.get(k).getTipo().equals("Sistema Operativo")) {
								resumen.add("S.O. : " + softwareModel.get(k).getNombre());
								resumen.add("Version : " + softwareModel.get(k).getVersion());
								resumen.add("Estado de licencia : " + softwareModel.get(k).getEstadoLicencia());
							}
						}
					
						resumen.add("RAM : " + pcModel.get(j).getMemorias() + " - " + pcModel.get(j).getCapacidad());
						resumen.add("MP : " + pcModel.get(j).getMicro() + " de " + pcModel.get(j).getMicroCapacidad());
						resumen.add("HHDD : " + pcModel.get(j).getDisco());
						resumen.add("Detalle : " + pcModel.get(j).getDetalle());
					}
				}
				break;
			case (2):
				List<monitorModel> monitorModel = this.monitorDAO.getMonitorCif(cif);
				for (int j = 0; j < monitorModel.size(); j++) {
					if (atencionModel.get(i).getIdEquipo().longValue()== monitorModel.get(j).getId()) {
						resumen = new ArrayList<String>();
						resumen.add("Marca : " + monitorModel.get(j).getMarca());
						resumen.add("Pulgadas : " + monitorModel.get(j).getPulgadas());
						resumen.add("Tipo : " + monitorModel.get(j).getTipo());
					}
				}
				break;
			case (3):
				List<impresoraModel> impresoraModel = this.impresoraDAO.getImpresoraCif(cif);
				for (int j = 0; j < impresoraModel.size(); j++) {
					if (atencionModel.get(i).getIdEquipo().longValue()==impresoraModel.get(j).getId()) {
						resumen = new ArrayList<String>();
						resumen.add("Marca : " + impresoraModel.get(j).getMarca());
						resumen.add("Modelo : " + impresoraModel.get(j).getModelo());
						resumen.add("Detalle : " + impresoraModel.get(j).getDetalle());
					}
				}
				break;
			case (4):
				List<telefonoModel> telefonoModel = this.telefonoDAO.getTelefonoCif(cif);
				for (int j = 0; j < telefonoModel.size(); j++) {
					if (atencionModel.get(i).getIdEquipo().longValue()== telefonoModel.get(j).getId()) {
						resumen = new ArrayList<String>();
						resumen.add("Marca : " + telefonoModel.get(j).getMarca());
						resumen.add("Interno : " + telefonoModel.get(j).getInterno());
						for (int j2 = 0; j2 < redModel.size(); j2++) {
							if(telefonoModel.get(j).getId().longValue() == redModel.get(j2).getIdEquipo().longValue()) {
								resumen.add("Ip : " + redModel.get(j2).getIp());
							}
							
						}
					}
				}
				break;
			case (5):
				List<redModel> redModel = this.redDao.getCpuCif(cif);
				for (int j = 0; j < redModel.size(); j++) {
					if (atencionModel.get(i).getIdEquipo().longValue()==redModel.get(j).getIdEquipo()) {
						resumen = new ArrayList<String>();
						resumen.add("IP : " + redModel.get(j).getIp());
						resumen.add("Mascara : " + redModel.get(j).getDns());
						resumen.add("Segmento : " + redModel.get(j).getSegmento());
						resumen.add("Swicht : " + redModel.get(j).getSwitchRed());
						resumen.add("Puerto - Vlan : " + redModel.get(j).getPuerto() + " - "
										+ redModel.get(j).getVlan());
					}
					
				}
				break;
			}
			
			for (int k = 0; k < tipoModel.size(); k++) {
				if (tipoModel.get(k).getId().longValue() == atencionModel.get(i).getIdTipo().longValue()) {
					equipo = tipoModel.get(k).getSigla();
				}
			}
			
			atencionDtoObjResponce at = new atencionDtoObjResponce(atencionModel.get(i).getId(), atencionModel.get(i).getCif(),
					atencionModel.get(i).getCodigo(), atencionModel.get(i).getFechasolicitud(),
					atencionModel.get(i).getHorasolicitud(), equipo, resumen, caracteristica,
					atencionModel.get(i).getEspecificacion(), atencionModel.get(i).getError(),
					atencionModel.get(i).getDetalle(), atencionModel.get(i).getFechaatencion(),
					atencionModel.get(i).getHoraatencion(), atencionModel.get(i).getEstado(),
					atencionModel.get(i).getIdTipo(), idcarac);

			atencionDtoObjResponce.add(at);
		}
		return (atencionDtoObjResponce);
	}
	*/

}
