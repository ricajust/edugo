package com.ricajust.edugo.controllers;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ricajust.edugo.models.Billing;
import com.ricajust.edugo.services.BillingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/billings")
public class BillingController {

	@Autowired
	private BillingService service;
	
	@GetMapping
	public ResponseEntity<List<Billing>> getAllBillings() {
		List<Billing> billings = service.getAllBillings();
		if (billings.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
		}
		return ResponseEntity.ok().body(billings);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Billing> getBillingById(@PathVariable Long id) {
		Billing billing = service.getBillingById(id);
		return ResponseEntity.ok().body(billing);
	}

	@PostMapping
	public ResponseEntity<Billing> saveBilling(@RequestBody Billing billing) {
		billing = service.saveBilling(billing);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(billing.getId()).toUri();
		// Retorna o status 201 com o header "Location"
		return ResponseEntity.created(uri).build();
	}
}
