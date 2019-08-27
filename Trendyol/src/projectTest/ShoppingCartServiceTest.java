package projectTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import cart.ShoppingCartModel;
import cart.ShoppingCartService;
import delivery.DeliveryCostCalculator;
import delivery.DeliveryCostModel;
import discount.Campaign;
import discount.Coupons;
import enums.DiscountType;
import item.Category;
import item.Product;

class ShoppingCartServiceTest {

	// Hi� kampanya yokken
	@Test
	void test() throws Exception {

		ShoppingCartService cartService = new ShoppingCartService();

		Category category1 = Category.getInstance("Masa �st� Bilgisayarlar� Cart");
		Product product1 = Product.getInstance("Toshiba 520 Cart", 3250.0, category1);
		Product product2 = Product.getInstance("Toshiba 720 Cart", 3750.0, category1);

		Category category2 = Category.getInstance("Masa �st� Bilgisayarlar�2 Cart");
		Product product3 = Product.getInstance("Toshiba 5201 Cart", 3250.0, category1);
		Product product4 = Product.getInstance("Toshiba 7201 Cart", 3750.0, category1);

		ShoppingCartModel cart = new ShoppingCartModel();
		cart.addItem(product1, 3);
		cart.addItem(product2, 1);

		List<Campaign> campaignList = new ArrayList<>();

		cart = cartService.applyCampaignDiscount(campaignList, cart);

		assertEquals(Double.valueOf(0.0), cart.getCampaignDiscount());
	}

	// 1 kampanya var ancak minItem'dan az say�da �r�n sepete eklenmi�
	@Test
	void test2() throws Exception {

		ShoppingCartService cartService = new ShoppingCartService();

		Category category1 = Category.getInstance("Masa �st� Bilgisayarlar�3 Cart");
		Product product1 = Product.getInstance("Toshiba 5202 Cart", 3250.0, category1);
		Product product2 = Product.getInstance("Toshiba 7202 Cart", 3750.0, category1);

		Category category2 = Category.getInstance("Masa �st� Bilgisayarlar�4 Cart");
		Product product3 = Product.getInstance("Toshiba 5203 Cart", 3250.0, category1);
		Product product4 = Product.getInstance("Toshiba 7203 Cart", 3750.0, category1);

		ShoppingCartModel cart = new ShoppingCartModel();
		cart.addItem(product1, 3);
		cart.addItem(product2, 1);

		Double discountRule = 10.0;
		Integer minItem = 5;
		Campaign campaign = new Campaign(category1, discountRule, minItem, DiscountType.RATE);
		List<Campaign> campaignList = new ArrayList<>();
		campaignList.add(campaign);

		cart = cartService.applyCampaignDiscount(campaignList, cart);

		assertEquals(Double.valueOf(0.0), cart.getCampaignDiscount());
	}

	// Sepette 2 farkl� kategoriden �r�n var ve sadece 1 tanesinde kampanya var
	@Test
	void test3() throws Exception {

		ShoppingCartService cartService = new ShoppingCartService();

		Category category1 = Category.getInstance("Masa �st� Bilgisayarlar�5 Cart");
		Product product1 = Product.getInstance("Toshiba 5204 Cart", 3250.0, category1);
		Product product2 = Product.getInstance("Toshiba 7204 Cart", 3750.0, category1);

		Category category2 = Category.getInstance("Masa �st� Bilgisayarlar�6 Cart");
		Product product3 = Product.getInstance("Toshiba 5205 Cart", 3250.0, category2);
		Product product4 = Product.getInstance("Toshiba 7205 Cart", 3750.0, category2);

		ShoppingCartModel cart = new ShoppingCartModel();
		cart.addItem(product1, 3);
		cart.addItem(product2, 3);
		cart.addItem(product3, 1);
		cart.addItem(product4, 1);

		Double discountRule = 10.0;
		Integer minItem = 5;
		Campaign campaign = new Campaign(category1, discountRule, minItem, DiscountType.RATE);
		List<Campaign> campaignList = new ArrayList<>();
		campaignList.add(campaign);

		cart = cartService.applyCampaignDiscount(campaignList, cart);

		assertEquals(Double.valueOf(2100.0), cart.getCampaignDiscount());
	}

