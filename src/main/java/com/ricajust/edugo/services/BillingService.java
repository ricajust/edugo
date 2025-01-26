package com.ricajust.edugo.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricajust.edugo.dtos.BillingByStudentDTO;
import com.ricajust.edugo.dtos.BillingDTO;
import com.ricajust.edugo.models.Billing;
import com.ricajust.edugo.models.Payment;
import com.ricajust.edugo.models.Student;
import com.ricajust.edugo.repositories.BillingRepository;
import com.ricajust.edugo.repositories.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BillingService {

	@Autowired
	private final BillingRepository billingRepository;
	@Autowired
	private final StudentRepository studentRepository;

	public List<BillingDTO> getAllBillings() {
		return billingRepository.findAll().stream()
		.filter(billing -> billing.getStudent() != null) // Ignore Billings without Student
		.map(billing -> new BillingDTO(
			billing.getId(),
			billing.getDueDate(),
			billing.getAmount(),
			billing.getStatus(),
			billing.getStudent().getId(),
			billing.getPayments().stream().map(Payment::getId).toList()
		)).toList();
	}

	public Optional<BillingDTO> getBillingById(Long id) {
		return billingRepository.findById(id)
		.map(billing -> new BillingDTO(
			billing.getId(),
			billing.getDueDate(),
			billing.getAmount(),
			billing.getStatus(),
			billing.getStudent().getId(),
			billing.getPayments().stream().map(Payment::getId).toList()
		));
	}

	public List<BillingByStudentDTO> getBillingByStudentId(UUID studentId) {
		return billingRepository.findByStudentId(studentId).stream()
			.map(billing -> {
				BillingByStudentDTO.PaymentDTO paymentDTOs = null;
				if(billing.getPayments() != null) {
					paymentDTOs = new BillingByStudentDTO.PaymentDTO(
						billing.getId(),
						billing.getDueDate(),
						billing.getAmount()
					);
				}
				return new BillingByStudentDTO(
					billing.getId(),
					billing.getAmount(),
					billing.getDueDate(),
					billing.getStatus(),
					paymentDTOs
				);
			}).collect(Collectors.toList());
	}

	public Billing saveBilling(Billing billing) {
		return billingRepository.save(billing);
	}

	public BillingDTO createBilling(BillingDTO billingDTO) {
		// Validar o Student
		Student student = studentRepository.findById(billingDTO.getStudentId())
			.orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + billingDTO.getStudentId()));

		// Criar a entidade Billing
		Billing billing = new Billing();
		billing.setAmount(billingDTO.getAmount());
		billing.setStatus(billingDTO.getStatus());
		billing.setDueDate(billingDTO.getDueDate());
		billing.setStudent(student);

		Billing savedBilling = billingRepository.save(billing);

		return new BillingDTO(
			savedBilling.getId(),
			savedBilling.getDueDate(),
			savedBilling.getAmount(),
			savedBilling.getStatus(),
			savedBilling.getStudent().getId(),
			savedBilling.getPayments().stream().map(Payment::getId).toList()
		);
	}

	public void deleteBillingById(Long id) {
		billingRepository.deleteById(id);
	}
}
