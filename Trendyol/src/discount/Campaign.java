package discount;

import enums.DiscountType;
import item.Category;

public class Campaign {

	private Category category;
	private Double discountRule;
	private Integer minItem;
	private DiscountType discountType;
	
	public Campaign(Category category, Double discountRule, Integer minItem, DiscountType discountType) {
		super();
		this.category = category;
		this.discountRule = discountRule;
		this.minItem = minItem;
		this.discountType = discountType;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Double getDiscountRule() {
		return discountRule;
	}

	public void setDiscountRule(Double discountRule) {
		this.discountRule = discountRule;
	}

	public Integer getMinItem() {
		return minItem;
	}

	public void setMinItem(Integer minItem) {
		this.minItem = minItem;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}
	

}
