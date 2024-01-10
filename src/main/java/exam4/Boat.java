package exam4;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "boat")
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String type;
    private Integer displacement;
    private LocalDate constructionDate;
    

}
