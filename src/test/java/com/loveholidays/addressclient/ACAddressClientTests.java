package com.loveholidays.addressclient;

import com.loveholidays.addressclient.client.ACAddressClient;
import com.loveholidays.addressclient.client.RESTClient;
import com.loveholidays.addressclient.model.Address;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import static org.mockito.Mockito.*;


@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class ACAddressClientTests {

    private static final String INVALID_API_KEY = "PCW27-J25-QBLMF-69DMJ";
    private static final String VALID_API_KEY = "PCW27-J25NM-QBLMF-69DMJ";
    private static final String CORRECT_URL = "http://ws.postcoder.com/pcw/" + VALID_API_KEY + "/address/UK/";
    private static final String INCORRECT_URL = "http://ws.postcoder.com/pcw/" + INVALID_API_KEY + "/address/UK/";

    private static final String TEST_POSTAL_CODE = "W6 0LG";
    private static final String SUCCESS_STATUS_CODE = "202";
    private static final String EMPTY_STRING = "";

    @Mock
    private RestOperations restOperations;

    @InjectMocks
    private RESTClient client = new ACAddressClient(CORRECT_URL + TEST_POSTAL_CODE);

    @Test
    public void testGetAddress() {

        ResponseEntity<Address[]> myResponseEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);

        Mockito.when(restOperations.getForEntity(eq(CORRECT_URL + TEST_POSTAL_CODE), Matchers.eq(Address[].class), eq(TEST_POSTAL_CODE))).thenReturn(myResponseEntity);
        ResponseEntity<Address[]> responseEntity = client.getAddress(TEST_POSTAL_CODE);
        verify(restOperations, times(1)).getForEntity(eq(CORRECT_URL + TEST_POSTAL_CODE), Matchers.eq(Address[].class), eq(TEST_POSTAL_CODE));

        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(SUCCESS_STATUS_CODE, responseEntity.getStatusCode().toString());
    }

    @Test(expected = HttpClientErrorException.class)
    public void testIncorrectURLRequest() {

        client.setUrl(INCORRECT_URL + TEST_POSTAL_CODE);

        Mockito.when(restOperations.getForEntity(eq(INCORRECT_URL + TEST_POSTAL_CODE), Matchers.eq(Address[].class), eq(TEST_POSTAL_CODE))).thenThrow(new HttpClientErrorException(HttpStatus.FORBIDDEN));
        client.getAddress(TEST_POSTAL_CODE);
    }

    @Test //in case there are no finds, API returns [] no matter what the search term is, even null or
    public void testGetAddressEmpty() {

        client.setUrl(CORRECT_URL + EMPTY_STRING);

        ResponseEntity<Address[]> myResponseEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);

        Mockito.when(restOperations.getForEntity(eq(CORRECT_URL + EMPTY_STRING), Matchers.eq(Address[].class), eq(EMPTY_STRING))).thenReturn(myResponseEntity);
        ResponseEntity<Address[]> responseEntity = client.getAddress(EMPTY_STRING);
        verify(restOperations, times(1)).getForEntity(eq(CORRECT_URL + EMPTY_STRING), Matchers.eq(Address[].class), eq(EMPTY_STRING));

        Assert.assertNotNull(responseEntity);
    }


}
