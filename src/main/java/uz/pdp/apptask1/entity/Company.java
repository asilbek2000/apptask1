package uz.pdp.apptask1.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String director;
    @OneToOne
    private Address address;

    public Company(String name, String director, Address address) {
        this.name = name;
        this.director = director;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
