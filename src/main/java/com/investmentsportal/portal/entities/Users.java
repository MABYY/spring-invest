package com.investmentsportal.portal.entities;


import com.fasterxml.jackson.annotation.JsonView;
import com.investmentsportal.portal.entities.enums.AssetType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password" )
    private String password;

    @OneToOne(mappedBy = "users",  // eager by default -> set to lazy
            cascade = { CascadeType.REMOVE , CascadeType.PERSIST } ,
            orphanRemoval = true
    ) // 1-1
    private Profile profile;

    @OneToMany(mappedBy = "users",
            cascade = {CascadeType.REMOVE , CascadeType.PERSIST},
            orphanRemoval = true ) // 1-many
    private Set<Portfolio> portfolios = new HashSet<>();

}
