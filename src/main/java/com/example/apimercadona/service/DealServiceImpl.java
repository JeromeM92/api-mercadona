package com.example.apimercadona.service;

import com.example.apimercadona.entity.Deal;
import com.example.apimercadona.repository.DealRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DealServiceImpl implements DealService{

    @Autowired
    private final DealRepository dealRepository;

    @Override
    public Deal getDealById(Long dealId) {

        return dealRepository.findById(dealId).orElse(null);
    }

    @Override
    public void deleteDealById(Long dealId) {
        dealRepository.deleteById(dealId);
    }

    @Override
    public void updateDealById(Deal deal, Long dealId) {
        Optional<Deal> existingDealOptional = dealRepository.findById(dealId);

        if (existingDealOptional.isPresent()) {
            Deal existingDeal = existingDealOptional.get();
            // Mettez à jour les champs nécessaires
            existingDeal.setDealName(deal.getDealName());
            existingDeal.setStartDate(deal.getStartDate());
            existingDeal.setEndDate(deal.getEndDate());
            existingDeal.setDiscountPercentage(deal.getDiscountPercentage());

            // Enregistrez les modifications dans la base de données
            dealRepository.save(existingDeal);
        }
    }

    @Override
    public void createDeal(Deal deal) {

        dealRepository.save(deal);
    }

    @Override
    public List<Deal> getAllDeals() {
        return dealRepository.findAllDeals();
    }
}
