package com.fhce.inv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.inv.obj.equipoCompletoRequestDTO;
import com.fhce.inv.obj.equipoRequestDTO;
import com.fhce.inv.obj.equipoResponseDTO;
import com.fhce.inv.service.equipoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/equipo")
@RequiredArgsConstructor
public class equipoController {

    private final equipoService equipoService;
    
    @PostMapping("/addEquipo")
    public ResponseEntity<?> addEquipo(@RequestBody equipoCompletoRequestDTO request) {
        try {
            equipoResponseDTO response = equipoService.addEquipo(
                request.getEquipoRequestDTO(),
                request.getComponentePcRequestDTO(),
                request.getPerteneceRequestDTO(),
                request.getRedRequestDTO(),
                request.getUbicacionRequestDTO(),
                request.getSoftwareRequestDTO()
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error al crear equipo");
            errorResponse.put("error", e.getMessage());
            
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*@PostMapping("/addEquipoCpu")
    public ResponseEntity<?> addCpu(@RequestBody equipoCpuCompletoRequestDTO request) {
        try {
            // Para equipoCpuRequestDTO, siempre asumimos que el tipo es 1 (CPU)
            if (request.getEquipoRequestDTO() != null) {
                request.getEquipoRequestDTO().setIdTipo(1L);
            }
            
            equipoResponseDTO response = equipoService.addCpu(
                request.getEquipoRequestDTO(), 
                request.getComponentePcRequestDTO(),
                request.getPerteneceRequestDTO(),
                request.getRedRequestDTO(),
                request.getUbicacionRequestDTO(),
                request.getSoftwareRequestDTO()
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error al crear equipo CPU");
            errorResponse.put("error", e.getMessage());
            
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    
    @GetMapping("/getEquipotipo/{idTipo}")
    public ResponseEntity<List<equipoResponseDTO>> getEquipoTipo(@PathVariable Long idTipo) {
        try {
            List<equipoResponseDTO> response = equipoService.getEquipoTipo(idTipo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/updateEquipo/{idEquipo}")
    public ResponseEntity<?> updateEquipo(
            @PathVariable Long idEquipo,
            @RequestBody equipoRequestDTO equipoRequestDTO) {
        try {
            equipoResponseDTO response = equipoService.updateEquipo(idEquipo, equipoRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error al actualizar equipo");
            errorResponse.put("error", e.getMessage());
            
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}