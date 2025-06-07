package com.fhce.inv.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.inv.obj.ubicacionRequestDTO;
import com.fhce.inv.obj.ubicacionResponseDTO;
import com.fhce.inv.service.ubicacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ubicacion")
@RequiredArgsConstructor
public class ubicacionController {

    private final ubicacionService ubicacionService;
    
    @PostMapping("/addUbicacion")
    public ResponseEntity<?> addUbicacion(@RequestBody ubicacionRequestDTO ubicacionRequestDTO) {
        try {
            ubicacionResponseDTO response = ubicacionService.addUbicacion(ubicacionRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/updateUbicacion/{id}")
    public ResponseEntity<?> updateUbicacion(
            @PathVariable Long id,
            @RequestBody ubicacionRequestDTO ubicacionRequestDTO) {
        try {
            ubicacionResponseDTO response = ubicacionService.updateUbicacion(id, ubicacionRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getUbicacion/{id}")
    public ResponseEntity<?> getUbicacion(@PathVariable Long id) {
        try {
            ubicacionResponseDTO response = ubicacionService.getUbicacion(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getUbicacionesPorEquipo/{idEquipo}")
    public ResponseEntity<?> getUbicacionesPorEquipo(@PathVariable Long idEquipo) {
        try {
            List<ubicacionResponseDTO> response = ubicacionService.getUbicacionesPorEquipo(idEquipo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getUbicacionActiva/{idEquipo}")
    public ResponseEntity<?> getUbicacionActiva(@PathVariable Long idEquipo) {
        try {
            ubicacionResponseDTO response = ubicacionService.getUbicacionActiva(idEquipo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getHistorialUbicaciones/{idEquipo}")
    public ResponseEntity<?> getHistorialUbicaciones(@PathVariable Long idEquipo) {
        try {
            List<ubicacionResponseDTO> response = ubicacionService.getHistorialUbicaciones(idEquipo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cambiarEstadoUbicacion/{id}/{estado}")
    public ResponseEntity<?> cambiarEstadoUbicacion(
            @PathVariable Long id,
            @PathVariable int estado) {
        try {
            ubicacionResponseDTO response = ubicacionService.cambiarEstadoUbicacion(id, estado);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}