package com.example.apimercadona.service;

import com.example.apimercadona.entity.Deal;

import java.util.List;

public interface DealService {
    Deal getDealById(Long dealId);

    void deleteDealById(Long dealId);

    void updateDealById(Deal deal, Long dealId);

    void createDeal(Deal deal);

    List<Deal> getAllDeals();
}
