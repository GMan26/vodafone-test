
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import REST.CustomerRestController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = CustomerRestController.class)
public class AppConfig {

}