	// Sepette 2 farkl� kategoriden �r�n var ve parent child ili�kisi var
	@Test
	void test4() throws Exception {

		ShoppingCartService cartService = new ShoppingCartService();

		Category category1 = Category.getInstance("Masa �st� Bilgisayarlar�8 Cart");
		Product product1 = Product.getInstance("Toshiba 5207 Cart", 3250.0, category1);
		Product product2 = Product.getInstance("Toshiba 7207 Cart", 3750.0, category1);

		Category category2 = Category.getInstance("Masa �st� Bilgisayarlar�7 Cart");
		Product product3 = Product.getInstance("Toshiba 5206 Cart", 3250.0, category2);
		Product product4 = Product.getInstance("Toshiba 7206 Cart", 3750.0, category2);

		category1.getChildCategories().add(category2);
		category2.setParentCategory(category1);

		ShoppingCartModel cart = new ShoppingCartModel();
		cart.addItem(product1, 3);
		cart.addItem(product2, 3);
		cart.addItem(product3, 1);
		cart.addItem(product4, 1);

		Double discountRule = 50.0;
		Integer minItem = 5;
		Campaign campaign = new Campaign(category1, discountRule, minItem, DiscountType.AMOUNT);
		List<Campaign> campaignList = new ArrayList<>();
		campaignList.add(campaign);

		cart = cartService.applyCampaignDiscount(campaignList, cart);

		assertEquals(Double.valueOf(400.0), cart.getCampaignDiscount());
	}

	// Kategori1 kendi ba��na minItem'� ge�emiyor. �ocuk kategorilerle birlikte
	// ge�iyor. Kendisi ile birlikte �ocuk kategorilerdeki �r�n say�s� minItem'dan
	// b�y�k oldu�u i�in kampanyay� uygulad�m
	@Test
	void test5() throws Exception {

		ShoppingCartService cartService = new ShoppingCartService();

		Category category1 = Category.getInstance("Masa �st� Bilgisayarlar�9 Cart");
		Product product1 = Product.getInstance("Toshiba 5208 Cart", 3250.0, category1);
		Product product2 = Product.getInstance("Toshiba 7208 Cart", 3750.0, category1);

		Category category2 = Category.getInstance("Masa �st� Bilgisayarlar�10 Cart");
		Product product3 = Product.getInstance("Toshiba 5209 Cart", 3250.0, category2);
		Product product4 = Product.getInstance("Toshiba 7209 Cart", 3750.0, category2);

		category1.getChildCategories().add(category2);
		category2.setParentCategory(category1);

		ShoppingCartModel cart = new ShoppingCartModel();
		cart.addItem(product1, 2);
		cart.addItem(product2, 1);
		cart.addItem(product3, 1);
		cart.addItem(product4, 1);

		Double discountRule = 50.0;
		Integer minItem = 5;
		Campaign campaign = new Campaign(category1, discountRule, minItem, DiscountType.AMOUNT);

		List<Campaign> campaignList = new ArrayList<>();
		campaignList.add(campaign);

		cart = cartService.applyCampaignDiscount(campaignList, cart);

		assertEquals(Double.valueOf(250.0), cart.getCampaignDiscount());
	}

	// 2 Farkl� kampanya ile test yap�ld�
	@Test
	void test6() throws Exception {

		ShoppingCartService cartService = new ShoppingCartService();

		Category category1 = Category.getInstance("Masa �st� Bilgisayarlar�11 Cart");
		Product product1 = Product.getInstance("Toshiba 52010 Cart", 325.0, category1);
		Product product2 = Product.getInstance("Toshiba 72010 Cart", 375.0, category1);

		Category category2 = Category.getInstance("Masa �st� Bilgisayarlar�12 Cart");
		Product product3 = Product.getInstance("Toshiba 52011 Cart", 325.0, category2);
		Product product4 = Product.getInstance("Toshiba 72011 Cart", 375.0, category2);

		category1.getChildCategories().add(category2);
		category2.setParentCategory(category1);

		ShoppingCartModel cart = new ShoppingCartModel();
		cart.addItem(product1, 3);
		cart.addItem(product2, 3);
		cart.addItem(product3, 1);
		cart.addItem(product4, 1);

		Double discountRule = 25.0;
		Integer minItem = 5;
		Campaign campaign = new Campaign(category1, discountRule, minItem, DiscountType.AMOUNT);

		Double discountRule2 = 10.0;
		Integer minItem2 = 5;
		Campaign campaign2 = new Campaign(category1, discountRule2, minItem2, DiscountType.RATE);

		List<Campaign> campaignList = new ArrayList<>();
		campaignList.add(campaign);
		campaignList.add(campaign2);

		cart = cartService.applyCampaignDiscount(campaignList, cart);

		assertEquals(Double.valueOf(280.0), cart.getCampaignDiscount());
	}
	
