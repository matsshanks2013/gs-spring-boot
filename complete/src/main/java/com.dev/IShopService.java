/**
 * 
 */
package com.dev;

/**
 * @author IBM
 *
 */


import java.util.List;

import com.dev.springmvc.model.Shop;



public interface IShopService {
	
	Shop findById(long id);
	
	Shop findByName(String name);
	
	void saveShop(Shop shop);
	
	void updateShop(Shop shop);
	


	List<Shop> findAllShops(); 
	
	
	
	public boolean isShopExist(Shop shop);

	void deleteShopById(long id);
	
}

