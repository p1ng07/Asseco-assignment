package com.asseco.Electoral.Repositories;

import com.asseco.Electoral.Models.District;
import com.asseco.Electoral.Models.ElectoralTable;
import com.asseco.Electoral.Models.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ElectoralTableRepository extends JpaRepository<ElectoralTable, Long> {
    List<ElectoralTable> findByDistrictAndMunicipality(District district, Municipality municipality);
    List<ElectoralTable> findByDistrict(District district);
    List<ElectoralTable> findByMunicipality(Municipality municipality);

    Optional<ElectoralTable> findByNameIdentifier(String nameIdentifier);
}