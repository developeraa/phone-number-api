package com.sample.phonenumber.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sample.phonenumber.model.PhoneNumber;
import com.sample.phonenumber.model.PhoneNumberStatusRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PhoneNumberService {

	private static List<PhoneNumber> ALL_NUMBERS_REPO = new ArrayList<PhoneNumber>();

	static {
		long customerIdStart = 1000000;
		long phoneNumber = 421000000;
		for (int i = 0; i < 100; i++) {
			String phoneNumberValue = "0" + String.valueOf(phoneNumber + i);
			ALL_NUMBERS_REPO.add(new PhoneNumber(phoneNumberValue,"New",
					String.valueOf(customerIdStart + i % 10),phoneNumberValue));
		}
	}

	public List<PhoneNumber> getAllPhoneNumbers() {
		return ALL_NUMBERS_REPO;
	}

	public List<PhoneNumber> getPhoneNumbersForCustomerId(String customerId) {
		log.info("Returning phone number for customer"+customerId);
		return ALL_NUMBERS_REPO.stream()
				.filter(phoneNumber -> phoneNumber.getCustomerId().equals(customerId))
				.collect(Collectors.toList());
	}
	
	public PhoneNumber updatePhoneNumberStatus(String id, PhoneNumberStatusRequest statusRequest) {
		log.info("Updating phone number status for "+id);
		 PhoneNumber existingNumber = ALL_NUMBERS_REPO.stream()
				.filter(existingPhoneNumber -> existingPhoneNumber.getId().equals(id))
				.findFirst().get();
		
		 existingNumber.setStatus(statusRequest.getStatus());
		 return existingNumber;
	}

}
