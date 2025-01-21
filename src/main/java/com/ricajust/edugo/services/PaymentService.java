package com.ricajust.edugo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricajust.edugo.dtos.BillingDTO;
import com.ricajust.edugo.dtos.PaymentDTO;
import com.ricajust.edugo.models.Billing;
import com.ricajust.edugo.models.Payment;
import com.ricajust.edugo.models.Student;
import com.ricajust.edugo.repositories.BillingRepository;
import com.ricajust.edugo.repositories.PaymentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

	@Autowired
	private final PaymentRepository paymentRepository;

	@Autowired
	private final BillingRepository billingRepository;

	public List<PaymentDTO> getAllPayments() {
		return paymentRepository.findAll().stream()
		.map(payment -> new PaymentDTO(
			payment.getId(),
			payment.getDate(),
			payment.getAmount(),
			payment.getBilling().getId()
		)).toList();
	}

	public Optional<PaymentDTO> getPaymentById(Long id) {
		return paymentRepository.findById(id)
		.map(payment -> new PaymentDTO(
			payment.getId(),
			payment.getDate(),
			payment.getAmount(),
			payment.getBilling().getId()
		));
	}

	public Payment savePayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	public PaymentDTO createPayment(PaymentDTO paymentDTO) {
		// Validar o Billing
		Billing billing = billingRepository.findById(paymentDTO.getBillingId())
			.orElseThrow(() -> new IllegalArgumentException("Billing not found with ID: " + paymentDTO.getBillingId()));
	
		// Criar a entidade Payment
		Payment payment = new Payment();
		payment.setAmount(paymentDTO.getAmount());
		payment.setDate(paymentDTO.getDate());
		payment.setBilling(billing);
	
		Payment savedPayment = paymentRepository.save(payment);
	
		return new PaymentDTO(
			savedPayment.getId(),
			savedPayment.getDate(),
			savedPayment.getAmount(),
			savedPayment.getBilling().getId()
		);
	}

	public void deletePaymentById(Long id) {
		paymentRepository.deleteById(id);
	}
}
