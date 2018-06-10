package vodaone.test.george.WebActions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import vodaone.test.george.Model.UserModel;
import vodaone.test.george.Repo.UserRepo;
import vodaone.test.george.StoreData.StoreCustomerData;

/*
 * This class will display information to the user
 * The information will contain a link to the web controller
 * in order to see the information on the database
 * 
 * The user may cancel at any time just by typing exit 
 */

@RestController

public class GetAllNumbers {
	
    static UserRepo repository = null;
	
	public static void getAllNumbers() {
		
		System.out.println("Vist http://localhost:8080/findall \n");

		        String result = "";
		        for(UserModel cust : repository.findAll()){
		        	System.out.println(result += cust.toString() +"\n");
		        }
		           

		        

	}
}
