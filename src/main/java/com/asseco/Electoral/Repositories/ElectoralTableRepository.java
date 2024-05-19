package com.asseco.Electoral.Repositories;

import com.asseco.Electoral.Models.ElectoralTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ElectoralTableRepository extends JpaRepository<ElectoralTable, Long> {
    List<ElectoralTable> findByDistrict(String district);
    List<ElectoralTable> findByMunicipality(String municipality);
}