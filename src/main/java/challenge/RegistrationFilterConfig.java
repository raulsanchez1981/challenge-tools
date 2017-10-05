package challenge;

import challenge.filters.NoContentFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Created by rsanchpa on 01/10/2017.
 */
//@Configuration
public class RegistrationFilterConfig {

    @Bean
    public FilterRegistrationBean noContentFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new NoContentFilter());
        registration.addUrlPatterns("/challenge/*");
        registration.setName("challengeNoContentFilter");
        //registration.setOrder(1);
        return registration;
    }

}
