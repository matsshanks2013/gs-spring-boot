package hello;

import com.dev.springmvc.model.Shop;
import com.google.gson.JsonArray;
//import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleMapHelper {


	public static void populateShopLatLong(Shop shop) {
		// TODO Auto-generated method stub
		RestTemplate template = new RestTemplate();
		 try {
			String address = shop.getAddress();
			System.out.println("address" + address);
			URL base =new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=AIzaSyAiJJKwGCzT4d19bffOFn5ZRNv_xXO6NfI");
			System.out.println("base" + base);
			ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
			String shopJSON = response.getBody();
			System.out.println("Shop Json" + shopJSON);
			parse(shopJSON, shop);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void parse(String shopJSON, Shop shop) {
		// TODO Auto-generated method stub
		    try {
				JsonElement jelement = new JsonParser().parse(shopJSON);
				JsonObject  json = jelement.getAsJsonObject();
				
				JsonArray resultArray = json.getAsJsonArray("results");
				JsonObject jresult = resultArray.get(0).getAsJsonObject();
				JsonObject jgeometry = jresult.getAsJsonObject("geometry");
				JsonObject jlocation = jgeometry.getAsJsonObject("location");
				String lat = jlocation.get("lat").toString();
				System.out.println("lat" + lat);
				shop.setLat(lat);
				String longitude = jlocation.get("lng").toString();
				System.out.println("longitude" + longitude);
				shop.setLongitude(longitude);
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    catch (IndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
		
	}

}
