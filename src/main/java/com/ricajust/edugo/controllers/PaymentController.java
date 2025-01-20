package com.ricajust.edugo.controllers;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ricajust.edugo.models.Payment;
import com.ricajust.edugo.services.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
	private PaymentService service;

	@GetMapping
	public ResponseEntity<List<Payment>> getAllPayments() {
		List<Payment> payments = service.getAllPayments();
		if (payments.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
		}
		return ResponseEntity.ok().body(payments);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
		Payment payment = service.getPaymentById(id);
		return ResponseEntity.ok().body(payment);
	}
	
	@PostMapping
	public ResponseEntity<Payment> savePayment(@RequestBody Payment payment) {
		payment = service.savePayment(payment);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(payment.getId()).toUri();
		// Retorna o status 201 com o header "Location"
		return ResponseEntity.created(uri).build();
	}
}
