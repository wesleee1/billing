package com.example.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.billing.model.BillingOrder;

@Repository
public interface OrderRepository extends JpaRepository<BillingOrder, Long> {
}
