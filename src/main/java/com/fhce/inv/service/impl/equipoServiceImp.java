package com.fhce.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.componentePcDao;
import com.fhce.inv.dao.equipoDao;
import com.fhce.inv.dao.perteneceDao;
import com.fhce.inv.dao.redDao;
import com.fhce.inv.dao.softwareDao;
import com.fhce.inv.dao.tipoDao;
import com.fhce.inv.dao.ubicacionDao;
import com.fhce.inv.model.componentePcModel;
import com.fhce.inv.model.equipoModel;
import com.fhce.inv.model.perteneceModel;
import com.fhce.inv.model.redModel;
import com.fhce.inv.model.softwareModel;
import com.fhce.inv.model.tipoModel;
import com.fhce.inv.model.ubicacionModel;
import com.fhce.inv.obj.componentePcRequestDTO;
import com.fhce.inv.obj.equipoRequestDTO;
import com.fhce.inv.obj.equipoResponseDTO;
import com.fhce.inv.obj.perteneceRequestDTO;
import com.fhce.inv.obj.redRequestDTO;
import com.fhce.inv.obj.softwareRequestDTO;
import com.fhce.inv.obj.ubicacionRequestDTO;
import com.fhce.inv.service.equipoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class equipoServiceImp implements equipoService {

    private final equipoDao equipoDao;
    private final tipoDao tipoDao;
    private final componentePcDao componentePcDao;
    private final perteneceDao perteneceDao;
    private final redDao redDao;
    private final ubicacionDao ubicacionDao;
    private final softwareDao softwareDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public equipoResponseDTO addEquipo(
    		equipoRequestDTO equipoRequestDTO,
    		componentePcRequestDTO componentesPCRequestDTO,
    		perteneceRequestDTO perteneceRequestDTO,
    		redRequestDTO redRequestDTO,
    		ubicacionRequestDTO ubicacionRequestDTO,
    		softwareRequestDTO softwareRequestDTO) {
    	
    	if (equipoDao.existsByCodigo(equipoRequestDTO.getCodigo())) {
            throw new RuntimeException("Ya existe un equipo con el código: " + equipoRequestDTO.getCodigo());
        }
        
        tipoModel tipo = tipoDao.findById(equipoRequestDTO.getIdTipo())
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
        
        equipoModel equipo = modelMapper.map(equipoRequestDTO, equipoModel.class);
        equipo.setTipo(tipo);
        
        equipoModel savedEquipo = equipoDao.save(equipo);
        
        if (componentesPCRequestDTO != null) {
            componentePcModel componentePc = new componentePcModel();
            componentePc.setFuente(componentesPCRequestDTO.getFuente());
            componentePc.setMemorias(componentesPCRequestDTO.getMemorias());
            componentePc.setCapacidad(componentesPCRequestDTO.getCapacidad());
            componentePc.setMicro(componentesPCRequestDTO.getMicro());
            componentePc.setMicroCapacidad(componentesPCRequestDTO.getMicroCapacidad());
            componentePc.setDisco(componentesPCRequestDTO.getDisco());
            componentePc.setCortapico(componentesPCRequestDTO.getCortapico());
            componentePc.setDetalle(componentesPCRequestDTO.getDetalle());
            componentePc.setTeclado(componentesPCRequestDTO.getTeclado());
            componentePc.setMouse(componentesPCRequestDTO.getMouse());
            componentePc.setVersionamiento(componentesPCRequestDTO.getVersionamiento());
            componentePc.setEquipo(savedEquipo);
            
            componentePcDao.save(componentePc);
        }
        
        if (perteneceRequestDTO != null) {
            perteneceModel pertenece = new perteneceModel();
            pertenece.setCif(perteneceRequestDTO.getCif());
            pertenece.setEquipo(savedEquipo);
            pertenece.setFechaAdd(perteneceRequestDTO.getFechaAdd());
            pertenece.setFechaDel(perteneceRequestDTO.getFechaDel());
            
            String estado = perteneceRequestDTO.getEstado();
            pertenece.setEstado(estado != null && !estado.isEmpty() ? estado : "activo");
            
            perteneceDao.save(pertenece);
        }
        
        if (redRequestDTO != null) {
            if (redRequestDTO.getPuerto() != null && redDao.existsByPuerto(redRequestDTO.getPuerto())) {
                throw new RuntimeException("El puerto ya está en uso por otro equipo");
            }
            
            redModel red = new redModel();
            red.setIp(redRequestDTO.getIp());
            red.setSegmento(redRequestDTO.getSegmento());
            red.setDns(redRequestDTO.getDns());
            red.setVlan(redRequestDTO.getVlan());
            red.setSwitchRed(redRequestDTO.getSwitchRed());
            red.setPuerto(redRequestDTO.getPuerto());
            red.setFecharegistro(redRequestDTO.getFechaRegistro());
            red.setEstado(redRequestDTO.getEstado());
            red.setEquipo(savedEquipo);
            
            redDao.save(red);
        }
        
        if (ubicacionRequestDTO != null) {
            ubicacionModel ubicacion = new ubicacionModel();
            ubicacion.setAmbiente(ubicacionRequestDTO.getAmbiente());
            ubicacion.setLatitud(ubicacionRequestDTO.getLatitud());
            ubicacion.setLongitud(ubicacionRequestDTO.getLongitud());
            ubicacion.setFecha(ubicacionRequestDTO.getFecha());
            ubicacion.setEstado(ubicacionRequestDTO.getEstado());
            ubicacion.setEquipo(savedEquipo); 
            
            ubicacionDao.save(ubicacion);
        }
        
        if (softwareRequestDTO != null) {
            softwareModel software = new softwareModel();
            software.setNombre(softwareRequestDTO.getNombre());
            software.setVersion(softwareRequestDTO.getVersion());
            software.setEstadoLicencia(softwareRequestDTO.getEstadoLicencia());
            software.setTipo(softwareRequestDTO.getTipo());
            software.setFecha(softwareRequestDTO.getFecha());
            software.setEstado(softwareRequestDTO.getEstado());
            software.setEquipo(savedEquipo);
            
            softwareDao.save(software);
        }
        

        equipoResponseDTO responseDTO = modelMapper.map(savedEquipo, equipoResponseDTO.class);
        responseDTO.setIdTipo(tipo.getIdTipo());
        responseDTO.setTipoNombre(tipo.getNombre());
        
        return responseDTO;
    }

    /*@Override
    @Transactional
    public equipoResponseDTO addCpu(
            equipoRequestDTO equipoRequestDTO, 
            componentePcRequestDTO componentesPCRequestDTO, 
            perteneceRequestDTO perteneceRequestDTO,
            redRequestDTO redRequestDTO,
            ubicacionRequestDTO ubicacionRequestDTO,
            softwareRequestDTO softwareRequestDTO) {
        
        // Forzar tipo 1 (CPU) para todos los equipos de este tipo
        Long idTipoCpu = 1L;
        tipoModel tipo = tipoDao.findById(idTipoCpu)
                .orElseThrow(() -> new RuntimeException("No encontrado"));
        
        equipoModel equipo = modelMapper.map(equipoRequestDTO, equipoModel.class);
        equipo.setTipo(tipo);
        
        equipoModel savedEquipo = equipoDao.save(equipo);
        
        if (componentesPCRequestDTO != null) {
            componentePcModel componentePc = new componentePcModel();
            componentePc.setFuente(componentesPCRequestDTO.getFuente());
            componentePc.setMemorias(componentesPCRequestDTO.getMemorias());
            componentePc.setCapacidad(componentesPCRequestDTO.getCapacidad());
            componentePc.setMicro(componentesPCRequestDTO.getMicro());
            componentePc.setMicroCapacidad(componentesPCRequestDTO.getMicroCapacidad());
            componentePc.setDisco(componentesPCRequestDTO.getDisco());
            componentePc.setCortapico(componentesPCRequestDTO.getCortapico());
            componentePc.setDetalle(componentesPCRequestDTO.getDetalle());
            componentePc.setTeclado(componentesPCRequestDTO.getTeclado());
            componentePc.setMouse(componentesPCRequestDTO.getMouse());
            componentePc.setVersionamiento(componentesPCRequestDTO.getVersionamiento());
            componentePc.setEquipo(savedEquipo);
            
            componentePcDao.save(componentePc);
        }
        
        if (perteneceRequestDTO != null) {
            perteneceModel pertenece = new perteneceModel();
            pertenece.setCif(perteneceRequestDTO.getCif());
            pertenece.setEquipo(savedEquipo);
            pertenece.setFechaAdd(perteneceRequestDTO.getFechaAdd());
            pertenece.setFechaDel(perteneceRequestDTO.getFechaDel());
            
            String estado = perteneceRequestDTO.getEstado();
            pertenece.setEstado(estado != null && !estado.isEmpty() ? estado : "activo");
            
            perteneceDao.save(pertenece);
        }
        
        if (redRequestDTO != null) {
            // Validar que el puerto no esté en uso
            if (redRequestDTO.getPuerto() != null && redDao.existsByPuerto(redRequestDTO.getPuerto())) {
                throw new RuntimeException("El puerto ya está en uso por otro equipo");
            }
            
            redModel red = new redModel();
            red.setIp(redRequestDTO.getIp());
            red.setSegmento(redRequestDTO.getSegmento());
            red.setDns(redRequestDTO.getDns());
            red.setVlan(redRequestDTO.getVlan());
            red.setSwitchRed(redRequestDTO.getSwitchRed());
            red.setPuerto(redRequestDTO.getPuerto());
            red.setFecharegistro(redRequestDTO.getFechaRegistro());
            red.setEstado(redRequestDTO.getEstado());
            red.setEquipo(savedEquipo); 
            
            redDao.save(red);
        }
        
        if (ubicacionRequestDTO != null) {
            ubicacionModel ubicacion = new ubicacionModel();
            ubicacion.setAmbiente(ubicacionRequestDTO.getAmbiente());
            ubicacion.setLatitud(ubicacionRequestDTO.getLatitud());
            ubicacion.setLongitud(ubicacionRequestDTO.getLongitud());
            ubicacion.setFecha(ubicacionRequestDTO.getFecha());
            ubicacion.setEstado(ubicacionRequestDTO.getEstado());
            ubicacion.setEquipo(savedEquipo); 
            
            ubicacionDao.save(ubicacion);
        }
        
        if (softwareRequestDTO != null) {
            softwareModel software = new softwareModel();
            software.setNombre(softwareRequestDTO.getNombre());
            software.setVersion(softwareRequestDTO.getVersion());
            software.setEstadoLicencia(softwareRequestDTO.getEstadoLicencia());
            software.setTipo(softwareRequestDTO.getTipo());
            software.setFecha(softwareRequestDTO.getFecha());
            software.setEstado(softwareRequestDTO.getEstado());
            software.setEquipo(savedEquipo); 
            
            softwareDao.save(software);
        }
        
        equipoResponseDTO responseDTO = modelMapper.map(savedEquipo, equipoResponseDTO.class);
        responseDTO.setIdTipo(tipo.getIdTipo());
        responseDTO.setTipoNombre(tipo.getNombre());
        
        return responseDTO;
    }*/
    
    @Override
    @Transactional
    public List<equipoResponseDTO> getEquipoTipo(Long idTipo) {
        //Verif tipo existe
        tipoModel tipo = tipoDao.findById(idTipo)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));

        List<equipoModel> equipos = equipoDao.findByTipoIdTipo(idTipo);
        return equipos.stream()
                .map(equipo -> {
                    equipoResponseDTO dto = modelMapper.map(equipo, equipoResponseDTO.class);

                    dto.setIdTipo(tipo.getIdTipo());
                    dto.setTipoNombre(tipo.getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public equipoResponseDTO updateEquipo(Long idEquipo, equipoRequestDTO equipoRequestDTO) {
        equipoModel equipo = equipoDao.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        if (equipoRequestDTO.getCodigo() != null) {
            equipo.setCodigo(equipoRequestDTO.getCodigo());
        }
        
        if (equipoRequestDTO.getMacSerie() != null) {
            equipo.setMacSerie(equipoRequestDTO.getMacSerie());
        }
        
        if (equipoRequestDTO.getMarca() != null) {
            equipo.setMarca(equipoRequestDTO.getMarca());
        }
        
        if (equipoRequestDTO.getModelo() != null) {
            equipo.setModelo(equipoRequestDTO.getModelo());
        }
        
        if (equipoRequestDTO.getDetalle() != null) {
            equipo.setDetalle(equipoRequestDTO.getDetalle());
        }
        
        if (equipoRequestDTO.getIdTipo() != null && !equipo.getTipo().getIdTipo().equals(equipoRequestDTO.getIdTipo())) {
            tipoModel nuevoTipo = tipoDao.findById(equipoRequestDTO.getIdTipo())
                    .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
            equipo.setTipo(nuevoTipo);
        }
        
        equipoModel updatedEquipo = equipoDao.save(equipo);
        
        equipoResponseDTO responseDTO = modelMapper.map(updatedEquipo, equipoResponseDTO.class);
        
        responseDTO.setIdTipo(updatedEquipo.getTipo().getIdTipo());
        responseDTO.setTipoNombre(updatedEquipo.getTipo().getNombre());
        
        return responseDTO;
    }
}

/*import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.componentePcDao;
import com.fhce.inv.dao.equipoDao;
import com.fhce.inv.dao.perteneceDao;
import com.fhce.inv.dao.tipoDao;
import com.fhce.inv.model.componentePcModel;
import com.fhce.inv.model.equipoModel;
import com.fhce.inv.model.perteneceModel;
import com.fhce.inv.model.tipoModel;
import com.fhce.inv.obj.componentePcRequestDTO;
import com.fhce.inv.obj.equipoRequestDTO;
import com.fhce.inv.obj.equipoResponseDTO;
import com.fhce.inv.obj.perteneceRequestDTO;
import com.fhce.inv.service.equipoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class equipoServiceImp implements equipoService {

    private final equipoDao equipoDao;
    private final tipoDao tipoDao;
    private final componentePcDao componentePcDao;
    private final perteneceDao perteneceDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public equipoResponseDTO addEquipo(equipoRequestDTO equipoRequestDTO, perteneceRequestDTO perteneceRequestDTO) {
        //buscar el tipo
        tipoModel tipo = tipoDao.findById(equipoRequestDTO.getIdTipo())
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
        
        equipoModel equipo = modelMapper.map(equipoRequestDTO, equipoModel.class);
        equipo.setTipo(tipo);
  
        equipoModel savedEquipo = equipoDao.save(equipo);
        
        if (perteneceRequestDTO != null) {
            perteneceModel pertenece = new perteneceModel();
            pertenece.setCif(perteneceRequestDTO.getCif());
            pertenece.setEquipo(savedEquipo);
            pertenece.setFechaAdd(perteneceRequestDTO.getFechaAdd());
            pertenece.setFechaDel(perteneceRequestDTO.getFechaDel());
            pertenece.setEstado(perteneceRequestDTO.getEstado());
            
            perteneceDao.save(pertenece);
        }
        
        equipoResponseDTO responseDTO = modelMapper.map(savedEquipo, equipoResponseDTO.class);
        
        responseDTO.setIdTipo(tipo.getIdTipo());
        responseDTO.setTipoNombre(tipo.getNombre());
        
        return responseDTO;
    }
    
    @Override
    @Transactional
    public equipoResponseDTO addCpu(equipoRequestDTO equipoRequestDTO, componentePcRequestDTO componentesPCRequestDTO, perteneceRequestDTO perteneceRequestDTO) {
        Long idTipoCpu = 1L;
        
        tipoModel tipo = tipoDao.findById(idTipoCpu)
                .orElseThrow(() -> new RuntimeException("Tipo CPU no encontrado"));
        
        equipoModel equipo = modelMapper.map(equipoRequestDTO, equipoModel.class);
        equipo.setTipo(tipo);
        
        equipoModel savedEquipo = equipoDao.save(equipo);
        
        componentePcModel componentePc = modelMapper.map(componentesPCRequestDTO, componentePcModel.class);
        componentePc.setEquipo(savedEquipo);
        
        componentePcDao.save(componentePc);
        
        if (perteneceRequestDTO != null) {
            perteneceModel pertenece = new perteneceModel();
            pertenece.setCif(perteneceRequestDTO.getCif());
            pertenece.setEquipo(savedEquipo); 
            
            pertenece.setFechaAdd(perteneceRequestDTO.getFechaAdd());
            pertenece.setFechaDel(perteneceRequestDTO.getFechaDel());
            
            pertenece.setEstado(perteneceRequestDTO.getEstado() != null 
                    ? perteneceRequestDTO.getEstado() : "activo");
            
            perteneceDao.save(pertenece);
        }
        
        equipoResponseDTO responseDTO = modelMapper.map(savedEquipo, equipoResponseDTO.class);
        
        responseDTO.setIdTipo(tipo.getIdTipo());
        responseDTO.setTipoNombre(tipo.getNombre());
        
        return responseDTO;
    }
    
    ///@Override
    @Transactional
    public equipoResponseDTO addCpu(equipoRequestDTO equipoRequestDTO, componentePcRequestDTO componentesPCRequestDTO, perteneceRequestDTO perteneceRequestDTO) {
        //buscar el tipo
        tipoModel tipo = tipoDao.findById(equipoRequestDTO.getIdTipo())
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
        
        // validar que es un tipo CPU
        if (!tipo.getSigla().equals("CPU")) {
            throw new RuntimeException("El tipo debe ser CPU para agregar componentes de PC");
        }
        
        //crea el equipo
        equipoModel equipo = modelMapper.map(equipoRequestDTO, equipoModel.class);
        equipo.setTipo(tipo);
        
        // guardar el equipo
        equipoModel savedEquipo = equipoDao.save(equipo);
        
        componentePcModel componentePc = modelMapper.map(componentesPCRequestDTO, componentePcModel.class);
        componentePc.setEquipo(savedEquipo);
        
        componentePcDao.save(componentePc);
        
        if (perteneceRequestDTO != null) {
            perteneceModel pertenece = new perteneceModel();
            pertenece.setCif(perteneceRequestDTO.getCif());
            pertenece.setEquipo(savedEquipo);
            pertenece.setFechaAdd(perteneceRequestDTO.getFechaAdd());
            pertenece.setFechaDel(perteneceRequestDTO.getFechaDel());
            pertenece.setEstado(perteneceRequestDTO.getEstado());
            
            perteneceDao.save(pertenece);
        }
        
        equipoResponseDTO responseDTO = modelMapper.map(savedEquipo, equipoResponseDTO.class);
        
        responseDTO.setIdTipo(tipo.getIdTipo());
        responseDTO.setTipoNombre(tipo.getNombre());
        
        return responseDTO;
    }///
    @Override
    @Transactional
    public List<equipoResponseDTO> getEquipoTipo(Long idTipo) {
        //Verif tipo existe
        tipoModel tipo = tipoDao.findById(idTipo)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));

        List<equipoModel> equipos = equipoDao.findByTipoIdTipo(idTipo);
        return equipos.stream()
                .map(equipo -> {
                    equipoResponseDTO dto = modelMapper.map(equipo, equipoResponseDTO.class);

                    dto.setIdTipo(tipo.getIdTipo());
                    dto.setTipoNombre(tipo.getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public equipoResponseDTO updateEquipo(Long idEquipo, equipoRequestDTO equipoRequestDTO) {
        equipoModel equipo = equipoDao.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        if (equipoRequestDTO.getCodigo() != null) {
            equipo.setCodigo(equipoRequestDTO.getCodigo());
        }
        
        if (equipoRequestDTO.getMacSerie() != null) {
            equipo.setMacSerie(equipoRequestDTO.getMacSerie());
        }
        
        if (equipoRequestDTO.getMarca() != null) {
            equipo.setMarca(equipoRequestDTO.getMarca());
        }
        
        if (equipoRequestDTO.getModelo() != null) {
            equipo.setModelo(equipoRequestDTO.getModelo());
        }
        
        if (equipoRequestDTO.getDetalle() != null) {
            equipo.setDetalle(equipoRequestDTO.getDetalle());
        }
        
        if (equipoRequestDTO.getIdTipo() != null && !equipo.getTipo().getIdTipo().equals(equipoRequestDTO.getIdTipo())) {
            tipoModel nuevoTipo = tipoDao.findById(equipoRequestDTO.getIdTipo())
                    .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
            equipo.setTipo(nuevoTipo);
        }
        
        equipoModel updatedEquipo = equipoDao.save(equipo);
        
        equipoResponseDTO responseDTO = modelMapper.map(updatedEquipo, equipoResponseDTO.class);
        
        responseDTO.setIdTipo(updatedEquipo.getTipo().getIdTipo());
        responseDTO.setTipoNombre(updatedEquipo.getTipo().getNombre());
        
        return responseDTO;
    }
}*/