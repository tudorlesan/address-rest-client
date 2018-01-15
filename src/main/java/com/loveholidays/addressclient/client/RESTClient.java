package com.loveholidays.addressclient.client;

import com.loveholidays.addressclient.model.Address;
import org.springframework.http.ResponseEntity;

public interface RESTClient {
    ResponseEntity<Address[]> getAddress(final String searchTerm);
    void setUrl(String url);
}
