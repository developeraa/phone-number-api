package com.sample.phonenumber;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.phonenumber.model.PhoneNumber;
import com.sample.phonenumber.model.PhoneNumberStatusRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc()
public class PhoneNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * The test checks if the response contains a hundred results
     * @throws Exception
     */
    @Test
    public void whenAllPhoneNumbers_ReturnHundredResponses() throws Exception {
        this.mockMvc.perform(get("/phonenumbers").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        	      .andExpect(jsonPath("$[0].id", is("0421000000")))
        	      .andExpect(jsonPath("$[99].id", is("0421000099")));
        	      
    }
    
    /**
     * The test checks if the response contains the phone number for the customerId passed in
     * @throws Exception
     */
    @Test
    public void whenCustomerIdIsPassed_ReturnRelevantNumbers() throws Exception {
    	PhoneNumber expectedNumber = new PhoneNumber("0421000003", "New", "1000003", "0421000003");
        String jsonResponse = this.mockMvc.perform(get("/phonenumbers/customer/1000003")).andReturn().getResponse().getContentAsString();
        List<PhoneNumber> list = new ObjectMapper().readValue(jsonResponse, new TypeReference<List<PhoneNumber>>(){});
        PhoneNumber returnedNumber = list.get(0);
        assertThat(returnedNumber,equalTo(expectedNumber));
        
    }
    
    @Test
    public void whenStatusEnabled_ReturnPhoneNumberWithUpdatedStatus() throws Exception {
    	PhoneNumberStatusRequest request = new PhoneNumberStatusRequest();
    			request.setStatus("Enabled");
    	MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/phonenumbers/0421000002/status")
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .accept(MediaType.APPLICATION_JSON)
                                      .content(new ObjectMapper().writeValueAsString(request));
        this.mockMvc.perform(builder).andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is("0421000002")))
	      .andExpect(jsonPath("$.status", is("Enabled")));
    }


}