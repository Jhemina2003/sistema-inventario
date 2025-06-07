package com.fhce.inv.service;

import java.util.List;

import com.fhce.inv.obj.ubicacionRequestDTO;
import com.fhce.inv.obj.ubicacionResponseDTO;

public interface ubicacionService {
	ubicacionResponseDTO addUbicacion(ubicacionRequestDTO ubicacionRequestDTO);
    ubicacionResponseDTO updateUbicacion(Long id, ubicacionRequestDTO ubicacionRequestDTO);
    ubicacionResponseDTO getUbicacion(Long id);
    List<ubicacionResponseDTO> getUbicacionesPorEquipo(Long idEquipo);
    
    ubicacionResponseDTO getUbicacionActiva(Long idEquipo);
    List<ubicacionResponseDTO> getHistorialUbicaciones(Long idEquipo);
    ubicacionResponseDTO cambiarEstadoUbicacion(Long id, int estado);
}
