package vodaone.test.george;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

import vodaone.test.george.Controllers.Rest;
import vodaone.test.george.Controllers.Web;

/*
 * Web Controller 
 * **************
 * This Web Controller uses constructors to verify the given information
 * These constructors will then store the information created in the User Model H2 Database
 * 
 * The Web Controller will then contain actions to perform on the database
 * & will use a Repository to perform custom actions on the database
 */

/*
 * REST Controller 
 * **************
 * The REST Controller will communicate actions when being called by JSON 
 * to the same H2 Database created in the User Model 
 * 
 * The REST Controller will receive,create,update&delete from H2 Database 
 * & will use a Repository to perform custom actions on the database
 * 
 * As the REST Controller has some issues creating a new User,
 * You must add the Users from the CLI Menu
 * Then visit http://localhost:8080/saveMobileNumber to insert in database
 */

@SpringBootApplication

//listens for REST controller
@ComponentScan(basePackageClasses = Rest.class)

// listens for Web controller
@ComponentScan(basePackageClasses = Web.class)

public class VodafoneTestApplication {
	static boolean firstRun = false;

  
	public static void main(String[] args) throws IOException {
		SpringApplication.run(VodafoneTestApplication.class, args);
		
		//loads up the CLI Menu
		do {
			Menu.main(null);
			firstRun = true;
		} while(firstRun == false);
	}
	
		
			
}
