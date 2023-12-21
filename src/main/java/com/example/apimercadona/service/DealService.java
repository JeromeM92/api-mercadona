package com.example.apimercadona.service;

import com.example.apimercadona.entity.Deal;

import java.util.List;

public interface DealService {
    List<Deal> getAllDeals();
    Deal getDealById(Long dealId);
    Deal createDeal(Deal deal);
    Deal updateDeal(Deal deal, Long dealId);
    void deleteDealById(Long dealId);
}

