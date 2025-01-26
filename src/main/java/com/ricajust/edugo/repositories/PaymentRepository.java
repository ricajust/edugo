package com.ricajust.edugo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricajust.edugo.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	Payment findByBillingId(Long billingId);
}
