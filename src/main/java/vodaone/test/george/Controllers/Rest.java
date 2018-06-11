package vodaone.test.george.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vodaone.test.george.Model.UserModel;
import vodaone.test.george.RestActions.CustomerActions;
import vodaone.test.george.StoreData.ServiceType;
import vodaone.test.george.StoreData.StoreCustomerData;

@RestController
public class Rest {

static StoreCustomerData SCD;
	@Autowired
	private CustomerActions customerDAO;

	//works
	@GetMapping("/customers/json/allMobile")
	public ResponseEntity list() {
		
			if(StoreCustomerData.storeUserData.isEmpty()) {
		        return new ResponseEntity(StoreCustomerData.storeUserData,HttpStatus.OK);
			}
				
		
        return new ResponseEntity(StoreCustomerData.storeUserData,HttpStatus.ACCEPTED);

	}
	
	//works
	@GetMapping("/customers/json/Mobile/{msisdn}")
	public ResponseEntity getCustomer(@PathVariable("msisdn") String msisdn) {

		int storePosition =0;

		for (int i = 0; i < StoreCustomerData.storeUserData.size(); i++) {
			if(StoreCustomerData.storeUserData.get(i).msisdn.equals(msisdn)) {
				storePosition = i;
				return new ResponseEntity(StoreCustomerData.storeUserData.get(i),HttpStatus.ACCEPTED);

			} 
		}
		return new ResponseEntity("HTTP Error : 200",HttpStatus.OK);


	}
	
	@PutMapping(value = "/customers/json/create/")
	public ResponseEntity createCustomer(@RequestBody UserModel customer) {

		int index = Web.index;
		customer = new UserModel(SCD.storeUserData.get(index).msisdn, SCD.storeUserData.get(index).customer_id_owner, SCD.storeUserData.get(index).customer_id_user, SCD.storeUserData.get(index).service_type, SCD.storeUserData.get(index).service_start_date);
    	index++;

		return new ResponseEntity(customer, HttpStatus.OK);
	}
		

	// finds, but does not delete from UserModel database,
	// needs some editing from the CustomerActions, did not have time to complete it
	@DeleteMapping("/customers/json/delete/{msisdn}")
	public ResponseEntity deleteCustomer(@PathVariable("msisdn") String msisdn) {
		
		boolean userFound = false;
		
		do { 
				for (int i = 0; i < StoreCustomerData.storeUserData.size(); i++) 
				{
					if(StoreCustomerData.storeUserData.get(i).msisdn.equals(msisdn)) {
						StoreCustomerData.storeUserData.remove(i);						
						userFound = true;
						return new ResponseEntity("Cusomer Found to be deleted " + msisdn, HttpStatus.ACCEPTED);
						
					} 
				}
			} while(userFound = false);
		
			 if(!userFound) 
			 {
					return new ResponseEntity("No Customer found for ID " + msisdn, HttpStatus.NOT_FOUND);

			 }
		
		return new ResponseEntity(msisdn, HttpStatus.OK);


		}
	
	@PostMapping("/customers/json/update/{msisdn}")
	public ResponseEntity updateCustomer(@PathVariable String msisdn, @RequestBody UserModel customer) {
		
		boolean foundUser = false;
		do {
				for (int i = 0; i < SCD.storeUserData.size(); i++) {
					if(SCD.storeUserData.get(i).msisdn.equals(msisdn) && SCD.storeUserData.get(i).service_type.equals(ServiceType.MOBILE_POSTPAID)) {
						SCD.storeUserData.get(i).service_type = ServiceType.MOBILE_PREPAID;
						foundUser = true;
					} else {
						SCD.storeUserData.get(i).service_type = ServiceType.MOBILE_POSTPAID;
						foundUser = true;
					}
				}

		} while(foundUser == false);
		
		if(foundUser) {
			return new ResponseEntity(customer, HttpStatus.OK);
		}

		return new ResponseEntity(customer, HttpStatus.NOT_FOUND);
	}
	
	

}
