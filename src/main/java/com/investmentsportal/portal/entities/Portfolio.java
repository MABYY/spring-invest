package com.investmentsportal.portal.entities;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="")
@Table(name = "Portfolio")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column( length = 100, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY  ) // owner carries the FK
    @JoinColumn(name = "users_id" )
    private Users users ;
                                            // fetch = FetchType.LAZY by default,
    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable( // create the FK // DELETE CASCADE ENABLED WHEN USER DELETED -> CHECK MIGRATION
            name = "asset_list",
            joinColumns = @JoinColumn(name="portfolio_id"),
            inverseJoinColumns = @JoinColumn(name="asset_id"))
    private Set<Asset> assets = new LinkedHashSet<>();

}
