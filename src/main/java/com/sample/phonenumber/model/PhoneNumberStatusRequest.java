package com.sample.phonenumber.model;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class PhoneNumberStatusRequest {

	@Pattern(regexp = "New|Enabled", message = "Status must be \"New\" or \"Enabled\"")
	private String status;
}
