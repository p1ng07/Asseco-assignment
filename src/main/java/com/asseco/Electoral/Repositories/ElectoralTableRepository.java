package com.asseco.Electoral.Repositories;

import com.asseco.Electoral.Models.ElectoralTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElectoralTableRepository extends JpaRepository<ElectoralTable, Long> {
}