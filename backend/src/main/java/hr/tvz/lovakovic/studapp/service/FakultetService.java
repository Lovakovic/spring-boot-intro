package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.command.FakultetCommand;
import hr.tvz.lovakovic.studapp.domain.Fakultet;
import hr.tvz.lovakovic.studapp.dto.FakultetDTO;

import java.util.List;

public interface FakultetService {
    List<FakultetDTO> findAll();

    FakultetDTO findFakultetByName(String name);
    Boolean addFakultet(FakultetCommand fakultetCommand);
    Boolean deleteFakultetByName(String name);
    FakultetDTO convertToFakultetDTO(Fakultet fakultet);
}
