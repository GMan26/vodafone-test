package vodaone.test.george.WebActions;

import java.util.Scanner;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import vodaone.test.george.Exit;
import vodaone.test.george.Model.UserModel;
import vodaone.test.george.StoreData.ServiceType;
import vodaone.test.george.StoreData.StoreCustomerData;
import vodaone.test.george.StoreData.UpdateCustomerData;
import vodaone.test.george.StoreData.UpdateCustomerData.UpdateCustomerData1;

public class ChangeServiceType {
	
	/*
	 * This class will receive & verify information provided by the user
	 * The user may cancel at any time just by typing exit 
	 */
	
	private static Scanner askInput = new Scanner(System.in);
	public static String getMobileNumber;
	static boolean FoundMobile = false;
	
	public static void beginSearchMobileNumberProccess(){
	
			newUserMobileInput();
		
	}
	
	private static void newUserMobileInput() 
	{
		boolean veriferedServiceType = false;
		Integer changeServiceType;
					do  
					{ 			
					System.out.println("Enter the Mobile : ");
					getMobileNumber = askInput.next();
					if(Exit.wantsExit(getMobileNumber));
						if (FormatMobileNumber.isMobileNumberFormat(getMobileNumber) == true) // checks if Input is char or int
		    			{
		    				for (int i = 0; i < StoreCustomerData.storeUserData.size(); i++) 
		    				{
		    				
		    					if(getMobileNumber.equals(StoreCustomerData.storeUserData.get(i).msisdn)) 
		    					{
		    						System.out.println("It seems like "+getMobileNumber+" has a current plan of " +
											StoreCustomerData.storeUserData.get(i).service_type.toString());
		    						System.out.println("Choose the number for service type you would like to change to");
									System.out.println("1. PrePaid");
									System.out.println("2. PostPaid");
									
		    						changeServiceType = askInput.nextInt();
		    						if(Exit.wantsExit(changeServiceType.toString()));

		    						
		    						//Provides update action on constructor on the Service Type Options - PrePaid / PostPaid
		    						switch(changeServiceType) {
		    						case 1: 
		    								if(getMobileNumber.equals(StoreCustomerData.storeUserData.get(i).msisdn)) {
		    									StoreCustomerData.storeUserData.get(i).service_type.equals(ServiceType.MOBILE_PREPAID) ;
		    									StoreCustomerData.storeUserData.get(i).service_type = ServiceType.MOBILE_PREPAID;	    									
		    									System.out.println("Payment for "+getMobileNumber+ 
		    											           " has been changed from PostPaid to PrePaid");
		    									System.out.println("To apply changes please visit http://localhost:8080/changeServiceType?msisdn="+getMobileNumber);
		    									
		    									veriferedServiceType = true;
		    									UpdateCustomerData.updateUserData.add(new UpdateCustomerData1(StoreCustomerData.storeUserData.get(i).msisdn, StoreCustomerData.storeUserData.get(i).customer_id_owner, StoreCustomerData.storeUserData.get(i).customer_id_user, ServiceType.MOBILE_PREPAID, StoreCustomerData.storeUserData.get(i).service_start_date));
		    							}
		    							break;
		    						case 2:
		    								if(getMobileNumber.equals(StoreCustomerData.storeUserData.get(i).msisdn)) {
		    									StoreCustomerData.storeUserData.get(i).service_type.equals(ServiceType.MOBILE_POSTPAID) ;
		    									StoreCustomerData.storeUserData.get(i).service_type = ServiceType.MOBILE_POSTPAID;	    									

		    									System.out.println("Payment for "+getMobileNumber+ 
		    											           " has been changed from PrePaid to PostPaid");
		    									System.out.println("To apply changes please visit http://localhost:8080/changeServiceType?msisdn="+getMobileNumber);
		    									
		    									UpdateCustomerData.updateUserData.add(new UpdateCustomerData1(StoreCustomerData.storeUserData.get(i).msisdn, StoreCustomerData.storeUserData.get(i).customer_id_owner, StoreCustomerData.storeUserData.get(i).customer_id_user, ServiceType.MOBILE_POSTPAID, StoreCustomerData.storeUserData.get(i).service_start_date));

		    									veriferedServiceType = true;
		    								
		    							}
		    							break;
		    						default:
		    							System.err.println("Chosen Invaild Option - 1.Prepaid 2.PostPaid");
		    							break;
			    						}
			    					}
			    				}
			    			}
						} while(veriferedServiceType == false);
	}

}
