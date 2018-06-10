package vodaone.test.george.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import vodaone.test.george.StoreData.ServiceType;

/*
 * This Model class creates a database using given parameters
 * 
 */
@Entity
@Table(name = "UserModel")

	public class UserModel implements Serializable{
	

	//Auto-Gen serialVerionUID
	 
	private static final long serialVersionUID = -6615736827891353044L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private int id;

	 @Column(name = "msisdn")
	 private String msisdn;
	 
	 @Column(name = "customer_id_owner")
	 private int customer_id_owner;
	    
	 @Column(name = "customer_id_user")
	 private int customer_id_user;
	 
	 @Column(name = "service_type")
	 private ServiceType service_type;
	 
	 @Column(name = "service_start_date")
	 private long service_start_date;
	 
	 
	 
     public UserModel() {
     }
	 
     public UserModel(String msisdn, int customer_id_owner, int customer_id_user, 
    		 ServiceType service_type, long service_start_date) {
         this.msisdn = msisdn;
         this.customer_id_owner = customer_id_owner;
         this.customer_id_user = customer_id_user;
         this.service_type = service_type;
         this.service_start_date = service_start_date;
     }
	 
     @Override
     public String toString() {
         return "clients{" + "id = " + id + ", Msisdn =" + msisdn + ", Customer_id_owner =" + customer_id_owner + 
         		", Customer_id_user = " + customer_id_user + ", service_type = " + service_type + ", service_start_date = " + service_start_date;
     }
     
	}
