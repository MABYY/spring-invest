package com.investmentsportal.portal.entities;

import com.investmentsportal.portal.entities.enums.AssetType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor; // required by entities
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String isin;

    @Column( unique = true)
    private String ticker;

    @Column( length = 100, unique = true)
    private String name;

    @Column( nullable = false, name = "asset_type")
    @Enumerated(EnumType.STRING)
    private AssetType type;

    @Column( length = 100)
    private String description;

//    @ManyToMany(mappedBy = "assets") // name of the field that owns teh relationship
//    private Set<Portfolio> portfolios = new HashSet<>();

}
