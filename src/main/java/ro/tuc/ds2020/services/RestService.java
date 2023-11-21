package ro.tuc.ds2020.services;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;

@Service
public class RestService {
    private final RestTemplate restTemplate;

    @Autowired
    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Your method that performs the HTTP request
    public void callOtherMicroserviceEndpoint(int personId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create a JSON object with the required data
        // Create a JSON object with the required data
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", personId);

        HttpEntity<JSONObject> request = new HttpEntity<>(jsonObject, headers);

        restTemplate.exchange("http://localhost:8000/person", HttpMethod.POST, request, Void.class);
    }

//    @Autowired
//    public void callOtherMicroserviceEndpointDelete(int personId) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//
//        restTemplate.exchange("http://localhost:8000/person/{personId}", HttpMethod.DELETE,  Void.class);
//    }

}

