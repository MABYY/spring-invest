package com.investmentsportal.portal.services;

import com.investmentsportal.portal.entities.Asset;
import com.investmentsportal.portal.entities.dtos.requests.AssetRequest;


import com.investmentsportal.portal.exceptions.ItemNotFoundException;
import com.investmentsportal.portal.mappers.AssetMapper;
import com.investmentsportal.portal.repositories.AssetRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;


@AllArgsConstructor
@Service
public class AssetService {
    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    public ResponseEntity<List<Asset>> getAssets(){
        return ResponseEntity.ok(assetRepository.findAll());
    }

    public ResponseEntity<?> getAssetById(Long id){
        var asset = assetRepository.findById(id).orElse(null);
        if (asset!=null)
            return ResponseEntity.ok(assetMapper.toDto(asset));
        else
            throw new ItemNotFoundException("Asset not found");
    }

    public ResponseEntity<?> getAssetByTicker( String ticker){
        var asset = assetRepository.findByTicker(ticker).orElse(null);
        if (asset!=null)
            return ResponseEntity.ok(assetMapper.toDto(asset));
        else
            throw new ItemNotFoundException("Asset not found");
    }

    public ResponseEntity<?> getAssetByIsin( String isin){
        var asset = assetRepository.findByIsin(isin).orElse(null);
        if (asset!=null)
            return ResponseEntity.ok(assetMapper.toDto(asset));
        else
            throw new ItemNotFoundException("Asset not found");
    }
    public ResponseEntity<?> createAsset( AssetRequest request) {
        var asset = assetRepository.findByTicker(request.getTicker()).orElse(null);
        if(asset == null){
            var new_asset = assetMapper.toEntity(request);
            assetRepository.save(new_asset);
            return ResponseEntity.ok(assetMapper.toDto(new_asset));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    public ResponseEntity<?> updateAsset( Long id, AssetRequest request) {
        var asset = assetRepository.findById(id).orElse(null);
        if (asset!=null){
            assetMapper.update( request , asset);
            return ResponseEntity.ok(assetMapper.toDto(asset));
        } else {
            throw new ItemNotFoundException("Asset not found");
        }
    }

    public ResponseEntity<?> deleteAsset( Long id) {
        var asset = assetRepository.findById(id).orElse(null);
        if (asset!=null){
            assetRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            throw new ItemNotFoundException("Asset not found");
        }

    }



}
