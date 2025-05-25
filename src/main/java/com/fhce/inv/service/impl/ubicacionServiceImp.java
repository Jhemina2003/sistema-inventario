package com.fhce.inv.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.equipoDao;
import com.fhce.inv.dao.ubicacionDao;
import com.fhce.inv.model.equipoModel;
import com.fhce.inv.model.ubicacionModel;
import com.fhce.inv.obj.ubicacionRequestDTO;
import com.fhce.inv.obj.ubicacionResponseDTO;
import com.fhce.inv.service.ubicacionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ubicacionServiceImp implements ubicacionService {

    private final ubicacionDao ubicacionDao;
    private final equipoDao equipoDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public ubicacionResponseDTO addUbicacion(ubicacionRequestDTO ubicacionRequestDTO) {
        equipoModel equipo = equipoDao.findById(ubicacionRequestDTO.getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        ubicacionModel ubicacion = modelMapper.map(ubicacionRequestDTO, ubicacionModel.class);
        ubicacion.setEquipo(equipo);

        if (ubicacion.getFecha() == null) {
            ubicacion.setFecha(LocalDate.now());
        }
        
        // Establecer estado activo por defecto
        ubicacion.setEstado(1);
        ubicacionModel savedUbicacion = ubicacionDao.save(ubicacion);
        ubicacionResponseDTO response = modelMapper.map(savedUbicacion, ubicacionResponseDTO.class);
        response.setIdEquipo(equipo.getIdequipo());
        response.setCodigoEquipo(equipo.getCodigo());
        
        return response;
    }
    
    @Override
    @Transactional
    public ubicacionResponseDTO updateUbicacion(Long id, ubicacionRequestDTO ubicacionRequestDTO) {
        ubicacionModel ubicacion = ubicacionDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));
        if (ubicacionRequestDTO.getIdEquipo() != null) {
            equipoModel equipo = equipoDao.findById(ubicacionRequestDTO.getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
            ubicacion.setEquipo(equipo);
        }
        
        if (ubicacionRequestDTO.getAmbiente() != null) {
            ubicacion.setAmbiente(ubicacionRequestDTO.getAmbiente());
        }
        
        if (ubicacionRequestDTO.getLatitud() != null) {
            ubicacion.setLatitud(ubicacionRequestDTO.getLatitud());
        }
        
        if (ubicacionRequestDTO.getLongitud() != null) {
            ubicacion.setLongitud(ubicacionRequestDTO.getLongitud());
        }
        
        if (ubicacionRequestDTO.getFecha() != null) {
            ubicacion.setFecha(ubicacionRequestDTO.getFecha());
        }
        ubicacionModel updatedUbicacion = ubicacionDao.save(ubicacion);
        ubicacionResponseDTO response = modelMapper.map(updatedUbicacion, ubicacionResponseDTO.class);
        response.setIdEquipo(updatedUbicacion.getEquipo().getIdequipo());
        response.setCodigoEquipo(updatedUbicacion.getEquipo().getCodigo());
        
        return response;
    }
    
    @Override
    @Transactional
    public ubicacionResponseDTO getUbicacion(Long id) {
        // Buscar la ubicación
        ubicacionModel ubicacion = ubicacionDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));
        if (ubicacion.getEstado() != 1) {
            throw new RuntimeException("Ubicación no disponible (inactiva)");
        }
        ubicacionResponseDTO response = modelMapper.map(ubicacion, ubicacionResponseDTO.class);
        response.setIdEquipo(ubicacion.getEquipo().getIdequipo());
        response.setCodigoEquipo(ubicacion.getEquipo().getCodigo());
        
        return response;
    }
    
    @Override
    @Transactional
    public List<ubicacionResponseDTO> getUbicacionesPorEquipo(Long idEquipo) {
        equipoModel equipo = equipoDao.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        
        List<ubicacionModel> ubicaciones = ubicacionDao.findByEquipoAndEstado(equipo, 1);
        
        return ubicaciones.stream()
                .map(ubicacion -> {
                    ubicacionResponseDTO dto = modelMapper.map(ubicacion, ubicacionResponseDTO.class);
                    dto.setIdEquipo(equipo.getIdequipo());
                    dto.setCodigoEquipo(equipo.getCodigo());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public ubicacionResponseDTO cambiarEstadoUbicacion(Long id, int estado) {
        if (estado != 1 && estado != 0) {
        	throw new RuntimeException("Estado no válido. Use 1 para activo, 0 para inactivo");
        }
        ubicacionModel ubicacion = ubicacionDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));
        ubicacion.setEstado(estado);
        
        ubicacionModel updatedUbicacion = ubicacionDao.save(ubicacion);
        
        ubicacionResponseDTO response = modelMapper.map(updatedUbicacion, ubicacionResponseDTO.class);
        response.setIdEquipo(updatedUbicacion.getEquipo().getIdequipo());
        response.setCodigoEquipo(updatedUbicacion.getEquipo().getCodigo());
        
        return response;
    }
}
