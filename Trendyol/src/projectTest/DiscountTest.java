package projectTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import discount.Campaign;
import discount.Coupons;
import enums.DiscountType;
import item.Category;

class DiscountTest {

	// CampaignDiscount getter setter
	@Test
	void test1() throws Exception {

		Category category2 = Category.getInstance("Masa Üstü Bilgisayarlarý1 Discount");
		Category category3 = Category.getInstance("Masa Üstü Bilgisayarlarý2 Discount");

		Double discountRule = 50.0;
		Integer minItem = 5;

		Campaign campaign = new Campaign(category2, discountRule, minItem, DiscountType.AMOUNT);

		campaign.setCategory(category3);
		campaign.setDiscountRule(30.0);
		campaign.setMinItem(3);
		campaign.setDiscountType(DiscountType.RATE);

		assertEquals(category3, campaign.getCategory());
		assertEquals(Double.valueOf(30.0), campaign.getDiscountRule());
		assertEquals(Integer.valueOf(3), campaign.getMinItem());
		assertEquals(DiscountType.RATE, campaign.getDiscountType());
	}
	
	@Test
	void test2() throws Exception {
		
		Category category2 = Category.getInstance("Masa Üstü Bilgisayarlarý3 Discount");
		Category category3 = Category.getInstance("Masa Üstü Bilgisayarlarý4 Discount");
		
		Double discountRule = 50.0;
		Double minPurchase = 100.0;
		
		Coupons coupon = new Coupons(minPurchase, discountRule, DiscountType.RATE);
		
		coupon.setMinPurchase(150.0);
		coupon.setDiscountRule(10.0);
		coupon.setDiscountType(DiscountType.AMOUNT);
		
		assertEquals(Double.valueOf(150.0), coupon.getMinPurchase());
		assertEquals(Double.valueOf(10.0), coupon.getDiscountRule());
		assertEquals(DiscountType.AMOUNT, coupon.getDiscountType());
	}
}
