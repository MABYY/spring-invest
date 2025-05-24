package com.investmentsportal.portal.repositories;

import com.investmentsportal.portal.entities.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    Optional<Asset> findByTicker(String ticker);

    Optional<Asset> findByIsin(String isin);

    Optional<Asset> findAllByTicker(String ticker);
}
