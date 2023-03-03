package tim.projekat.servisi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tim.projekat.model.Address;

@Service
public class GeocodingService {

    private final String NOMINATIM_API_ENDPOINT = "https://nominatim.openstreetmap.org/reverse";

    private RestTemplate restTemplate;

    public GeocodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Address geocode(double lat, double lon) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(NOMINATIM_API_ENDPOINT)
                .queryParam("format", "jsonv2")
                .queryParam("lat", lat)
                .queryParam("lon", lon);

        Address address = restTemplate.getForObject(builder.toUriString(), Address.class);
        return address;
    }
}
