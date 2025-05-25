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

import com.fhce.inv.obj.atencionDtoObjResponce;
import com.fhce.inv.obj.atencionRequestDTO;
import com.fhce.inv.obj.atencionResponseDTO;
import com.fhce.inv.service.atencionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/atencion")
@RequiredArgsConstructor
public class atencionController {

    private final atencionService atencionService;
    
    @PostMapping("/addAtencion") 
    public ResponseEntity<atencionResponseDTO> addAtencion(
        @RequestBody atencionRequestDTO atencionRequestDTO) {
        try {
            atencionResponseDTO response = atencionService.addAtencion(atencionRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/updateAtencion/{id}")
    public ResponseEntity<atencionResponseDTO> updateAtencion(
            @PathVariable Long id, 
            @RequestBody atencionRequestDTO atencionRequestDTO) {
        try {
            atencionResponseDTO response = atencionService.updateAtencion(id, atencionRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/lista-espera")
    public ResponseEntity<List<atencionDtoObjResponce>> getListaEsperaAtencion() {
        try {
            List<atencionDtoObjResponce> response = atencionService.getListaEsperaAtencion();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getAtencionesPorEquipoYCif/{idEquipo}/{cif}")
    public ResponseEntity<?> getAtencionesPorEquipoYCif(
            @PathVariable Long idEquipo,
            @PathVariable Long cif) {
        try {
            List<atencionDtoObjResponce> response = atencionService.getAtencionesPorEquipoYCif(idEquipo, cif);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}