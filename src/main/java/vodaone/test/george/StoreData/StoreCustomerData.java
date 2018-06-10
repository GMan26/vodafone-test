package vodaone.test.george.StoreData;

import java.util.ArrayList;

public class StoreCustomerData {

	/*
	 * Presentation Layer (plug-in)
	 * This is where the user data will be stored before beginning added to the database
	 * The information will be stored using constructor
	 * This uses, String msisdn (E164 Format), int Owner ID, int User ID, Service-Type (Emun), long service start data (Unix Epoch Millis)
	 */


	String msisdn = null;
	int customer_id_owner = 0;
	int customer_id_user = 0;
	public static ServiceType service_type;
	String service_start_date = null;
	
    public static ArrayList<StoreCustomerData1> storeUserData = new ArrayList<StoreCustomerData1>();
    
    static public class StoreCustomerData1 {

    	public StoreCustomerData1(String msisdn, int customer_id_owner, int customer_id_user, ServiceType service_type, long service_start_date) {
            this.msisdn = msisdn;
            this.customer_id_owner = customer_id_owner;
            this.customer_id_user = customer_id_user;
            this.service_type = service_type;
            this.service_start_date = service_start_date;

        }

        public String msisdn;
        public int customer_id_owner;
        public int customer_id_user;
        public ServiceType service_type;
        public long service_start_date;
    }
    
    
}
