package com.fhce.inv.service;

import com.fhce.inv.obj.perteneceRequestDTO;
import com.fhce.inv.obj.perteneceResponseDTO;

import java.util.List;

public interface perteneceService {
    perteneceResponseDTO addPertenece(perteneceRequestDTO perteneceRequestDTO);
    perteneceResponseDTO updatePertenece(Long id, perteneceRequestDTO perteneceRequestDTO);
    perteneceResponseDTO updatePerteneceIdEquipo(Long idEquipo, perteneceRequestDTO perteneceRequestDTO);
    perteneceResponseDTO getPertenece(Long id);
    List<perteneceResponseDTO> getPerteneceByCif(Long cif);
    List<perteneceResponseDTO> getPertenecePorEquipo(Long idEquipo);
    perteneceResponseDTO getPropietarioActual(Long idEquipo);
    
    List<perteneceResponseDTO> getPertenecePorEquipoYCif(Long idEquipo, Long cif);
}
