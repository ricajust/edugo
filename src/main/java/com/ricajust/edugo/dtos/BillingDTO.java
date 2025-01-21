package com.ricajust.edugo.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingDTO {
	private Long id;
	private Date dueDate;
	private Double amount;
	private String status;
	private UUID studentId;
	private List<Long> paymentIds;
}
