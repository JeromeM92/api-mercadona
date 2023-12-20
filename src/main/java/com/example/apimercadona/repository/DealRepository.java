package com.example.apimercadona.repository;

import com.example.apimercadona.entity.Deal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository extends CrudRepository<Deal, Long> {

    @Query("SELECT a FROM Deal a")
    List<Deal> findAllDeals();
}
