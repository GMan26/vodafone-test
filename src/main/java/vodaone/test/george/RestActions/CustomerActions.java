package vodaone.test.george.RestActions;

import java.util.List;

import org.springframework.stereotype.Component;
import vodaone.test.george.Controllers.Web;
import vodaone.test.george.Model.UserModel;
import vodaone.test.george.StoreData.ServiceType;
import vodaone.test.george.StoreData.StoreCustomerData;

@Component
public class CustomerActions {
	
	StoreCustomerData SCD;
	
	/**
	 * Returns list of customers from h2 database.
	 * 
	 * @return list of customers
	 */

	
	public List list() {
		List list= null;
		for (int i = 0; i < StoreCustomerData.storeUserData.size(); i++) {
			list.add(0, StoreCustomerData.storeUserData.get(i));
		}
		return list;
	}

	

	/**
	 * Create new customer in h2 database. Updates the id and insert new
	 * customer in list.
	 * 
	 * @param customer
	 *            Customer object
	 * @return customer object with updated id
	 */
	public UserModel create(UserModel customer) {
		
		//links to index from web controller, in order not to clash
		int index = Web.index;
		customer = new UserModel(SCD.storeUserData.get(index).msisdn, SCD.storeUserData.get(index).customer_id_owner, SCD.storeUserData.get(index).customer_id_user, SCD.storeUserData.get(index).service_type, SCD.storeUserData.get(index).service_start_date);
    	index++;

		return customer;
	}
	
	/**
	 * Delete the customer object from h2 database. If customer not found for
	 * given id, returns null.
	 * 
	 * @param id
	 *            the customer id
	 * @return id of deleted customer object
	 */
	public String delete(String msisdn) {

		for (int i = 0; i < StoreCustomerData.storeUserData.size(); i++) {
			if(StoreCustomerData.storeUserData.get(i).msisdn.equals(msisdn)) {
				StoreCustomerData.storeUserData.remove(i);
			}
			return StoreCustomerData.storeUserData.get(i).msisdn;
		}


		return null;
	}
	
	public UserModel update(String msisdn, ServiceType st, UserModel customer) {

		for (int i = 0; i < SCD.storeUserData.size(); i++) {
			if(SCD.storeUserData.get(i).msisdn.equals(msisdn) && SCD.storeUserData.get(i).service_type.equals(ServiceType.MOBILE_POSTPAID)) {
				SCD.storeUserData.get(i).service_type = ServiceType.MOBILE_PREPAID;
			} else {
				SCD.storeUserData.get(i).service_type = ServiceType.MOBILE_POSTPAID;
			}
		}
		
//		for (Customer c : customers) {
//			if (c.getId().equals(id)) {
//				customer.setId(c.getId());
//				customers.remove(c);
//				customers.add(customer);
//				return customer;
//			}
//		}

		return null;
	}
	
	public UserModel get(String msisdn) {
			
//		for (UserModel c.msisdn : us) {
//			if (c.getId().equals(id)) {
//				return c;
//			}
//		}
		return null;
		
	}

	
	

}