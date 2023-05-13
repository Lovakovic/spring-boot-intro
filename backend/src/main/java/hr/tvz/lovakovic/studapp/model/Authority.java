package hr.tvz.lovakovic.studapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "authority")
public class Authority {
    @Id
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}