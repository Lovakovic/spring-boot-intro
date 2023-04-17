package hr.tvz.lovakovic.studapp.repository;

import hr.tvz.lovakovic.studapp.model.Fakultet;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FakultetRepositoryImpl implements FakultetRepository {
    public static List<Fakultet> fakulteti = new ArrayList<>();

    static {
        fakulteti.add(new Fakultet("TVZ", "Vrbik 8", "Zagreb", -56789,
                LocalDate.of(2023, 5 ,22)));
        fakulteti.add(new Fakultet("FER", "Ferovska 12", "Zagreb", -757123,
                LocalDate.of(2023, 5, 13)));
    }

    @Override
    public List<Fakultet> findAll() { return fakulteti; }

    @Override
    public Optional<Fakultet> findFakultetByName(String name) {
        return fakulteti.stream()
                .filter(fakultet -> fakultet.getName().toLowerCase().equals(name.toLowerCase()))
                .findFirst();
    }

    @Override
    public Fakultet save(Fakultet fakultet) {
        fakulteti.add(fakultet);
        return fakultet;
    }
}
