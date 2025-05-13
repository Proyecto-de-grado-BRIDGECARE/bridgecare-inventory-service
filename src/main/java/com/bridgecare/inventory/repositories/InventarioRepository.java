package com.bridgecare.inventory.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgecare.common.models.entities.Puente;
import com.bridgecare.inventory.models.entities.Inventario;

import jakarta.transaction.Transactional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByPuente(Puente puente);

    @Query("SELECT i FROM Inventario i WHERE " +
           "LOWER(i.observaciones) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Inventario> searchInventarios(String keyword);
    @Transactional
    void deleteByPuente(Puente puente);
    @Transactional
    void deleteByPuenteId(Long puenteId);

}
