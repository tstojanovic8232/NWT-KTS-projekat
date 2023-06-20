package tim.projekat.servisi;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class FacebookServis {
    private final RestTemplate restTemplate;
    @Autowired
    public FacebookServis(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    public boolean validateClientId(String credential, String clientId) {
        // Example validation logic using RestTemplate
        String validationUrl = "https://graph.facebook.com/debug_token?input_token=" + credential + "&access_token="+clientId;



        // Set up request headers and body
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("credential", credential);
        requestBody.add("clientId", clientId);

        // Send the POST request and check the response
        ResponseEntity<String> response = restTemplate.postForEntity(validationUrl, new HttpEntity<>(requestBody, headers), String.class);

        // Check the response status
        if (response.getStatusCode() == HttpStatus.OK) {
            // Validation successful
            return true;
        } else {
            // Validation failed
            return false;
        }
    }

    public String getUserEmail(String credential) {
        // Example logic to extract user email from the credential
        // Assuming the credential is in JSON format with an "email" field
        // You may need to adjust this logic based on the actual structure of the credential
        String email="";
        String apiurl="https://graph.facebook.com/me?fields=f+email&access_token=" + credential;
        String response=restTemplate.getForObject(apiurl,String.class);
        System.out.println(response);


        return email;
    }
}
