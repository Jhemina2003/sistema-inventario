package com.fhce.inv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.atencionDao;
import com.fhce.inv.dao.componentePcDao;
import com.fhce.inv.dao.equipoDao;
import com.fhce.inv.dao.perteneceDao;
import com.fhce.inv.model.atencionModel;
import com.fhce.inv.model.componentePcModel;
import com.fhce.inv.model.equipoModel;
import com.fhce.inv.model.perteneceModel;
import com.fhce.inv.model.tipoModel;
import com.fhce.inv.obj.atencionDtoObjResponce;
import com.fhce.inv.obj.atencionRequestDTO;
import com.fhce.inv.obj.atencionResponseDTO;
import com.fhce.inv.service.atencionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class atencionServiceImp implements atencionService {

    private final atencionDao atencionDao;
    private final equipoDao equipoDao;
    private final perteneceDao perteneceDao;
    private final componentePcDao componentePcDao;   
    private final ModelMapper modelMapper;
    
    
    @Transactional
    public atencionResponseDTO addAtencion(atencionRequestDTO atencionRequestDTO) {
        equipoModel equipo = equipoDao.findById(atencionRequestDTO.getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        
        perteneceModel pertenece = perteneceDao.findById(atencionRequestDTO.getIdPertenece())
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
        
        atencionModel atencion = new atencionModel();
        atencion.setEquipo(equipo);
        atencion.setPertenece(pertenece);
        atencion.setFechaSolicitud(atencionRequestDTO.getFechaSolicitud());
        atencion.setHoraSolicitud(atencionRequestDTO.getHoraSolicitud());
        atencion.setEspecificacion(atencionRequestDTO.getEspecificacion());
        atencion.setError(atencionRequestDTO.getError());
        atencion.setSolucion(atencionRequestDTO.getSolucion());
        atencion.setFechaAtencion(atencionRequestDTO.getFechaAtencion());
        atencion.setHoraAtencion(atencionRequestDTO.getHoraAtencion());
        atencion.setEstado(atencionRequestDTO.getEstado());
        
        atencionModel savedAtencion = atencionDao.save(atencion);
        
        atencionResponseDTO responseDTO = new atencionResponseDTO();
        responseDTO.setIdAtencion(savedAtencion.getIdAtencion());
        responseDTO.setIdEquipo(savedAtencion.getEquipo().getIdequipo());
        responseDTO.setCodigoEquipo(savedAtencion.getEquipo().getCodigo());
        responseDTO.setFechaSolicitud(savedAtencion.getFechaSolicitud());
        responseDTO.setHoraSolicitud(savedAtencion.getHoraSolicitud());
        responseDTO.setEspecificacion(savedAtencion.getEspecificacion());
        responseDTO.setError(savedAtencion.getError());
        responseDTO.setSolucion(savedAtencion.getSolucion());
        responseDTO.setFechaAtencion(savedAtencion.getFechaAtencion());
        responseDTO.setHoraAtencion(savedAtencion.getHoraAtencion());
        responseDTO.setEstado(savedAtencion.getEstado());
        responseDTO.setIdPertenece(savedAtencion.getPertenece().getIdPertenece());
        
        return responseDTO;
    }
    
    @Override
    @Transactional
    public atencionResponseDTO updateAtencion(Long id, atencionRequestDTO atencionRequestDTO) {
        atencionModel atencion = atencionDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Atención no encontrada"));
        
        if (atencionRequestDTO.getIdEquipo() != null) {
            equipoModel equipo = equipoDao.findById(atencionRequestDTO.getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
            atencion.setEquipo(equipo);
        }

        if (atencionRequestDTO.getIdPertenece() != null) {
            perteneceModel pertenece = perteneceDao.findById(atencionRequestDTO.getIdPertenece())
                    .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
            atencion.setPertenece(pertenece);
        }
        
        //actualizar
        if (atencionRequestDTO.getFechaSolicitud() != null) {
            atencion.setFechaSolicitud(atencionRequestDTO.getFechaSolicitud());
        }
        
        if (atencionRequestDTO.getHoraSolicitud() != null) {
            atencion.setHoraSolicitud(atencionRequestDTO.getHoraSolicitud());
        }
        
        if (atencionRequestDTO.getEspecificacion() != null) {
            atencion.setEspecificacion(atencionRequestDTO.getEspecificacion());
        }
        
        if (atencionRequestDTO.getError() != null) {
            atencion.setError(atencionRequestDTO.getError());
        }
        
        if (atencionRequestDTO.getSolucion() != null) {
            atencion.setSolucion(atencionRequestDTO.getSolucion());
        }
        
        if (atencionRequestDTO.getFechaAtencion() != null) {
            atencion.setFechaAtencion(atencionRequestDTO.getFechaAtencion());
        }
        
        if (atencionRequestDTO.getHoraAtencion() != null) {
            atencion.setHoraAtencion(atencionRequestDTO.getHoraAtencion());
        }
        
        if (atencionRequestDTO.getEstado() != null) {
            atencion.setEstado(atencionRequestDTO.getEstado());
        }
        
        atencionModel updatedAtencion = atencionDao.save(atencion);
        
        atencionResponseDTO responseDTO = new atencionResponseDTO();
        responseDTO.setIdAtencion(updatedAtencion.getIdAtencion());
        responseDTO.setIdEquipo(updatedAtencion.getEquipo().getIdequipo());
        responseDTO.setCodigoEquipo(updatedAtencion.getEquipo().getCodigo());
        responseDTO.setFechaSolicitud(updatedAtencion.getFechaSolicitud());
        responseDTO.setHoraSolicitud(updatedAtencion.getHoraSolicitud());
        responseDTO.setEspecificacion(updatedAtencion.getEspecificacion());
        responseDTO.setError(updatedAtencion.getError());
        responseDTO.setSolucion(updatedAtencion.getSolucion());
        responseDTO.setFechaAtencion(updatedAtencion.getFechaAtencion());
        responseDTO.setHoraAtencion(updatedAtencion.getHoraAtencion());
        responseDTO.setEstado(updatedAtencion.getEstado());
        responseDTO.setIdPertenece(updatedAtencion.getPertenece().getIdPertenece());
        
        return responseDTO;
    }

    
    @Override
    @Transactional
    public List<atencionDtoObjResponce> getListaEsperaAtencion() {
        //Obtener todas las atenciones en estado de espera -->estado = 0
        List<atencionModel> atenciones = atencionDao.findByEstado(0);
        
        return atenciones.stream()
                .map(this::convertToObjResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<atencionDtoObjResponce> getListaAtendidas() {
        // Obtener todas las atenciones que ya fueron atendidas --> estado = 1
        List<atencionModel> atenciones = atencionDao.findByEstado(1);
        
        return atenciones.stream()
                .map(this::convertToObjResponse)
                .collect(Collectors.toList());
    }
    
    private atencionDtoObjResponce convertToObjResponse(atencionModel atencion) {
        atencionDtoObjResponce response = new atencionDtoObjResponce();
        
        modelMapper.map(atencion, response);
        
        // Datos básicos
        response.setIdAtencion(atencion.getIdAtencion());
        response.setCif(atencion.getPertenece().getCif());
        response.setCodigo(atencion.getEquipo().getCodigo());
        
        if (atencion.getHoraSolicitud() != null) {
            response.setHoraSolicitud(atencion.getHoraSolicitud().toString());
        }
        
        response.setEquipo(atencion.getEquipo().getMarca() + " " + atencion.getEquipo().getModelo());
        response.setIdTipo(atencion.getEquipo().getTipo().getIdTipo());
        response.setDetalle(atencion.getEquipo().getDetalle());
        
        if (atencion.getHoraAtencion() != null) {
            response.setHoraAtencion(atencion.getHoraAtencion().toString());
        }
        
        // Crear un resumen más detallado
        List<String> resumen = new ArrayList<>();
        equipoModel equipo = atencion.getEquipo();
        tipoModel tipo = equipo.getTipo();
        
        resumen.add("Id Equipo: " + equipo.getIdequipo());
        resumen.add("Código: " + equipo.getCodigo());
        resumen.add("Serie/MAC: " + equipo.getMacSerie());
        resumen.add("Marca: " + equipo.getMarca());
        resumen.add("Modelo: " + equipo.getModelo());
        resumen.add("Tipo: " + tipo.getNombre() + " (" + tipo.getSigla() + ")");
        resumen.add("Detalles: " + (equipo.getDetalle() != null ? equipo.getDetalle() : "No especificado"));
        
        if (tipo.getSigla().equals("CPU")) {
            List<componentePcModel> componentes = componentePcDao.findByEquipoIdequipo(equipo.getIdequipo());
            
            if (!componentes.isEmpty()) {
                componentePcModel componente = componentes.get(0);
                
                resumen.add("--- Componentes de PC ---");
                resumen.add("Fuente: " + componente.getFuente());
                resumen.add("Memorias: " + componente.getMemorias() + " módulos");
                resumen.add("Capacidad RAM: " + componente.getCapacidad());
                resumen.add("Procesador: " + componente.getMicro());
                resumen.add("Velocidad CPU: " + componente.getMicroCapacidad());
                resumen.add("Disco: " + componente.getDisco());
                resumen.add("Protector: " + componente.getCortapico());
                resumen.add("Teclado: " + componente.getTeclado());
                resumen.add("Mouse: " + componente.getMouse());
                
                if (componente.getVersionamiento() != null) {
                    resumen.add("Versionamiento: " + componente.getVersionamiento());
                }
                
                resumen.add("Detalle: " + componente.getDetalle());
            }
        }
        
        resumen.add("--- Información del problema ---");
        resumen.add("Error: " + (atencion.getError() != null ? atencion.getError() : "No especificado"));
        resumen.add("Especificación: " + (atencion.getEspecificacion() != null ? atencion.getEspecificacion() : "No especificado"));
        
        response.setResumen(resumen);
        
        return response;
    }
    
    @Override
    @Transactional
    public List<atencionDtoObjResponce> getAtencionesPorEquipoYCif(Long idEquipo, Long cif) {
        // equipo existe
        if (!equipoDao.existsById(idEquipo)) {
            throw new RuntimeException("Equipo no encontrado");
        }
        List<atencionModel> atenciones = atencionDao.findByEquipoIdequipoAndPerteneceCif(idEquipo, cif);
        
        if (atenciones.isEmpty()) {
            throw new RuntimeException("No se encontraron atenciones para el equipo y CIF especificados");
        }
        
        return atenciones.stream()
                .map(this::convertToObjResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<atencionDtoObjResponce> getHistorialAtencionesPorEquipo(Long idEquipo) {
        equipoModel equipo = equipoDao.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        
        //Obtener todas las atenciones del equipo (atendidas y en espera) ordenadas por fecha
        List<atencionModel> atenciones = atencionDao.findByEquipoOrderByFechaSolicitudDesc(equipo);
        
        return atenciones.stream()
                .map(this::convertToObjResponseHistorial)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<atencionDtoObjResponce> getHistorialAtencionesPorCif(Long cif) {
        List<atencionModel> atenciones = atencionDao.findByPerteneceCifOrderByFechaSolicitudDesc(cif);
        
        return atenciones.stream()
                .map(this::convertToObjResponseHistorial)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<atencionDtoObjResponce> getHistorialCompleto() {
        // Obtener todas las atenciones del sistema ordenadas por fecha
        List<atencionModel> atenciones = atencionDao.findAllByOrderByFechaSolicitudDesc();
        
        return atenciones.stream()
                .map(this::convertToObjResponseHistorial)
                .collect(Collectors.toList());
    }
    
 // Metodo mejorado para incluir información histórica
    private atencionDtoObjResponce convertToObjResponseHistorial(atencionModel atencion) {
        atencionDtoObjResponce response = new atencionDtoObjResponce();
        
        // Información básica
        response.setIdAtencion(atencion.getIdAtencion());
        response.setCif(atencion.getPertenece().getCif());
        response.setCodigo(atencion.getEquipo().getCodigo());
        response.setFechaSolicitud(atencion.getFechaSolicitud());
        response.setHoraSolicitud(atencion.getHoraSolicitud());
        response.setEquipo(atencion.getEquipo().getMarca() + " " + atencion.getEquipo().getModelo());
        response.setIdTipo(atencion.getEquipo().getTipo().getIdTipo());
        response.setEspecificacion(atencion.getEspecificacion());
        response.setError(atencion.getError());
        response.setDetalle(atencion.getEquipo().getDetalle());
        response.setFechaAtencion(atencion.getFechaAtencion());
        response.setHoraAtencion(atencion.getHoraAtencion());
        response.setEstado(atencion.getEstado());
        
        // Crear resumen más detallado para el historial
        List<String> resumen = new ArrayList<>();
        equipoModel equipo = atencion.getEquipo();
        tipoModel tipo = equipo.getTipo();
        
        resumen.add("=== INFORMACIÓN DE LA ATENCIÓN ===");
        resumen.add("ID Atención: " + atencion.getIdAtencion());
        resumen.add("Estado: " + (atencion.getEstado() == 1 ? "Atendida" : "En espera"));
        resumen.add("Fecha solicitud: " + atencion.getFechaSolicitud());
        
        if (atencion.getFechaAtencion() != null) {
            resumen.add("Fecha atención: " + atencion.getFechaAtencion());
        }
        
        resumen.add("=== EQUIPO EN ESE MOMENTO ===");
        resumen.add("ID Equipo: " + equipo.getIdequipo());
        resumen.add("Código: " + equipo.getCodigo());
        resumen.add("Tipo: " + tipo.getNombre() + " (" + tipo.getSigla() + ")");
        resumen.add("Marca: " + equipo.getMarca());
        resumen.add("Modelo: " + equipo.getModelo());
        resumen.add("Serie/MAC: " + equipo.getMacSerie());
        
        resumen.add("=== ASIGNACIÓN EN ESE MOMENTO ===");
        resumen.add("CIF responsable: " + atencion.getPertenece().getCif());
        resumen.add("Fecha asignación: " + atencion.getPertenece().getFechaAdd());
        resumen.add("Estado asignación: " + atencion.getPertenece().getEstado());
        
        // Información del problema
        resumen.add("=== PROBLEMA REPORTADO ===");
        resumen.add("Error: " + (atencion.getError() != null ? atencion.getError() : "No especificado"));
        resumen.add("Especificación: " + (atencion.getEspecificacion() != null ? atencion.getEspecificacion() : "No especificado"));
        
        if (atencion.getSolucion() != null && !atencion.getSolucion().isEmpty()) {
            resumen.add("Solución aplicada: " + atencion.getSolucion());
        }
        
        // Información de componentes si es CPU
        if (tipo.getSigla().equals("CPU")) {
            List<componentePcModel> componentes = componentePcDao.findByEquipoIdequipo(equipo.getIdequipo());
            
            if (!componentes.isEmpty()) {
                componentePcModel componente = componentes.get(0);
                
                resumen.add("=== COMPONENTES DEL EQUIPO ===");
                resumen.add("Procesador: " + componente.getMicro());
                resumen.add("RAM: " + componente.getCapacidad());
                resumen.add("Disco: " + componente.getDisco());
                resumen.add("Detalles técnicos: " + componente.getDetalle());
            }
        }
        
        response.setResumen(resumen);
        
        return response;
    }
}
