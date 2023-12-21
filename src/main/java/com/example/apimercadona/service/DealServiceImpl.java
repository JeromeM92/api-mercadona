package com.example.apimercadona.service;

import com.example.apimercadona.entity.Deal;
import com.example.apimercadona.entity.Product;
import com.example.apimercadona.repository.DealRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DealServiceImpl implements DealService{

    @Autowired
    private DealRepository dealRepository; // Assurez-vous d'avoir un DealRepository

    @Override
    public List<Deal> getAllDeals() {
        List<Deal> deals = new ArrayList<>();
        dealRepository.findAll().forEach(deals::add);
        return deals;
    }

    @Override
    public Deal getDealById(Long dealId) {
        return dealRepository.findById(dealId).orElse(null);
    }

    @Override
    public Deal createDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    @Override
    public Deal updateDeal(Deal deal, Long dealId) {
        if (dealRepository.existsById(dealId)) {
            deal.setDealId(dealId); // Assurez-vous de définir l'ID du deal
            return dealRepository.save(deal);
        }
        return null; // ou gérez cette situation autrement, par exemple, en lançant une exception
    }

    @Override
    public void deleteDealById(Long dealId) {
        dealRepository.deleteById(dealId);
    }
}
