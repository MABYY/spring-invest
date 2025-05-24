package com.investmentsportal.portal.services;

import com.investmentsportal.portal.entities.Asset;
import com.investmentsportal.portal.entities.Portfolio;
import com.investmentsportal.portal.entities.Users;
import com.investmentsportal.portal.entities.dtos.PortfolioDto;
import com.investmentsportal.portal.entities.dtos.requests.portfolio.PortfolioCreateRequest;
import com.investmentsportal.portal.entities.dtos.requests.portfolio.PortfolioUpdateRequest;
import com.investmentsportal.portal.exceptions.PortfolioNotFoundException;
import com.investmentsportal.portal.exceptions.UserNotFoundException;
import com.investmentsportal.portal.mappers.PortfolioMapper;
import com.investmentsportal.portal.repositories.AssetRepository;
import com.investmentsportal.portal.repositories.PortfolioRepository;
import com.investmentsportal.portal.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;
    private final UsersRepository usersRepository;
    private final AssetRepository assetRepository;

    public ResponseEntity<?> findAllPortfolios () {
        var portfolioList =  new ArrayList<PortfolioDto>();
        portfolioRepository.findAll().forEach( a-> {
            var dto = portfolioMapper.toDto(a);
            var tickerList = a.getAssets().stream().map(Asset::getTicker).toList();
            dto.setAssets(new HashSet<>(tickerList));
            portfolioList.add(dto);
        });
        return ResponseEntity.ok(portfolioList);

    }

   //

    public ResponseEntity<?>  findAllPortfoliosByUserId (Long id) {
        var user = usersRepository.findById(id).orElseThrow(UserNotFoundException::new);
        var portfolioList =  new ArrayList<PortfolioDto>();
        user.getPortfolios().forEach( a-> {
            var dto = portfolioMapper.toDto(a);
            var tickerList = a.getAssets().stream().map(Asset::getTicker).toList();
            dto.setAssets(new HashSet<>(tickerList));
            portfolioList.add(dto);
        });
        return ResponseEntity.ok(portfolioList);

    }

    public ResponseEntity<?> createPortfolio(PortfolioCreateRequest request, Long id) {
        var user = usersRepository.findById(id).orElseThrow(UserNotFoundException::new);

        var portfolio = createPortfolio( user , request.getName(), request.getAssets() );
        portfolioRepository.save(portfolio);

        var response = portfolioMapper.toDto(portfolio);

        List<String> assetListFinal = portfolio.getAssets().stream().map(asset -> asset.getTicker()).toList();
        response.setAssets(new HashSet<>(assetListFinal));

        return ResponseEntity.ok(response);
    };



    public ResponseEntity<?> updatePortfolio(PortfolioUpdateRequest request, Long id) {
        usersRepository.findById(id).orElseThrow(UserNotFoundException::new);
        var portfolio = portfolioRepository
                .findById(request.getId())
                .orElseThrow(PortfolioNotFoundException::new);

        portfolio.setName(request.getName());
        Set<Asset> assetList = new LinkedHashSet<>();
        Set<String> assetTickersList = new LinkedHashSet<>();
        request.getAssets().stream().forEach(a-> {
            if(assetExistsByTricker(a)){
                assetList.add(assetByTricker(a)) ;
                assetTickersList.add(a)
                ;};
        });
        portfolio.setAssets(assetList);

        portfolioRepository.save(portfolio);

        var response = portfolioMapper.toDto(portfolio);
        response.setAssets(assetTickersList);


        return ResponseEntity.ok(response);
    }


    public ResponseEntity<?> deletePortfolio(Long id) {
        portfolioRepository.findById(id).orElseThrow(PortfolioNotFoundException::new);
        portfolioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private Portfolio createPortfolio( Users user , String name, Set<String> assets ){
        var portfolio = new Portfolio();
        portfolio.setUsers(user);
        portfolio.setName(name);
        Set<Asset> assetList = new LinkedHashSet<>();
        assets.stream().forEach(a-> {
            if(assetExistsByTricker(a)){ assetList.add(assetByTricker(a)) ;};
        });
        portfolio.setAssets(assetList);
        return portfolio;
    }



    private Boolean assetExistsByTricker(String ticker) {
        return assetRepository.findAllByTicker(ticker).isPresent();
    }
    private Asset assetByTricker(String ticker) {
        return assetRepository.findAllByTicker(ticker).orElse(null);
    }



}
