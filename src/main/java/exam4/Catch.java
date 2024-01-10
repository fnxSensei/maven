package exam4;

import jakarta.persistence.*;

@Entity
@Table(name = "catch")
public class Catch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "fishing_trip_id")
    private FishingTrip fishingTrip;
    
    @ManyToOne
    @JoinColumn(name = "fish_species_id")
    private FishSpecies fishSpecies;
    
    private Integer catchCount;

}