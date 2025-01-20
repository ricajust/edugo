package com.ricajust.edugo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricajust.edugo.models.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long>{

}
