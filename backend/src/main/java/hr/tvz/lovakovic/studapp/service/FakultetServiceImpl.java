package hr.tvz.lovakovic.studapp.service;

import hr.tvz.lovakovic.studapp.command.FakultetCommand;
import hr.tvz.lovakovic.studapp.model.Fakultet;
import hr.tvz.lovakovic.studapp.model.FakultetDTO;
import hr.tvz.lovakovic.studapp.repository.FakultetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static hr.tvz.lovakovic.studapp.repository.FakultetRepositoryImpl.fakulteti;

@Service
public class FakultetServiceImpl implements FakultetService {
    private final FakultetRepository fakultetRepository;

    @Autowired
    public FakultetServiceImpl(FakultetRepository fakultetRepository) { this.fakultetRepository = fakultetRepository; }

    @Override
    public List<FakultetDTO> findAll() {
        return fakultetRepository.findAll().stream()
                .map(this::convertToFakultetDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FakultetDTO findFakultetByName(String name) {
        return fakultetRepository.findFakultetByName(name)
                .map(this::convertToFakultetDTO)
                .orElse(null);
    }

    @Override
    public Boolean addFakultet(FakultetCommand fakultetCommand) {
        // Check if fakultet with the same name already exists
        if(fakultetRepository.findFakultetByName(fakultetCommand.getName()).isPresent()) {
            return false;
        }

        Fakultet fakultet = new Fakultet(fakultetCommand.getName(),
                fakultetCommand.getStreet(), fakultetCommand.getCity(),
                fakultetCommand.getDebtAmount(), fakultetCommand.getNextPromotionDate());
        fakultetRepository.save(fakultet);
        return true;
    }

    @Override
    public Boolean deleteFakultetByName(String name) {
        Optional<Fakultet> fakultetToDelete = fakultetRepository.findFakultetByName(name);
        if(fakultetToDelete.isPresent()) {
            fakulteti.remove(fakultetToDelete.get());
            return true;
        }

        return false;
    }

    @Override
    public FakultetDTO convertToFakultetDTO(Fakultet fakultet) {
        String fullAddress = fakultet.getStreet() + ", " + fakultet.getCity();
        return new FakultetDTO(fakultet.getName(), fullAddress, fakultet.getNextPromotionDate());
    }
}
