package com.fhce.inv.service;

import java.util.List;
import com.fhce.inv.obj.atencionRequestDTO;
import com.fhce.inv.obj.atencionResponseDTO;
import com.fhce.inv.obj.atencionDtoObjResponce;

public interface atencionService {
    atencionResponseDTO addAtencion(atencionRequestDTO atencionRequestDTO);
    atencionResponseDTO updateAtencion(Long id, atencionRequestDTO atencionRequestDTO);
    List<atencionDtoObjResponce> getListaEsperaAtencion();
    
    List<atencionDtoObjResponce> getAtencionesPorEquipoYCif(Long idEquipo, Long cif);
    
    List<atencionDtoObjResponce> getListaAtendidas();
    
    List<atencionDtoObjResponce> getHistorialAtencionesPorEquipo(Long idEquipo);
    List<atencionDtoObjResponce> getHistorialAtencionesPorCif(Long cif);
    List<atencionDtoObjResponce> getHistorialCompleto(); //Todas las atenciones ordenadas por fecha
}
