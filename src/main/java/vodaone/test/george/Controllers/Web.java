package vodaone.test.george.Controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.h2.command.dml.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vodaone.test.george.Model.UserModel;
import vodaone.test.george.Repo.UserRepo;
import vodaone.test.george.StoreData.ServiceType;
import vodaone.test.george.StoreData.StoreCustomerData;
import vodaone.test.george.StoreData.UpdateCustomerData;
import vodaone.test.george.WebActions.AddMobileNumbers;
import vodaone.test.george.WebActions.ChangeServiceType;

/*
 * This Web controller is able to perform actions on the database
 * It is able to use pre-defined code and custom code found in UserRepo.java
 */
@RestController

public class Web {

	public static String changeServiceTypeID;
	public static String changeServiceTypeMobile;
	public static String changeService;
	public static int index = 0;

	StoreCustomerData SCD;
	UpdateCustomerData UCD;
	
    Map<Integer, UserModel> custStores = new HashMap<Integer, UserModel>();

	
	@Autowired
    UserRepo repository;
	
    @RequestMapping("/")
    public String home(){
        return "Hi there to everyone @ Vodafone ! :D ";
    }
    
    //returns all mobiles found in database
    @RequestMapping("/findall")
    public ResponseEntity finadAll(){
      	
    	boolean isEmpty = false;
    	
    		String result = "";
            for(UserModel cust : repository.findAll()){
                result += cust.toString() + "</br>";
                
                }
            
            if(result == "") {
                return new ResponseEntity("no mobile in db", HttpStatus.OK);

            }
            
            return new ResponseEntity(result,HttpStatus.ACCEPTED);



        
    }
    // returns mobile on given criteria (Mobile Number) 
    @RequestMapping("/getMobileNumber")
    public String findByMsisdn(@RequestParam("msisdn") String msisdn){
        String result = "";
        
        for(UserModel cust: repository.findByMsisdn(msisdn)){
            result += cust.toString() + "</br>"; 
        }
        return result;
    }
   
    // saves mobile on given criteria (Mobile Number)
    // this criteria is store in a constructor for verification
	@RequestMapping("/saveMobileNumber")
    public ResponseEntity process(){

    	if(index == SCD.storeUserData.size()) {
    		System.out.println("User Exists");
            return new ResponseEntity("User Exists",HttpStatus.CONFLICT);
    		
    	} else {
        	repository.save(new UserModel(SCD.storeUserData.get(index).msisdn, SCD.storeUserData.get(index).customer_id_owner,
            		SCD.storeUserData.get(index).customer_id_user, SCD.storeUserData.get(index).service_type, SCD.storeUserData.get(index).service_start_date));
        	
        	index++;
        	System.out.println("Input Parameters Saved for mobile : " +SCD.storeUserData.get(index-1).msisdn);
        	
        	
        	for (int i = 0; i < SCD.storeUserData.size(); i++) {
  				if(!SCD.storeUserData.get(i).msisdn.equals(SCD.storeUserData.get(i).msisdn)) {
  		            return new ResponseEntity("User Not Found",HttpStatus.NOT_FOUND);
  		            
  				}
  				
  			}
	            return new ResponseEntity("User has been added",HttpStatus.ACCEPTED);

           

    	}
    
      
        
    }

	
	// this will perform a change service type action on the user
    @RequestMapping("/changeServiceType")
    public ResponseEntity updateServiceType(@RequestParam("msisdn") String msisdn)  {	

    	/*
    	 * As mentioned in the README.md there was a slight issue with the update
    	 * To implement the action is very simple & there must be something small i am missing
    	 * In order for this test to still update the mobile number,it uses a select, delete and add method
    	 * This is NOT ideal has it removes the auto_incriment id of the user
    	 */

    	
    			for (int i = 0; i < SCD.storeUserData.size(); i++) {
					if(!SCD.storeUserData.get(i).equals(msisdn)) {
				    	return new ResponseEntity("Mobile Number " +msisdn+ " has been changed",HttpStatus.ACCEPTED);
					} 
					else {
				    	repository.removeByMsisdn(msisdn);
			    		index--;
			    	    repository.save(new UserModel(SCD.storeUserData.get(index).msisdn, SCD.storeUserData.get(index).customer_id_owner,
			            		SCD.storeUserData.get(index).customer_id_user, SCD.storeUserData.get(index).service_type, SCD.storeUserData.get(index).service_start_date));
			    	    
					}

    			}

    	    /*
    	     * The commented section below is how it should update the service type
    	     * It uses a custom action @Query action which is found inside UserRepo
    	     * It then uses a parameters to be updated in the database
    	     */

    	
//    	for (int i = 0; i < StoreCustomerData.storeUserData.size(); i++) {
//			if(StoreCustomerData.storeUserData.get(i).msisdn.equals(msisdn))   {
//				if(StoreCustomerData.storeUserData.get(0).service_type.equals(ServiceType.MOBILE_POSTPAID) ) {
//				    repository.markEntryAsRead(msisdn, ServiceType.MOBILE_PREPAID);
//
//				}
//				
//				if(StoreCustomerData.storeUserData.get(0).service_type.equals(ServiceType.MOBILE_PREPAID) ) {
//				    repository.markEntryAsRead(msisdn, ServiceType.MOBILE_POSTPAID);
//
//				}
//			}
//		}
//	    UpdateCustomerData.updateUserData.clear();
    	
	            return new ResponseEntity("Mobile Number not found",HttpStatus.NOT_FOUND);
    }
    
    
    //Performs delete action on database
    @RequestMapping("/delete")
    public ResponseEntity removeMsisdn(@RequestParam("msisdn") String msisdn){
      String result = "";
	  	repository.removeByMsisdn(msisdn);

  	
	for (int i = 0; i < SCD.storeUserData.size(); i++) {
			if(!SCD.storeUserData.get(i).msisdn.equals(msisdn)) {
			    return new ResponseEntity("Mobile "+msisdn+" has been removed",HttpStatus.ACCEPTED);
			}
			
		}
    return new ResponseEntity("Mobile " +msisdn+" to be removed has not been found",HttpStatus.NOT_FOUND);


        

  }
  
    
    
