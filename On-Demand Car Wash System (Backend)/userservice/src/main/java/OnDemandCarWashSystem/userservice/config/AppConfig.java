package OnDemandCarWashSystem.userservice.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.FormContentFilter;

@Configuration
public class AppConfig {

    @Bean
    public FormContentFilter formContentFilter() {
        return new FormContentFilter();
    }

    // Other bean definitions can be added here

}