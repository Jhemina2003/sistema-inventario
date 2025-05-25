package com.fhce.inv.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fhce.inv.model.atencionModel;

@Repository
public interface atencionDao extends JpaRepository<atencionModel, Long> {
    List<atencionModel> findByEstado(int estado);
    
    List<atencionModel> findByEquipoIdequipoAndPerteneceCif(Long idEquipo, Long cif);
}
