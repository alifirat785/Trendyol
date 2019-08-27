package item;

public class ProductOfCart {

	private Product product;
	private Integer quantity;
	
	public ProductOfCart(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	// Ürün sepetine eklenmiþ olan ürünün kendisi deðiþtirilemez sadece adeti deðiþir. Eðer ürün deðiþtirilmek isteniyorsa silinmeli.
	public Product getProduct() {
		return product;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
