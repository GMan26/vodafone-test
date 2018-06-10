package vodaone.test.george.WebActions;

import java.util.Scanner;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import vodaone.test.george.Exit;
import vodaone.test.george.StoreData.StoreCustomerData;

public class DeleteMobile {

	/*
	 * This class will delete information provided by the user
	 * The user may cancel at any time just by typing exit 
	 */
	
	private static Scanner askInput = new Scanner(System.in);
	private static int getMobileNumber;
	private static boolean FoundMobile = false;
	
	
	
	public static void beginDeleteMobileNumberProcess(){

		userMobileInput();

	}
	
	private static void userMobileInput() {
		

		boolean mobileVerifed = false;
		System.out.println("Information");
		System.out.println("If you wish to cancel");
		System.out.println("just type exit \n");

		
		do  {
			System.out.println("Enter Mobile to Search : ");
		String getMobileNumber = askInput.next();
		if(Exit.wantsExit(getMobileNumber));

		if(getMobileNumber.equals("exit")) {mobileVerifed = true;}

			if (FormatMobileNumber.isMobileNumberFormat(getMobileNumber) == true) // checks if Input is char or int
			{
				for (int i = 0; i < StoreCustomerData.storeUserData.size(); i++) {
					
					if(getMobileNumber.equals(StoreCustomerData.storeUserData.get(i).msisdn)) 
					{
						System.out.println("Please Visit http://localhost:8080/delete?msisdn="+StoreCustomerData.storeUserData.get(i).msisdn);
						StoreCustomerData.storeUserData.remove(i);
						FoundMobile = true;
					}
					
				}
				
				if(FoundMobile == false) {
				System.err.println("Mobile number not found");
				}


			} else {
				System.err.println("Number Format = 0035699123456 / 35699123456");
				System.err.println("Please Try Inputting Mobile Number again \n");
			}	
			
		}

		 while(mobileVerifed == false);
	}
	
    
}
