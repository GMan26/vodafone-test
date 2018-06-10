package vodaone.test.george.WebActions;

import java.util.Scanner;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import vodaone.test.george.Exit;
import vodaone.test.george.StoreData.StoreCustomerData;

public class GetMobileNumber {
	
	/*
	 * This class will receive & verify information provided by the user
	 * The user may cancel at any time just by typing exit 
	 */
	private static Scanner askInput = new Scanner(System.in);
	static int getMobile_NumberTest;
	static boolean FoundMobile = false;
	
	public static void beginSearchMobileNumberProccess(){
		
			
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

			if (FormatMobileNumber.isMobileNumberFormat(getMobileNumber) == true) // checks if Input is char or int
			{
				for (int i = 0; i < StoreCustomerData.storeUserData.size(); i++) {
					
					if(getMobileNumber.equals(StoreCustomerData.storeUserData.get(i).msisdn)) 
					{
						mobileVerifed = true;
						System.out.println(getMobileNumber +" has been found");
						// the ?msisdn is not working
						System.out.println("Results at http://localhost:8080/getMobileNumber?msisdn="+getMobileNumber);						
						FoundMobile = true;
					}
					
				}
				
				if(FoundMobile == false) {
				System.out.println("Mobile number not found");
				}


			} else {
				System.err.println("Invaild input, please enter integer");
				System.err.println("Please Try Inputting Mobile Number again \n");
			}	
			
		}

		 while(mobileVerifed == false);
		
		
}
	
    

}
