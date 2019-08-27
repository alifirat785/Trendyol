package discount;

import enums.DiscountType;

public class Coupons {

	private Double minPurchase;
	private Double discountRule;
	private DiscountType discountType;
	
	public Coupons(Double minPurchase, Double discountRule, DiscountType discountType) {
		super();
		this.minPurchase = minPurchase;
		this.discountRule = discountRule;
		this.discountType = discountType;
	}

	public Double getMinPurchase() {
		return minPurchase;
	}

	public void setMinPurchase(Double minPurchase) {
		this.minPurchase = minPurchase;
	}

	public Double getDiscountRule() {
		return discountRule;
	}

	public void setDiscountRule(Double discountRule) {
		this.discountRule = discountRule;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}
	
	
}
