package com.asseco.Electoral.Repositories;

import com.asseco.Electoral.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);
    Optional<Person> findByCitizenCardId(String ccId);
}
