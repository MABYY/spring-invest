package com.investmentsportal.portal.controllers;

import com.investmentsportal.portal.entities.Asset;
import com.investmentsportal.portal.entities.dtos.requests.AssetRequest;
import com.investmentsportal.portal.mappers.AssetMapper;
import com.investmentsportal.portal.repositories.AssetRepository;
import com.investmentsportal.portal.services.AssetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Tag(name = "Assets")
@RestController
@RequestMapping("/asset")
public class AssetController {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    private final AssetService assetService;

    @GetMapping("/home")
    public  String getAssetsHome(){
        return "Assets Home";
    };

    @GetMapping("/all")
    public ResponseEntity<List<Asset>> getAssets(){
        return assetService.getAssets();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getAssetById(@PathVariable Long id){
        return assetService.getAssetById(id);

    };

    @GetMapping("/ticker/{ticker}")
    public ResponseEntity<?> getAssetByTicker(@PathVariable String ticker){
        return assetService.getAssetByTicker(ticker);
    }

    @GetMapping("/isin/{isin}")
    public ResponseEntity<?> getAssetByIsin(@PathVariable String isin){
        return assetService.getAssetByIsin(isin);
    }


    @PostMapping
    public ResponseEntity<?> createAsset(@Valid @RequestBody AssetRequest request) {
        return assetService.createAsset(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAsset(
            @PathVariable Long id, @Valid @RequestBody AssetRequest request) {
        return assetService.updateAsset(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsset( @PathVariable Long id) {
        return assetService.deleteAsset(id);
    }


}

