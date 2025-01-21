package com.ricajust.edugo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricajust.edugo.dtos.BillingDTO;
import com.ricajust.edugo.models.Billing;
import com.ricajust.edugo.services.BillingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/billings")
public class BillingController {

	@Autowired
	private final BillingService billingService;

	@GetMapping
	public ResponseEntity<List<BillingDTO>> getAllBillings() {
		List<BillingDTO> billings = billingService.getAllBillings();
		return ResponseEntity.ok(billings);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BillingDTO> getBillingById(@PathVariable Long id) {
		return billingService.getBillingById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<BillingDTO> createBilling(@RequestBody BillingDTO billing) {
		BillingDTO savedBilling = billingService.createBilling(billing);
		return ResponseEntity.status(201).body(savedBilling);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Billing> updateBilling(@PathVariable Long id, @RequestBody Billing updatedBilling) {
		return billingService.getBillingById(id).map(existingBilling -> {
			updatedBilling.setId(existingBilling.getId());
			Billing savedBilling = billingService.saveBilling(updatedBilling);
			return ResponseEntity.ok(savedBilling);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBilling(@PathVariable Long id) {
		if (billingService.getBillingById(id).isPresent()) {
			billingService.deleteBillingById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
