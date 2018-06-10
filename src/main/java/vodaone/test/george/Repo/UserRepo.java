package vodaone.test.george.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import vodaone.test.george.Model.UserModel;
import vodaone.test.george.StoreData.ServiceType;

/*
 * This Repository class is able to handle custom actions to the database
 */

public interface UserRepo extends CrudRepository<UserModel, String>{
	
    @Transactional
    String removeByMsisdn(String msisdn);
    
    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.service_type=:service_type WHERE u.msisdn=:msisdn")
    void markEntryAsRead(@Param("service_type") ServiceType service_type, @Param("msisdn") String msisdn);
    
    
//    @Transactional
//    @Modifying //clearAutomatically is true by default, so there's no need to specify it
//    @Query("UPDATE UserModel u SET u.msisdn=:msisdn WHERE u.service_type=:service_type")
//    public void markEntryAsRead(@Param("msisdn") String msisdn, @Param("service_type") ServiceType ServiceType);

    List<UserModel> findByMsisdn(String msisdn);



} 
