package vodaone.test.george.WebActions;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class FormatMobileNumber {

	/*
	 * This class will verify the mobile number information provided by the user, this is used in multiple classes
	 * This will verify the mobile number with the use of a country code
	 * 
	 * Left this class in this package for security  
	 */
	
	protected static boolean isMobileNumberFormat(String unverifiedVodafoneNumber) {
    	
    	PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    	PhoneNumber verifiedVodafoneNumber =null;
    	boolean isVerfired = false;
    	
    	try {
    		verifiedVodafoneNumber  = phoneUtil.parse(unverifiedVodafoneNumber, "MT");
    		
        	if(phoneUtil.isValidNumber(verifiedVodafoneNumber)) 
        	{ 
        		isVerfired = true;
        	} else {System.err.println("Invaild Mobile Number Format"); isVerfired = false;}
        	
        	
    	} catch (NumberParseException e) {
    	  System.err.println("Number was not verfied according to country code, e: " + e.toString());
    	}
    	
    	
    	return isVerfired;
    }
}
