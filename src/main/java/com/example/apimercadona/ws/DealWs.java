package com.example.apimercadona.ws;

import com.example.apimercadona.entity.Deal;
import com.example.apimercadona.payload.DealDto;
import com.example.apimercadona.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(ApiRegistration.API_REST + ApiRegistration.DEAL)
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DealWs {

    @Autowired
    private DealService dealService;

    // Convertir Deal en DealDto
    private DealDto convertToDto(Deal deal) {
        return new DealDto(deal.getDealId(), deal.getDealName(), deal.getStartDate(), deal.getEndDate(), deal.getDiscountPercentage());
    }

    // Convertir DealDto en Deal
    private Deal convertToEntity(DealDto dealDto) {
        return new Deal(dealDto.getDealName(), dealDto.getDiscountPercentage(), dealDto.getStartDate(), dealDto.getEndDate());
    }

    @GetMapping("/all-deals")
    public ResponseEntity<List<DealDto>> getAllDeals() {
        List<DealDto> deals = dealService.getAllDeals()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(deals);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DealDto> getDealById(@PathVariable Long id) {
        Deal deal = dealService.getDealById(id);
        if (deal != null) {
            return ResponseEntity.ok(convertToDto(deal));
        }
        return ResponseEntity.notFound().build();
    }
    // Créer un nouveau deal
    @PostMapping("/create-deal")
    public ResponseEntity<DealDto> createDeal(@RequestBody DealDto dealDto) {
        Deal deal = dealService.createDeal(convertToEntity(dealDto));
        return ResponseEntity.ok(convertToDto(deal));
    }
    // Mettre à jour un deal
    @PutMapping("/update/{id}")
    public ResponseEntity<DealDto> updateDeal(@PathVariable Long id, @RequestBody DealDto dealDto) {
        Deal updatedDeal = dealService.updateDeal(convertToEntity(dealDto), id);
        if (updatedDeal != null) {
            return ResponseEntity.ok(convertToDto(updatedDeal));
        }
        return ResponseEntity.notFound().build();
    }
    // Supprimer un deal
    @DeleteMapping("/delete/{id}")
    public void deleteDeal(@PathVariable Long id) {
        dealService.deleteDealById(id);
    }
}
