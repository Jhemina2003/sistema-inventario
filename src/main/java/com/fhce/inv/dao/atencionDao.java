package com.fhce.inv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fhce.inv.model.atencionModel;
import com.fhce.inv.model.equipoModel;

@Repository
public interface atencionDao extends JpaRepository<atencionModel, Long> {
    List<atencionModel> findByEstado(int estado);
    
    List<atencionModel> findByEquipoIdequipoAndPerteneceCif(Long idEquipo, Long cif);
    
    List<atencionModel> findByEquipo(equipoModel equipo);
    List<atencionModel> findByEquipoAndEstado(equipoModel equipo, int estado);
    List<atencionModel> findByEquipoOrderByFechaSolicitudDesc(equipoModel equipo);
    List<atencionModel> findByPerteneceCifOrderByFechaSolicitudDesc(Long cif);
    List<atencionModel> findAllByOrderByFechaSolicitudDesc();
}
