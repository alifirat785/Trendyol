package delivery;

public class DeliveryCostModel {

	private Double fixedCost;
	private Double costPerDelivery;
	private Double costPerProduct;
	
	public DeliveryCostModel(Double fixedCost, Double costPerDelivery, Double costPerProduct) {
		super();
		this.fixedCost = fixedCost;
		this.costPerDelivery = costPerDelivery;
		this.costPerProduct = costPerProduct;
	}

	public Double getFixedCost() {
		return fixedCost;
	}

	public void setFixedCost(Double fixedCost) {
		this.fixedCost = fixedCost;
	}

	public Double getCostPerDelivery() {
		return costPerDelivery;
	}

	public void setCostPerDelivery(Double costPerDelivery) {
		this.costPerDelivery = costPerDelivery;
	}

	public Double getCostPerProduct() {
		return costPerProduct;
	}

	public void setCostPerProduct(Double costPerProduct) {
		this.costPerProduct = costPerProduct;
	}
}
