package com.fhce.inv.service;

import java.util.List;

import com.fhce.inv.obj.componentePcRequestDTO;
import com.fhce.inv.obj.equipoRequestDTO;
import com.fhce.inv.obj.equipoResponseDTO;
import com.fhce.inv.obj.perteneceRequestDTO;
import com.fhce.inv.obj.redRequestDTO;
import com.fhce.inv.obj.softwareRequestDTO;
import com.fhce.inv.obj.ubicacionRequestDTO;

public interface equipoService {
	/*equipoResponseDTO addCpu(equipoRequestDTO equipoRequestDTO, componentePcRequestDTO componentePcRequestDTO, perteneceRequestDTO perteneceRequestDTO);
    equipoResponseDTO addEquipo(equipoRequestDTO equipoRequestDTO, perteneceRequestDTO perteneceRequestDTO); // para otros tipos
    List<equipoResponseDTO> getEquipoTipo(Long idTipo);
    
    equipoResponseDTO updateEquipo(Long idEquipo, equipoRequestDTO equipoRequestDTO);*/
	
	/*equipoResponseDTO addCpu(
		equipoRequestDTO equipoRequestDTO, 
        componentePcRequestDTO componentePcRequestDTO, 
        perteneceRequestDTO perteneceRequestDTO,
        redRequestDTO redRequestDTO,
        ubicacionRequestDTO ubicacionRequestDTO,
        softwareRequestDTO softwareRequestDTO
	);*/
	    
    equipoResponseDTO addEquipo(
        equipoRequestDTO equipoRequestDTO, 
        componentePcRequestDTO componentePcRequestDTO,
        perteneceRequestDTO perteneceRequestDTO,
        redRequestDTO redRequestDTO,
        ubicacionRequestDTO ubicacionRequestDTO,
        softwareRequestDTO softwareRequestDTO
    );
	    
    List<equipoResponseDTO> getEquipoTipo(Long idTipo);
	    
    equipoResponseDTO updateEquipo(Long idEquipo, equipoRequestDTO equipoRequestDTO);
    
    List<equipoResponseDTO> getAllEquipos();
}
