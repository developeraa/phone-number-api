package com.sample.phonenumber.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode()
public class PhoneNumber {

	private String id;
	private String status;
	private String customerId;
	private String number;

}
