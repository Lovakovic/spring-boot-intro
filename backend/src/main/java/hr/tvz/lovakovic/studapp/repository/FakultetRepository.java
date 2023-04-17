package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.domain.Fakultet;

import java.util.List;
import java.util.Optional;

public interface FakultetRepository {
    List<Fakultet> findAll();
    Optional<Fakultet> findFakultetByName(String name);
    Fakultet save(Fakultet fakultet);
}
