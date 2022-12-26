package models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(
            name = "secondName",
            unique = false,
            nullable = true,
            insertable = true,
            updatable = true,
            length = 250
    )
    private String surname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ElementCollection
    private List<String> skills;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String surname, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public User(String name, String surname, Gender gender, List<String> skills) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.skills = skills;
    }
}
