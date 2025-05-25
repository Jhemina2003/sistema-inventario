package com.fhce.inv.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fhce.inv.model.equipoModel;
//import com.fhce.inv.model.tipoModel;

@Repository
public interface equipoDao extends JpaRepository<equipoModel, Long> {
    List<equipoModel> findByTipoIdTipo(Long idTipo);
    
    boolean existsByCodigo(String codigo);
    
    equipoModel findByCodigo(String codigo);
}
