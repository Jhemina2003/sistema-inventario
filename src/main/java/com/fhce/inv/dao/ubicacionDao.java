package com.fhce.inv.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fhce.inv.model.equipoModel;
import com.fhce.inv.model.ubicacionModel;

@Repository
public interface ubicacionDao extends JpaRepository<ubicacionModel, Long> {
    List<ubicacionModel> findByEquipoAndEstado(equipoModel equipo, int estado);
    
    List<ubicacionModel> findByEquipoOrderByFechaDesc(equipoModel equipo);
}