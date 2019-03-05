package com.sample.phonenumber.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.phonenumber.model.PhoneNumber;
import com.sample.phonenumber.model.PhoneNumberStatusRequest;
import com.sample.phonenumber.service.PhoneNumberService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class PhoneNumberController {

	PhoneNumberService phoneNumbersService;

	@GetMapping("/phonenumbers")
	public List<PhoneNumber> allPhoneNumbers() {
		return phoneNumbersService.getAllPhoneNumbers();
	}

	@GetMapping("/phonenumbers/customer/{customerId}")
	public List<PhoneNumber> customerPhoneNumbers(@PathVariable String customerId) {
		return phoneNumbersService.getPhoneNumbersForCustomerId(customerId);
	}

	@PutMapping("/phonenumbers/{id}/status")
	public PhoneNumber enablePhoneNumber(@PathVariable String id, @RequestBody @Valid PhoneNumberStatusRequest statusRequest) {
		return phoneNumbersService.updatePhoneNumberStatus(id, statusRequest);
	}

}
