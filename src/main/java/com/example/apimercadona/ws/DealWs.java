package com.example.apimercadona.ws;

import com.example.apimercadona.entity.Deal;
import com.example.apimercadona.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiRegistration.API_REST + ApiRegistration.DEAL)
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DealWs {

    @Autowired
    private DealService dealService;

    @GetMapping("/all-deals")
    public List<Deal> getAllDeals() {
        return dealService.getAllDeals();
    }

    @GetMapping("{dealId}")
    public Deal getDealById(@PathVariable("dealId") Long dealId) {
        return dealService.getDealById(dealId);
    }

    @PostMapping("/create-deal")
    public void createDeal(@RequestBody Deal deal) {
        dealService.createDeal(deal);
    }

    @PutMapping("/update")
    public void updateDealById(@RequestBody Deal deal, @PathVariable Long dealId) {
        Deal existingDeal = dealService.getDealById(dealId);

        existingDeal.setDealName(deal.getDealName());
        existingDeal.setDiscountPercentage(deal.getDiscountPercentage());
        existingDeal.setStartDate(deal.getStartDate());
        existingDeal.setEndDate(deal.getEndDate());

        dealService.updateDealById(existingDeal, dealId);
    }
    @DeleteMapping("/delete/{dealId}")
    public void deleteDealById(@PathVariable Long dealId) {
        dealService.deleteDealById(dealId);
    }
}
