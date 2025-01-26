package com.ricajust.edugo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricajust.edugo.models.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long>{
	@EntityGraph(attributePaths = {"payments"})
	List<Billing> findByStudentId(UUID studentId);
}
