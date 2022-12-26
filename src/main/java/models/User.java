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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private Passport passport;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_card",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> cards;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_sg",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sg_id")
    )
    private List<Sunglass> sunglasses;

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

    public User(String name, String surname, Passport passport) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
    }

    public User(String name, String surname, Gender gender, Passport passport, List<Card> cards) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.passport = passport;
        this.cards = cards;
    }

    public User(String name, String surname, List<Sunglass> sunglasses) {
        this.name = name;
        this.surname = surname;
        this.sunglasses = sunglasses;
    }
}
