package com.ricajust.edugo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricajust.edugo.models.Discipline;
import com.ricajust.edugo.models.Payment;
import com.ricajust.edugo.repositories.PaymentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

	@Autowired
	private PaymentRepository repository;

	public List<Payment> getAllPayments(){
		return repository.findAll();
	}

	public Payment getPaymentById(Long id){
		Optional<Payment> payment = repository.findById(id);
		return payment.orElseThrow(() -> new RuntimeException("Pagamento não localizado com o id: " + id));
	}

	public Payment savePayment(Payment payment) {
		return repository.save(payment);
	}
}
