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

import com.ricajust.edugo.dtos.PaymentDTO;
import com.ricajust.edugo.models.Payment;
import com.ricajust.edugo.services.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
	private final PaymentService paymentService;

	@GetMapping
	public ResponseEntity<List<PaymentDTO>> getAllPayments() {
		List<PaymentDTO> payments = paymentService.getAllPayments();
		return ResponseEntity.ok(payments);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
		return paymentService.getPaymentById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO payment) {
		PaymentDTO savedPayment = paymentService.createPayment(payment);
		return ResponseEntity.status(201).body(savedPayment);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
		return paymentService.getPaymentById(id).map(existingPayment -> {
			updatedPayment.setId(existingPayment.getId());
			Payment savedPayment = paymentService.savePayment(updatedPayment);
			return ResponseEntity.ok(savedPayment);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
		if (paymentService.getPaymentById(id).isPresent()) {
			paymentService.deletePaymentById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
