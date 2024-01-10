package exam4;

import jakarta.persistence.*;

@Entity
@Table(name = "crew_member")
public class CrewMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String address;
    private String position;


}