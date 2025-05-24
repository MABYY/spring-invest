package com.investmentsportal.portal.controllers;

import com.investmentsportal.portal.entities.dtos.requests.portfolio.PortfolioCreateRequest;
import com.investmentsportal.portal.entities.dtos.requests.portfolio.PortfolioUpdateRequest;
import com.investmentsportal.portal.repositories.PortfolioRepository;
import com.investmentsportal.portal.repositories.UsersRepository;
import com.investmentsportal.portal.services.PortfolioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/portfolio")
@AllArgsConstructor
@Tag(name = "Portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllPortfolios(){
        return portfolioService.findAllPortfolios();
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> create (@PathVariable Long userId,
                                     @Valid @RequestBody PortfolioCreateRequest request) {
        return portfolioService.createPortfolio(request, userId);
    }


    @GetMapping("/all/{userId}")
    public ResponseEntity<?> getPortfoliosByUserId(@PathVariable Long userId) {
      return portfolioService.findAllPortfoliosByUserId(userId);
    }



    @PutMapping("/{userId}")
    public ResponseEntity<?> updatePortfolio(@PathVariable Long userId,
                                            @RequestBody PortfolioUpdateRequest request) {
        return portfolioService.updatePortfolio(request,userId);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return portfolioService.deletePortfolio(id);
    }


}
