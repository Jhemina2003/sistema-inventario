package com.fhce.inv.service;

import java.util.List;

import com.fhce.inv.obj.redRequestDTO;
import com.fhce.inv.obj.redResponseDTO;

public interface redService {
    redResponseDTO addRed(redRequestDTO redRequestDTO);
    redResponseDTO updateRed(Long id, redRequestDTO redRequestDTO);
    redResponseDTO getRed(Long id);
    List<redResponseDTO> getRedPorEquipo(Long idEquipo);
    redResponseDTO cambiarEstadoRed(Long id, int estado);
    
    List<redResponseDTO> getHistorialRedes(Long idEquipo);
    redResponseDTO getRedActiva(Long idEquipo);
}
