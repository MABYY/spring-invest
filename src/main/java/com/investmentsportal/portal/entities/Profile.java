package com.investmentsportal.portal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "company")
    private String company;

    @OneToOne(fetch = FetchType.LAZY , cascade = { CascadeType.REMOVE , CascadeType.PERSIST } )
    @JoinColumn(name = "id") // owner of the 1-1 relation
    @MapsId // id & foreign key
    private Users users;

}
