package com.loveholidays.addressclient;


import com.loveholidays.addressclient.client.RESTClient;
import com.loveholidays.addressclient.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Arrays;
import java.util.Scanner;


@SpringBootApplication
@Profile("!test")
public class App implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    public App(@Qualifier("ac") RESTClient addressClient) {
        this.addressClient = addressClient;
    }

    public static void main(String[] args) {

		SpringApplication.run(App.class, args);
	}

	private final RESTClient addressClient;

    @Override
    public void run(String... strings) throws Exception {

        logger.info("Postal code:");
        Scanner scan = new Scanner(System.in);
        String searchTerm = scan.nextLine();
        logger.info("Name(optional):");
        String name = scan.nextLine();
        try {
            ResponseEntity<Address[]> responseEntity = addressClient.getAddress(searchTerm);
            logger.info("Addresses found:");
            Arrays.stream(responseEntity.getBody()).filter(address -> address.getOrganisation().toLowerCase().contains(name.toLowerCase())).forEach(address -> logger.info(address.toString()));
        }
        catch(HttpStatusCodeException ex){
            logger.error(ex.getMessage());
        }

    }
}
