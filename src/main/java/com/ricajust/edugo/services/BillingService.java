package com.ricajust.edugo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricajust.edugo.models.Billing;
import com.ricajust.edugo.repositories.BillingRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BillingService {

	@Autowired
	private BillingRepository repository;

	public List<Billing> getAllBillings(){
		return repository.findAll();
	}

	public Billing getBillingById(Long id){
		Optional<Billing> billing = repository.findById(id);
		return billing.orElseThrow(() -> new RuntimeException("Cobrança não localizada com o id: " + id));
	}

	public Billing saveBilling(Billing billing){
		return repository.save(billing);
	}
}
