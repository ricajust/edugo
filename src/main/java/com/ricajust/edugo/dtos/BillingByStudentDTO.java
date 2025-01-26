package com.ricajust.edugo.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingByStudentDTO {
	private Long id;
	private Double amount;
	private Date dueDate;
	private String status;
	private PaymentDTO paymentDTO;// Lista de pagamentos associados

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PaymentDTO {
		private Long id;
		private Date date;
		private Double amount;
	}
}
