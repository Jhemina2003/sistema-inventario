package com.fhce.inv.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fhce.inv.model.equipoModel;
import com.fhce.inv.model.redModel;

@Repository
public interface redDao extends JpaRepository<redModel, Long> {
    boolean existsByPuerto(String puerto);
    List<redModel> findByEquipoAndEstado(equipoModel equipo, int estado);
    
    List<redModel> findByPuertoAndEstado(String puerto, int estado);
    List<redModel> findByEquipoOrderByFecharegistroDesc(equipoModel equipo);
}