  //******* HTTP Status Code for further details *********
  
  @RequestMapping("/findallStatusCode")
  public HttpStatus findAllStatusCode(){
  	
      String result = "";
      for(UserModel cust : repository.findAll()){
          result += cust.toString() + "</br>";
          return HttpStatus.ACCEPTED;
      }
              
      
      return HttpStatus.OK;
  }
  
  @RequestMapping("/getMobileNumberStatusCode")
  public HttpStatus findByMsisdnStatusCode(@RequestParam("msisdn") String msisdn){
    String result = "";
    for(UserModel cust : repository.findAll()){
        result += cust.toString() + "</br>";
        return HttpStatus.ACCEPTED;
    }
            if(result == "") {
    }
    
    return HttpStatus.OK;
}
 
  
  @RequestMapping("/saveMobileNumberStatusCode")
  public HttpStatus processStatusCode(){

  	if(index == SCD.storeUserData.size()) {
  		System.out.println("User Exists");
  		return HttpStatus.CONFLICT;
  		
  	} else {
      	repository.save(new UserModel(SCD.storeUserData.get(index).msisdn, SCD.storeUserData.get(index).customer_id_owner,
          		SCD.storeUserData.get(index).customer_id_user, SCD.storeUserData.get(index).service_type, SCD.storeUserData.get(index).service_start_date));
      	
      	index++;
      	
      	System.out.println("Input Parameters Saved for mobile : " +SCD.storeUserData.get(index-1).msisdn);
      	
      	
      	for (int i = 0; i < SCD.storeUserData.size(); i++) {
				if(!SCD.storeUserData.get(i).msisdn.equals(SCD.storeUserData.get(i).msisdn)) {
		            return HttpStatus.NOT_FOUND;

				}
				
			}
          return HttpStatus.ACCEPTED;
         

  	}
  	
  	
  }

  

  



	  @RequestMapping("/deleteStatusCode")
	  public HttpStatus removeMsisdnStatusCode(@RequestParam("msisdn") String msisdn){
	    String result = "";
	    for(UserModel cust : repository.findAll()){
	        result += cust.toString() + "</br>";
	        return HttpStatus.ACCEPTED;
	    }
	            if(result == "") {
	    }
	    
	    return HttpStatus.OK;
	}
	  

    
    
}
