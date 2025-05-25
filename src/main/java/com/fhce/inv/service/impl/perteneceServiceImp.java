package com.fhce.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.equipoDao;
import com.fhce.inv.dao.perteneceDao;
import com.fhce.inv.model.equipoModel;
import com.fhce.inv.model.perteneceModel;
import com.fhce.inv.obj.perteneceRequestDTO;
import com.fhce.inv.obj.perteneceResponseDTO;
import com.fhce.inv.service.perteneceService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class perteneceServiceImp implements perteneceService {

    private final perteneceDao perteneceDao;
    private final equipoDao equipoDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public perteneceResponseDTO addPertenece(perteneceRequestDTO perteneceRequestDTO) {
        //Verif equipo existe
        equipoModel equipo = equipoDao.findById(perteneceRequestDTO.getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        
        List<perteneceModel> asignacionesActivas = perteneceDao.findByEquipoAndEstado(equipo, "activo");
        if (!asignacionesActivas.isEmpty()) {
            perteneceModel asignacionActiva = asignacionesActivas.get(0);
            
            //Si el equipo ya está asignado al mismo CIF
            if (asignacionActiva.getCif().equals(perteneceRequestDTO.getCif())) {
                throw new RuntimeException("Este equipo ya está asignado a este CIF: " + perteneceRequestDTO.getCif());
            } else {
                // Si está asignado a otro CIF
                throw new RuntimeException("Este equipo ya está asignado a otro usuario con CIF: " + 
                    asignacionActiva.getCif() + ". Por favor, finalice la asignación actual antes de crear una nueva.");
            }
        }

        perteneceModel pertenece = new perteneceModel();
        pertenece.setCif(perteneceRequestDTO.getCif());
        pertenece.setFechaAdd(perteneceRequestDTO.getFechaAdd());
        pertenece.setFechaDel(perteneceRequestDTO.getFechaDel());
        pertenece.setEstado(perteneceRequestDTO.getEstado());
        pertenece.setEquipo(equipo);
        
        perteneceModel savedPertenece = perteneceDao.save(pertenece);
        
        perteneceResponseDTO response = new perteneceResponseDTO();
        response.setIdPertenece(savedPertenece.getIdPertenece());
        response.setCif(savedPertenece.getCif());
        response.setIdEquipo(equipo.getIdequipo());
        response.setCodigoEquipo(equipo.getCodigo());
        response.setFechaAdd(savedPertenece.getFechaAdd());
        response.setFechaDel(savedPertenece.getFechaDel());
        response.setEstado(savedPertenece.getEstado());
        
        return response;
    }
    
    /*@Override
    @Transactional
    public perteneceResponseDTO updatePertenece(Long id, perteneceRequestDTO perteneceRequestDTO) {

        perteneceModel pertenece = perteneceDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
        
        if (perteneceRequestDTO.getIdEquipo() != null) {
            equipoModel equipo = equipoDao.findById(perteneceRequestDTO.getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
            pertenece.setEquipo(equipo);
        }
        
        if (perteneceRequestDTO.getCif() != null) {
            pertenece.setCif(perteneceRequestDTO.getCif());
        }
        
        if (perteneceRequestDTO.getFechaAdd() != null) {
            pertenece.setFechaAdd(perteneceRequestDTO.getFechaAdd());
        }
        
        if (perteneceRequestDTO.getFechaDel() != null) {
        	pertenece.setFechaDel(perteneceRequestDTO.getFechaDel());
        }
        
        if (perteneceRequestDTO.getEstado() != null) {
            pertenece.setEstado(perteneceRequestDTO.getEstado());
        }
        
        perteneceModel updatedPertenece = perteneceDao.save(pertenece);
        
        perteneceResponseDTO response = modelMapper.map(updatedPertenece, perteneceResponseDTO.class);
        response.setIdEquipo(updatedPertenece.getEquipo().getIdequipo());
        response.setCodigoEquipo(updatedPertenece.getEquipo().getCodigo());
        
        return response;
    }*/
    
    @Override
    @Transactional
    public perteneceResponseDTO updatePertenece(Long id, perteneceRequestDTO perteneceRequestDTO) {
        perteneceModel pertenece = perteneceDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
        
        if (perteneceRequestDTO.getIdEquipo() != null) {
            equipoModel equipo = equipoDao.findById(perteneceRequestDTO.getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
            pertenece.setEquipo(equipo);
        }
        
        if (perteneceRequestDTO.getCif() != null) {
            pertenece.setCif(perteneceRequestDTO.getCif());
        }
        

        if (perteneceRequestDTO.getFechaAdd() != null) {
            pertenece.setFechaAdd(perteneceRequestDTO.getFechaAdd());
        }
        
        if (perteneceRequestDTO.getFechaDel() != null) {
            pertenece.setFechaDel(perteneceRequestDTO.getFechaDel());
        }
        
        if (perteneceRequestDTO.getEstado() != null) {
            pertenece.setEstado(perteneceRequestDTO.getEstado());
        }
        
        perteneceModel updatedPertenece = perteneceDao.save(pertenece);
        
        perteneceResponseDTO response = new perteneceResponseDTO();
        response.setIdPertenece(updatedPertenece.getIdPertenece());
        response.setCif(updatedPertenece.getCif());
        response.setIdEquipo(updatedPertenece.getEquipo().getIdequipo());
        response.setCodigoEquipo(updatedPertenece.getEquipo().getCodigo());
        response.setFechaAdd(updatedPertenece.getFechaAdd());
        response.setFechaDel(updatedPertenece.getFechaDel());
        response.setEstado(updatedPertenece.getEstado());
        
        return response;
    }
    
    @Override
    @Transactional
    public perteneceResponseDTO updatePerteneceIdEquipo(Long idEquipo, perteneceRequestDTO perteneceRequestDTO) {
        equipoModel equipo = equipoDao.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        
        List<perteneceModel> asignacionesActivas = perteneceDao.findByEquipoAndEstado(equipo, "activo");
        
        if (asignacionesActivas.isEmpty()) {
            throw new RuntimeException("No hay asignaciones activas para este equipo");
        }

        perteneceModel pertenece = asignacionesActivas.get(0);
        
        if (perteneceRequestDTO.getCif() != null) {
            pertenece.setCif(perteneceRequestDTO.getCif());
        }
        
        if (perteneceRequestDTO.getFechaAdd() != null) {
            pertenece.setFechaAdd(perteneceRequestDTO.getFechaAdd());
        }
        
        if (perteneceRequestDTO.getFechaDel() != null) {
            pertenece.setFechaDel(perteneceRequestDTO.getFechaDel());
        }
        
        if (perteneceRequestDTO.getEstado() != null) {
            pertenece.setEstado(perteneceRequestDTO.getEstado());
        }
        
        perteneceModel updatedPertenece = perteneceDao.save(pertenece);
        
        perteneceResponseDTO response = new perteneceResponseDTO();
        response.setIdPertenece(updatedPertenece.getIdPertenece());
        response.setCif(updatedPertenece.getCif());
        response.setIdEquipo(equipo.getIdequipo());
        response.setCodigoEquipo(equipo.getCodigo());
        response.setFechaAdd(updatedPertenece.getFechaAdd());
        response.setFechaDel(updatedPertenece.getFechaDel());
        response.setEstado(updatedPertenece.getEstado());
        
        return response;
    }
    
    @Override
    @Transactional
    public perteneceResponseDTO getPertenece(Long id) {
        perteneceModel pertenece = perteneceDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
        
        perteneceResponseDTO response = modelMapper.map(pertenece, perteneceResponseDTO.class);
        response.setIdEquipo(pertenece.getEquipo().getIdequipo());
        response.setCodigoEquipo(pertenece.getEquipo().getCodigo());
        
        return response;
    }
    
    @Override
    @Transactional
    public List<perteneceResponseDTO> getPerteneceByCif(Long cif) {
        List<perteneceModel> asignaciones = perteneceDao.findByCif(cif);
        
        return asignaciones.stream()
                .map(pertenece -> {
                    perteneceResponseDTO dto = modelMapper.map(pertenece, perteneceResponseDTO.class);
                    dto.setIdEquipo(pertenece.getEquipo().getIdequipo());
                    dto.setCodigoEquipo(pertenece.getEquipo().getCodigo());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<perteneceResponseDTO> getPertenecePorEquipo(Long idEquipo) {
        equipoModel equipo = equipoDao.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        
        List<perteneceModel> asignaciones = perteneceDao.findByEquipo(equipo);
        
        return asignaciones.stream()
                .map(pertenece -> {
                    perteneceResponseDTO dto = modelMapper.map(pertenece, perteneceResponseDTO.class);
                    dto.setIdEquipo(equipo.getIdequipo());
                    dto.setCodigoEquipo(equipo.getCodigo());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public perteneceResponseDTO getPropietarioActual(Long idEquipo) {
        equipoModel equipo = equipoDao.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        List<perteneceModel> asignacionesActivas = perteneceDao.findByEquipoAndEstado(equipo, "activo");
        
        if (asignacionesActivas.isEmpty()) {
            throw new RuntimeException("Este equipo no está asignado actualmente a ninguna persona");
        }
        
        perteneceModel asignacionActiva = asignacionesActivas.get(0);
        
        perteneceResponseDTO response = modelMapper.map(asignacionActiva, perteneceResponseDTO.class);
        response.setIdEquipo(equipo.getIdequipo());
        response.setCodigoEquipo(equipo.getCodigo());
        
        return response;
    }
    
    @Override
    @Transactional
    public List<perteneceResponseDTO> getPertenecePorEquipoYCif(Long idEquipo, Long cif) {
        /*equipoModel equipo = equipoDao.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));*/
    	if (!equipoDao.existsById(idEquipo)) {
            throw new RuntimeException("Equipo no encontrado");
        }
        
        List<perteneceModel> asignaciones = perteneceDao.findByEquipoIdequipoAndCif(idEquipo, cif);
        
        if (asignaciones.isEmpty()) {
            throw new RuntimeException("No se encontraron asignaciones para el equipo y CIF especificados");
        }
        
        return asignaciones.stream()
                .map(pertenece -> {
                    perteneceResponseDTO dto = new perteneceResponseDTO();
                    dto.setIdPertenece(pertenece.getIdPertenece());
                    dto.setCif(pertenece.getCif());
                    dto.setIdEquipo(pertenece.getEquipo().getIdequipo());
                    dto.setCodigoEquipo(pertenece.getEquipo().getCodigo());
                    dto.setFechaAdd(pertenece.getFechaAdd());
                    dto.setFechaDel(pertenece.getFechaDel());
                    dto.setEstado(pertenece.getEstado());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}