package tim.projekat.servisi;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tim.projekat.model.APIResponse;

public class GeocodingService {

    private final String NOMINATIM_API_ENDPOINT = "https://nominatim.openstreetmap.org/reverse";

    private RestTemplate restTemplate;

    public GeocodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public APIResponse geocode(double lat, double lon) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(NOMINATIM_API_ENDPOINT)
                .queryParam("format", "jsonv2")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("accept-language", "sr");


        return restTemplate.getForObject(builder.toUriString(), APIResponse.class);
    }
}
