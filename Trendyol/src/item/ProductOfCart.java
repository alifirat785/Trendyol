package item;

public class ProductOfCart {

	private Product product;
	private Integer quantity;
	
	public ProductOfCart(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	// �r�n sepetine eklenmi� olan �r�n�n kendisi de�i�tirilemez sadece adeti de�i�ir. E�er �r�n de�i�tirilmek isteniyorsa silinmeli.
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
