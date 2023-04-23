package by.ciao.utils;

import by.ciao.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestService {

    private final RestTemplate restTemplate;
    @Value("${userdata_endpoint}")
    private String userdataEndpoint;

    public List<User> getUsers() {
        return restTemplate.exchange(
                userdataEndpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}
        ).getBody();
    }
}
