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

import com.fhce.inv.obj.perteneceRequestDTO;
import com.fhce.inv.obj.perteneceResponseDTO;
import com.fhce.inv.service.perteneceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pertenece")
@RequiredArgsConstructor
public class perteneceController {

    private final perteneceService perteneceService;
    
    @PostMapping("/addPertenece")
    public ResponseEntity<perteneceResponseDTO> addPertenece(@RequestBody perteneceRequestDTO perteneceRequestDTO) {
        try {
            perteneceResponseDTO response = perteneceService.addPertenece(perteneceRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/updatePertenece/{id}")
    public ResponseEntity<perteneceResponseDTO> updatePertenece(
            @PathVariable Long id,
            @RequestBody perteneceRequestDTO perteneceRequestDTO) {
        try {
            perteneceResponseDTO response = perteneceService.updatePertenece(id, perteneceRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/updatePerteneceIdEquipo/{idEquipo}")
    public ResponseEntity<?> updatePerteneceByEquipo(
            @PathVariable Long idEquipo,
            @RequestBody perteneceRequestDTO perteneceRequestDTO) {
        try {
            perteneceResponseDTO response = perteneceService.updatePerteneceIdEquipo(idEquipo, perteneceRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error al actualizar asignación por equipo");
            errorResponse.put("error", e.getMessage());
            
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getPertenece/{id}")
    public ResponseEntity<perteneceResponseDTO> getPertenece(@PathVariable Long id) {
        try {
            perteneceResponseDTO response = perteneceService.getPertenece(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getPertenecePorCif/{cif}")
    public ResponseEntity<List<perteneceResponseDTO>> getPerteneceByCif(@PathVariable Long cif) {
        try {
            List<perteneceResponseDTO> response = perteneceService.getPerteneceByCif(cif);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getPertenecePorEquipo/{idEquipo}")
    public ResponseEntity<List<perteneceResponseDTO>> getPertenecePorEquipo(@PathVariable Long idEquipo) {
        try {
            List<perteneceResponseDTO> response = perteneceService.getPertenecePorEquipo(idEquipo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/getPertenecePorEquipoYCif/{idEquipo}/{cif}")
    public ResponseEntity<?> getPertenecePorEquipoYCif(
            @PathVariable Long idEquipo,
            @PathVariable Long cif) {
        try {
            List<perteneceResponseDTO> response = perteneceService.getPertenecePorEquipoYCif(idEquipo, cif);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getEquiposPorTipo/{idTipo}")
    public ResponseEntity<?> getEquiposPorTipo(@PathVariable Long idTipo) {
        try {
            List<perteneceResponseDTO> response = perteneceService.getEquiposPorTipo(idTipo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}