	// 2 Farkl� kampanya ile test yap�ld�. En fazla indirimi yapan kampanya minItem ko�ulunu sa�lamad��� i�in di�er kampanya dikkate al�nd�.
	@Test
	void test7() throws Exception {

		ShoppingCartService cartService = new ShoppingCartService();

		Category category1 = Category.getInstance("Masa �st� Bilgisayarlar�13 Cart");
		Product product1 = Product.getInstance("Toshiba 52012 Cart", 325.0, category1);
		Product product2 = Product.getInstance("Toshiba 72012 Cart", 375.0, category1);

		Category category2 = Category.getInstance("Masa �st� Bilgisayarlar�14 Cart");
		Product product3 = Product.getInstance("Toshiba 52013 Cart", 325.0, category2);
		Product product4 = Product.getInstance("Toshiba 72013 Cart", 375.0, category2);

		category1.getChildCategories().add(category2);
		category2.setParentCategory(category1);

		ShoppingCartModel cart = new ShoppingCartModel();
		cart.addItem(product1, 3);
		cart.addItem(product2, 3);
		cart.addItem(product3, 1);
		cart.addItem(product4, 1);

		Double discountRule = 40.0;
		Integer minItem = 10;
		Campaign campaign = new Campaign(category1, discountRule, minItem, DiscountType.AMOUNT);

		Double discountRule2 = 10.0;
		Integer minItem2 = 5;
		Campaign campaign2 = new Campaign(category1, discountRule2, minItem2, DiscountType.RATE);

		List<Campaign> campaignList = new ArrayList<>();
		campaignList.add(campaign);
		campaignList.add(campaign2);

		cart = cartService.applyCampaignDiscount(campaignList, cart);

		assertEquals(Double.valueOf(280.0), cart.getCampaignDiscount());
	}
	
	//Kupon eklendi (DiscountType.RATE)
	@Test
	void test8() throws Exception {

		ShoppingCartService cartService = new ShoppingCartService();

		Category category1 = Category.getInstance("Masa �st� Bilgisayarlar�15 Cart");
		Product product1 = Product.getInstance("Toshiba 52014 Cart", 150.0, category1);
		Product product2 = Product.getInstance("Toshiba 72014 Cart", 150.0, category1);

		Category category2 = Category.getInstance("Masa �st� Bilgisayarlar�16 Cart");
		Product product3 = Product.getInstance("Toshiba 52015 Cart", 150.0, category2);
		Product product4 = Product.getInstance("Toshiba 72015 Cart", 150.0, category2);

		ShoppingCartModel cart = new ShoppingCartModel();
		cart.addItem(product1, 1);
		cart.addItem(product2, 1);
		cart.addItem(product3, 1);
		cart.addItem(product4, 1);

		Double minPurchase = 150.0;
		Double discountRule = 20.0;
		
		Coupons coupon = new Coupons(minPurchase, discountRule, DiscountType.RATE);

		cart = cartService.applyCouponDiscount(coupon, cart);

		assertEquals(Double.valueOf(120.0), cart.getCouponDiscount());
	}
	
	//Kupon eklendi (DiscountType.AMOUNT)
		@Test
		void test9() throws Exception {

			ShoppingCartService cartService = new ShoppingCartService();

			Category category1 = Category.getInstance("Masa �st� Bilgisayarlar�17 Cart");
			Product product1 = Product.getInstance("Toshiba 52016 Cart", 150.0, category1);
			Product product2 = Product.getInstance("Toshiba 72016 Cart", 150.0, category1);

			Category category2 = Category.getInstance("Masa �st� Bilgisayarlar�18 Cart");
			Product product3 = Product.getInstance("Toshiba 52017 Cart", 150.0, category2);
			Product product4 = Product.getInstance("Toshiba 72017 Cart", 150.0, category2);

			ShoppingCartModel cart = new ShoppingCartModel();
			cart.addItem(product1, 1);
			cart.addItem(product2, 1);
			cart.addItem(product3, 1);
			cart.addItem(product4, 1);

			Double minPurchase = 150.0;
			Double discountRule = 20.0;
			
			Coupons coupon = new Coupons(minPurchase, discountRule, DiscountType.AMOUNT);

			cart = cartService.applyCouponDiscount(coupon, cart);

			assertEquals(Double.valueOf(20.0), cart.getCouponDiscount());
		}
		
