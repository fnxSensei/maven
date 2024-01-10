package exam4;

import jakarta.persistence.*;

@Entity
@Table(name = "fish_species")
public class FishSpecies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sortOfFish;



}