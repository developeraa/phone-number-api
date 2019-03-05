# phone-number-service
APIs to handle customer phone numbers

This maven project can be build using : mvn clean install. 
  It will build, execute unit test.
  The test reports an be found in the target folder. 
  
The application can be run using : mvn spring-boot:run
	The swagger spec can be found at : http://localhost:8080/v2/api-docs
	The html view is available at: http://localhost:8080/swagger-ui.html#/phone-number-controller
	A bottom up approach has been followed where the API code is used to generate the 
specification. 
	
The service consists of 3 endpoints. 
1) GET : /phonenumbers 
This can be used to get all phone numbers in the repo

2) GET: /phonenumbers/customer/{customerId}
Used to get the phone numbers of a particular customer

3) PUT: /phonenumbers/{id}/status
Activate the phone number. 
