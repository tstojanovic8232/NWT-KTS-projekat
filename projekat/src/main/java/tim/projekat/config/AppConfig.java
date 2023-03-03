package tim.projekat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tim.projekat.servisi.GeocodingService;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GeocodingService geocodingService(RestTemplate restTemplate) {
        return new GeocodingService(restTemplate);
    }
}
