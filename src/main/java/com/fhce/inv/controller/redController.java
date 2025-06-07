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

import com.fhce.inv.obj.redRequestDTO;
import com.fhce.inv.obj.redResponseDTO;
import com.fhce.inv.service.redService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/red")
@RequiredArgsConstructor
public class redController {

    private final redService redService;
    
    @PostMapping("/addRed")
    public ResponseEntity<?> addRed(@RequestBody redRequestDTO redRequestDTO) {
        try {
            redResponseDTO response = redService.addRed(redRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/updateRed/{id}")
    public ResponseEntity<?> updateRed(
            @PathVariable Long id,
            @RequestBody redRequestDTO redRequestDTO) {
        try {
            redResponseDTO response = redService.updateRed(id, redRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getRed/{id}")
    public ResponseEntity<?> getRed(@PathVariable Long id) {
        try {
            redResponseDTO response = redService.getRed(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getRedPorEquipo/{idEquipo}")
    public ResponseEntity<?> getRedPorEquipo(@PathVariable Long idEquipo) {
        try {
            List<redResponseDTO> response = redService.getRedPorEquipo(idEquipo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/cambiarEstadoRed/{id}/{estado}")
    public ResponseEntity<?> cambiarEstadoRed(
            @PathVariable Long id,
            @PathVariable int estado) {
        try {
            redResponseDTO response = redService.cambiarEstadoRed(id, estado);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/getRedActiva/{idEquipo}")
    public ResponseEntity<?> getRedActiva(@PathVariable Long idEquipo) {
        try {
            redResponseDTO response = redService.getRedActiva(idEquipo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getHistorialRedes/{idEquipo}")
    public ResponseEntity<?> getHistorialRedes(@PathVariable Long idEquipo) {
        try {
            List<redResponseDTO> response = redService.getHistorialRedes(idEquipo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}