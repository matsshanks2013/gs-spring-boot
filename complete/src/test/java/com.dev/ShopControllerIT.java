package com.dev;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.dev.Application;
import com.dev.springmvc.model.Shop;

import junit.framework.Assert;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class ShopControllerIT {

    @Value("${local.server.port}")
    private int port;

	private URL base;
	
	
	private RestTemplate template;
	private RestTemplate ctemplate;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/shops/1");
		
		template = new TestRestTemplate();
		ctemplate = new RestTemplate();
	}

	@Test
	/**
	 * Testing to fetch the shop details. Finding the first record. The same test case can also be written to test individual parameters
	 * @throws Exception
	 */
	public void getShop() throws Exception {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.getBody(), equalTo("{\"id\":1,\"shopName\":\"Ishanya Mall+Opposite Golf Course, Airport Rd+Yerawada+Pune+Maharashtra+\",\"shopNumber\":\"\",\"postcode\":\"411006+\",\"country\":\"INDIA\",\"lat\":\"18.5598098\",\"longitude\":\"73.89160869999999\",\"address\":\"Ishanya Mall+Opposite Golf Course, Airport Rd+Yerawada+Pune+Maharashtra+411006+INDIA\"}"));
	}
	
	//@Test
	/**
	 * Testing to fetch the shop details. Finding the first record. The same test case can also be written to test individual parameters
	 * @throws Exception
	 */
//	public void createShop() throws Exception {
//		HttpClient httpClient = HttpClientBuilder.create().build();
//		HttpPost request = new HttpPost("http://localhost:" + port + "/shops/");
//        StringEntity params =new StringEntity("{\"id\":7,\"shopName\":\"Monte+Carlo+500+Patrya+maruti+Chowk+Bhatancha Bol+Narayan Peth+Bhatancha Bol+Narayan+Peth+Pune+Maharashtra+\",\"shopNumber\":\"500\",\"postcode\":\"411030+\",\"country\":\"INDIA\"}");
//        	",\"lat\":\"18.5172679802915\",\"longitude\":\"73.8486649802915\",\"address\":\"Monte+Carlo+500+Patrya+maruti+Chowk+Bhatancha Bol+Narayan Peth+Bhatancha Bol+Narayan+Peth+Pune+Maharashtra,+411030+India\"}");
//        request.addHeader("content-type", "application/json");
//        request.setEntity(params);
//        HttpResponse response = httpClient.execute(request);
//        int statusCode = response.getStatusLine().getStatusCode();
//        String statusStr = new Integer(statusCode).toString();
//        System.out.println("statusCode" + statusCode);
//        assertEquals(statusStr,"201");
//        
//        
//		
//	}
	
	@Test
	/**
	 * Testing to create the shop details. POST operation test.
	 * @throws Exception
	 */
	public void createShop() throws Exception {
	    String uri = new String("http://localhost:8080" + "/shops/");
        Shop sh = new Shop();
    
        sh.setShopName("Tesco+Express+");
        sh.setShopNumber("92-98+George+St+London");
        sh.setPostcode("W1U8NN");
        sh.setCountry("United+Kingdom");
    
        ResponseEntity<String> response  = template.postForEntity(uri, sh, String.class);
        assertEquals(response.getStatusCode(),HttpStatus.CREATED);
        
        
        
	}
	
}
