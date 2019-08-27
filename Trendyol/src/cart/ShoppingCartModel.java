package cart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import item.Product;
import item.ProductOfCart;

public class ShoppingCartModel {

	private List<ProductOfCart> productOfCartList;
	private Double totalShoppingCartAmount;
	private Double couponDiscount;
	private Double campaignDiscount;
	private Double deliveryCost;
	
	
	public ShoppingCartModel() {
		super();
		totalShoppingCartAmount = new Double(0.0);
		couponDiscount = new Double(0.0);
		campaignDiscount = new Double(0.0);
		deliveryCost = new Double(0.0);
	}

	public void addItem(Product product, Integer quantity) {
		
		getProductOfCartList();
		if(product != null && quantity != null) {
			ProductOfCart cart = new ProductOfCart(product, quantity);
			this.productOfCartList.add(cart);
			totalShoppingCartAmount += product.getPrice() * quantity;
		}		
	}
	
	//Ekran üzerinden toplu ürün seçilemeyeceði için için productOfCartList set metodunu yazmadým. Ayný anda sadece 1 farklý ürün sepete eklenebilecek. 
	public List<ProductOfCart> getProductOfCartList() {
		if(CollectionUtils.isNotEmpty(productOfCartList)) {
			return productOfCartList;
		}else {
			return productOfCartList = new ArrayList<>();
		}
		
	}

	public Double getTotalShoppingCartAmount() {
		return totalShoppingCartAmount;
	}
	
	public Double getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(Double couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public Double getCampaignDiscount() {
		return campaignDiscount;
	}

	public void setCampaignDiscount(Double campaignDiscount) {
		this.campaignDiscount = campaignDiscount;
	}

	public Double getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(Double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	
	public Double getTotalAmountAfterDiscounts() {
		
		Double totalAmountAfterDiscounts = this.totalShoppingCartAmount - this.campaignDiscount - this.couponDiscount;
		return totalAmountAfterDiscounts;
	}
}
