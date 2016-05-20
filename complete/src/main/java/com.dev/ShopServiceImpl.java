package com.dev;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.dev.springmvc.model.Shop;
import com.dev.springmvc.model.ShopAddressBean;

@Service("shopService")
// @Transactional
public class ShopServiceImpl implements IShopService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Shop> shops;

	static {
		shops = populateDummyShops();
	}

	public List<Shop> findAllShops() {
		return shops;
	}
    /**
     * Find by unique Id
     */
	public Shop findById(long id) {
		for (Shop shop : shops) {
			if (shop.getId() == id) {
				return shop;
			}
		}
		return null;
	}

	/**
	 * Find by Name
	 */
	
	public Shop findByName(String name) {
		for (Shop shop : shops) {
			if (shop.getShopName().equalsIgnoreCase(name)) {
				return shop;
			}
		}
		return null;
	}

	/**
	 * Method to save shop details
	 */
	public void saveShop(Shop shop) {
		shop.setId(counter.incrementAndGet());
		shops.add(shop);
	}

	/**
	 * Method to update Shop Details
	 */
	public void updateShop(Shop shop) {
		int index = shops.indexOf(shop);
		shops.set(index, shop);
	}

	public boolean isShopExist(Shop shop) {
		return findByName(shop.getShopName()) != null;
	}

	/**
	 * Populating dummy shops in a static memory
	 * 
	 * @return
	 */
	private static List<Shop> populateDummyShops() {
		List<Shop> shops = new ArrayList<Shop>();

		shops.add(new Shop(counter.incrementAndGet(),
				"Ishanya Mall+Opposite Golf Course, Airport Rd+Yerawada+Pune+Maharashtra+", "", "411006+", "INDIA"));
		shops.add(new Shop(counter.incrementAndGet(), "Sears+Valley View Center+", "13131+", "75240+", "USA"));
		shops.add(new Shop(counter.incrementAndGet(),
				"Reliance+mart+Westend+Center+Dp+Road+Opposite+Macdonald+Aundh+Harmony+Society+",
				"Ward+No.+8+Wireless+Colony+Aundh+Pune+Maharashtra+", "411007+", "India"));
		shops.add(new Shop(counter.incrementAndGet(), "Metro+", "1030+Adelaide+St=N+", "N5Y2M9+", "Canada"));
		return shops;
	}

	@Override
	public void deleteShopById(long id) {
		// TODO Auto-generated method stub
		for (Iterator<Shop> iterator = shops.iterator(); iterator.hasNext();) {
			Shop shop = iterator.next();
			if (shop.getId() == id) {
				iterator.remove();
			}
		}

	}

}
