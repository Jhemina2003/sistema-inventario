package com.fhce.inv.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fhce.inv.model.equipoModel;
import com.fhce.inv.model.perteneceModel;

public interface perteneceDao extends JpaRepository<perteneceModel, Long> {
    List<perteneceModel> findByCif(Long cif);
    List<perteneceModel> findByEquipo(equipoModel equipo);
    List<perteneceModel> findByEquipoAndEstado(equipoModel equipo, String estado);
    
    List<perteneceModel> findByEquipoIdequipoAndCif(Long idEquipo, Long cif);
}