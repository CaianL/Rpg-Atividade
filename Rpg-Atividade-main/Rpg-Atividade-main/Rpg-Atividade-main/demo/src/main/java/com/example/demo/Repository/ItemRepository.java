package com.example.demo.Repository;

import com.example.demo.Models.ItensModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItensModel, Integer> {
    List<ItensModel> findByPersonagemId(int personagemId);
    List<ItensModel> findByTipoItemIgnoreCase(String tipoItem);
}
