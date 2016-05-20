package com.dev;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.dev.springmvc.model.Shop;

@RestController
public class ShopRestController {
	private static final Logger logger = LoggerFactory.getLogger(ShopRestController.class);

	@Autowired
	IShopService shopService;  //Service which will do all data retrieval/manipulation work

	
	//-------------------Retrieve All Shops--------------------------------------------------------
	
	@RequestMapping(value = "/shops/", method = RequestMethod.GET)
	public ResponseEntity<List<Shop>> listAllUsers() {
		
		List<Shop> shops = shopService.findAllShops();
		if(shops.isEmpty()){
			return new ResponseEntity<List<Shop>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		getLatandLong(shops);
		return new ResponseEntity<List<Shop>>(shops, HttpStatus.OK);
	}
	
	


	private void getLatandLong(List<Shop> shops) {
		// TODO Auto-generated method stub
		
		
	}




	//-------------------Retrieve Single Shop--------------------------------------------------------
	
	@RequestMapping(value = "/shops/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shop> getShop(@PathVariable("id") long id) {
		logger.debug("Fetching Shop  with id " + id);
		Shop shop = shopService.findById(id);
		if (shop == null) {
			logger.debug("Shop with id " + id + " not found");
			return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Shop>(shop, HttpStatus.OK);
	}
	
	

	
	
	//-------------------Create a Shop--------------------------------------------------------
	
	@RequestMapping(value = "/shops/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createShop(@RequestBody Shop shop, 	UriComponentsBuilder ucBuilder) {
		logger.debug("Creating Shop " + shop.getShopName());

		if (shopService.isShopExist(shop)) {
			logger.debug("A Shop with name " + shop.getShopName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		shopService.saveShop(shop);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/shops/{id}").buildAndExpand(shop.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
	//------------------- Update a Shop --------------------------------------------------------
	
	@RequestMapping(value = "/shops/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Shop> updateShop(@PathVariable("id") long id, @RequestBody Shop shop) {
		System.out.println("Updating Shop " + id);
		
		Shop currentShop = shopService.findById(id);
		
		if (currentShop==null) {
			logger.debug("Shop with id " + id + " not found");
			return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
		}

		currentShop.setShopName(shop.getShopName());
		currentShop.setPostcode(shop.getPostcode());
		currentShop.setShopNumber(shop.getShopNumber());
		currentShop.setCountry(shop.getCountry());
		
		
		shopService.updateShop(currentShop);
		return new ResponseEntity<Shop>(currentShop, HttpStatus.OK);
	}
	
	//------------------- Delete a Shop --------------------------------------------------------
	
		@RequestMapping(value = "/shops/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Shop> deleteShop(@PathVariable("id") long id) {
			System.out.println("Fetching & Deleting Shop with id " + id);

			Shop shop = shopService.findById(id);
			if (shop == null) {
				logger.debug("Unable to delete. Shop with id " + id + " not found");
				return new ResponseEntity<Shop>(HttpStatus.NOT_FOUND);
			}

			shopService.deleteShopById(id);
			return new ResponseEntity<Shop>(HttpStatus.NO_CONTENT);
		}
	
}
