package exam4;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "fishing_trip")
public class FishingTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "boat_id")
    private Boat boat;
    
    private LocalDate departureDate;
    private LocalDate returnDate;
    private Double fishWeight;

}