		//Kupon eklenemedi. minPurchase sepet tutar�ndan b�y�k
		@Test
		void test10() throws Exception {

			ShoppingCartService cartService = new ShoppingCartService();

			Category category1 = Category.getInstance("Masa �st� Bilgisayarlar�19 Cart");
			Product product1 = Product.getInstance("Toshiba 52018 Cart", 150.0, category1);
			Product product2 = Product.getInstance("Toshiba 72018 Cart", 150.0, category1);

			Category category2 = Category.getInstance("Masa �st� Bilgisayarlar�20 Cart");
			Product product3 = Product.getInstance("Toshiba 52019 Cart", 150.0, category2);
			Product product4 = Product.getInstance("Toshiba 72019 Cart", 150.0, category2);

			ShoppingCartModel cart = new ShoppingCartModel();
			cart.addItem(product1, 1);
			cart.addItem(product2, 1);
			cart.addItem(product3, 2);
			cart.addItem(product4, 1);

			Double minPurchase = 770.0;
			Double discountRule = 20.0;
			
			Coupons coupon = new Coupons(minPurchase, discountRule, DiscountType.AMOUNT);

			cart = cartService.applyCouponDiscount(coupon, cart);

			assertEquals(Double.valueOf(0.0), cart.getCouponDiscount());
		}
		
		@Test
		void test11() throws Exception {

			ShoppingCartService cartService = new ShoppingCartService();

			Category category1 = Category.getInstance("Masa �st� Bilgisayarlar�21 Cart");
			Product product1 = Product.getInstance("Toshiba 52020 Cart", 150.0, category1);
			Product product2 = Product.getInstance("Toshiba 72020 Cart", 150.0, category1);

			Category category2 = Category.getInstance("Masa �st� Bilgisayarlar�22 Cart");
			Product product3 = Product.getInstance("Toshiba 52021 Cart", 150.0, category2);
			Product product4 = Product.getInstance("Toshiba 72021 Cart", 150.0, category2);

			category1.getChildCategories().add(category2);
			category2.setParentCategory(category1);
			
			ShoppingCartModel cart = new ShoppingCartModel();
			cart.addItem(product1, 1);
			cart.addItem(product2, 1);
			cart.addItem(product3, 2);
			cart.addItem(product4, 1);

			
			// campaign calculation
			Double discountRuleCampaign = 40.0;
			Integer minItem = 10;
			Campaign campaign = new Campaign(category1, discountRuleCampaign, minItem, DiscountType.AMOUNT);

			Double discountRuleCampaign2 = 10.0;
			Integer minItem2 = 5;
			Campaign campaign2 = new Campaign(category1, discountRuleCampaign2, minItem2, DiscountType.RATE);

			List<Campaign> campaignList = new ArrayList<>();
			campaignList.add(campaign);
			campaignList.add(campaign2);

			cart = cartService.applyCampaignDiscount(campaignList, cart);
			
			// coupon calculation
			Double minPurchase = 600.0;
			Double discountRuleCoupon = 10.0;			
			Coupons coupon = new Coupons(minPurchase, discountRuleCoupon, DiscountType.RATE);

			cart = cartService.applyCouponDiscount(coupon, cart);
			
			// deliveryCost calculation
			DeliveryCostModel deliveryCostModel = new DeliveryCostModel(2.99, 5.99, 3.99);			
			DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();
			Double deliveryCost = deliveryCostCalculator.calculateFor(cart, deliveryCostModel);
			
			cartService.print(cart);
		}
		
		// Kampanya uygulamad�m, sadece Kupon
		@Test
		void test12() throws Exception {

			ShoppingCartService cartService = new ShoppingCartService();

			Category category1 = Category.getInstance("Masa �st� Bilgisayarlar�23 Cart");
			Product product1 = Product.getInstance("Toshiba 52022 Cart", 150.0, category1);
			Product product2 = Product.getInstance("Toshiba 72022 Cart", 150.0, category1);

			Category category2 = Category.getInstance("Masa �st� Bilgisayarlar�24 Cart");
			Product product3 = Product.getInstance("Toshiba 52023 Cart", 150.0, category2);
			Product product4 = Product.getInstance("Toshiba 72023 Cart", 150.0, category2);

			category1.getChildCategories().add(category2);
			category2.setParentCategory(category1);
			
			ShoppingCartModel cart = new ShoppingCartModel();
			cart.addItem(product1, 1);
			cart.addItem(product2, 1);
			cart.addItem(product3, 2);
			cart.addItem(product4, 1);
			
			// coupon calculation
			Double minPurchase = 600.0;
			Double discountRuleCoupon = 10.0;			
			Coupons coupon = new Coupons(minPurchase, discountRuleCoupon, DiscountType.RATE);

			cart = cartService.applyCouponDiscount(coupon, cart);
			
			// deliveryCost calculation
			DeliveryCostModel deliveryCostModel = new DeliveryCostModel(2.99, 5.99, 3.99);			
			DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();
			Double deliveryCost = deliveryCostCalculator.calculateFor(cart, deliveryCostModel);
			
			cartService.print(cart);
		}
}
