package com.loveholidays.addressclient.client;


import com.loveholidays.addressclient.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestOperations;


@Component(value = "ac")
public class ACAddressClient implements RESTClient {

    private String url;

    private RestOperations restOperations;

    @Autowired
    public ACAddressClient(@Value("${postalcode.api.url}") final String url) {
        this.url = url;
    }

    @Autowired
    public void setRestOperations(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    public ResponseEntity<Address[]> getAddress(final String searchTerm) throws HttpStatusCodeException {
        return restOperations.getForEntity(url, Address[].class, searchTerm);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
