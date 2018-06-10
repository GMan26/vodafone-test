package vodaone.test.george.WebActions;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import vodaone.test.george.Exit;
import vodaone.test.george.Menu;
import vodaone.test.george.StoreData.ServiceType;
import vodaone.test.george.StoreData.StoreCustomerData;

public class AddMobileNumbers {
	
	/*
	 * This class will verify & add Mobile Number to the constructor 
	 * 
	 */
	
	
	private static Scanner askInput = new Scanner(System.in);
	public static boolean verifyMobileNumber = false;
	private static boolean verifyCustomerOwner = false;
	private static boolean verifyCustomerUser = false;
	private static boolean verifyServiceType = false;
	private static boolean verifyServiceStartDate = false;
	private static String getMobileNumber;
	private static int OwnerID;
	private static int UserID;
	private static ServiceType getChosenServiceType;
	private static long Time;

	
	public static void beginAddingMobileNumberProcess(){
			
			// Processes to verify parameters to add mobile to database
			userMobileInput();
			customer_id_ownerInput();
			customer_id_userInput(); 
			customer_service_type();
			service_start_date();
			
			// Will perform an ADD action if all parameters are corrects
			if(verifyMobileNumber && verifyCustomerOwner && verifyCustomerUser &&
					verifyServiceType && verifyServiceStartDate) {
				StoreCustomerData.storeUserData.add(new StoreCustomerData.StoreCustomerData1(getMobileNumber,OwnerID,UserID,getChosenServiceType,Time));
				
				System.out.println("All parameters are verified");
				System.out.println("Please go to http://localhost:8080/saveMobileNumber to save parameters into database");
			
		}
	}
	
	
		
	private static void userMobileInput() {
	
			
		boolean mobileVerifed = false;
			System.out.println("_______________");
		
			System.out.println("Information");
			System.out.println("If you wish to cancel");
			System.out.println("just type exit");
			System.out.println("Number Format = 0035699123456 / 35699123456");
			System.out.println("_______________\n");

			do  {
				System.out.println("Enter Mobile to Add : ");
			String addMobileNumber = askInput.next();
			if(Exit.wantsExit(addMobileNumber));
			
				if (FormatMobileNumber.isMobileNumberFormat(addMobileNumber) == true) // checks if Input is char or int
				{
					System.out.println("mobile number = right format");
					verifyMobileNumber = true;
					getMobileNumber = addMobileNumber;
					mobileVerifed = true;
				} else {
					System.err.println("Number Format = 0035699123456 / 35699123456");
				}	
				
				// checks if mobile already exists
				for (int i = 0; i < StoreCustomerData.storeUserData.size(); i++) {
					if(addMobileNumber.equals(StoreCustomerData.storeUserData.get(i).msisdn)) {
						System.err.println("Number already exists");
						verifyMobileNumber = false;
						mobileVerifed = false;
					}
				}
				

			}

			 while(mobileVerifed == false);

	}
	
	private static void customer_id_ownerInput() {
		boolean ownerVerifed = false;
        Integer getOwnerID;

		do  {
			
	        do {
				System.out.println("Enter Customer Owner ID : ");
	            while (!askInput.hasNextInt()) {
	                String ownerID = askInput.next();
	    			if(Exit.wantsExit(ownerID));
	                System.err.println("Input is not a valid ID : " + ownerID);
	            }
	            getOwnerID = askInput.nextInt();
    			if(Exit.wantsExit(getOwnerID.toString()));

	        } 
	        while (getOwnerID < 0);
	        
	        System.out.println("Owner ID : " + getOwnerID);
			verifyCustomerOwner = true;
			ownerVerifed = true;
			OwnerID = getOwnerID;

		}		    
		 while(ownerVerifed == false);
		
		
}
	
	private static void customer_id_userInput() {
		
		boolean userVerifed = false;
        Integer getUserID;

		do  {
			
	        do {
				System.out.println("Enter Customer User ID : ");
	            while (!askInput.hasNextInt()) {
	                String userID = askInput.next();
	    			if(Exit.wantsExit(userID));
	                System.err.println("Input is not a vaild ID: " + userID);
	            }
	            getUserID = askInput.nextInt();
    			if(Exit.wantsExit(getUserID.toString()));

	        } 
	        while (getUserID < 0);

	        System.out.println("User ID : " + getUserID);
			verifyCustomerUser = true;
			UserID = getUserID;
			userVerifed = true;

		}		    
		 while(userVerifed == false);

}
	private static void customer_service_type() {
		
		boolean verifiedServiceType = false;
		Integer getMobileNumber; 
		
		do  {
			System.out.println("Enter Customer service_type : ");
			System.out.println("Please Enter the Option Number you wish : ");
			System.out.println("1. MOBILE_PREPAID ");
			System.out.println("2. MOBILE_POSTPAID");
			
			getMobileNumber = askInput.nextInt();
			if(Exit.wantsExit(getMobileNumber.toString()));

			//Provides add action on constructor on the Service Type Options - PrePaid / PostPaid
			switch(getMobileNumber) {
			case 1: 
				System.out.println("You chose a PrePaid Plan");
				StoreCustomerData.service_type = ServiceType.MOBILE_PREPAID;
				getChosenServiceType = ServiceType.MOBILE_PREPAID;
				verifiedServiceType = true;
				verifyServiceType = true;
				break;
			case 2:
				System.out.println("You chose a PostPaid Plan");
				getChosenServiceType = ServiceType.MOBILE_POSTPAID;
				verifiedServiceType = true;
				verifyServiceType = true;

				break;
			default:
				System.err.println("Chosen Invaild Option - 1.Prepaid 2.PostPaid");
				break;
			}
		}

		 while(verifiedServiceType == false);
		
}
	private static void service_start_date() {
		Time = System.currentTimeMillis();
		System.out.println("Timestamp Recorded : " +  Time);
		verifyServiceStartDate = true;
	}
	
	
	
	

	
    

}
