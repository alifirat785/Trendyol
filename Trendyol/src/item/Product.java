package item;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

public class Product {

	// I gets title as unique. Otherwise I can't count different product in
	// DeliveryCostCalculator class
	private String title;
	private Double price;
	private Category category;
	private static Set<String> allProductsName;

	private Product(String title, Double price, Category category) {
		super();
		this.title = title;
		this.price = price;
		this.category = category;
	}

	public static Product getInstance(String title, Double price, Category category) throws Exception {

		getAllProductsName();
		Boolean isAdded = allProductsName.add(title);
		if (!isAdded) {
			throw new Exception("Product already exist!");
		} else {
			allProductsName.add(title);
			return new Product(title, price, category);
		}

	}

	// title unique deðerimiz olduðu için onun deðiþtirilmesine izin vermiyorum.
	public String getTitle() {
		return title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public static Set<String> getAllProductsName() {
		if (CollectionUtils.isNotEmpty(allProductsName)) {
			return allProductsName;
		} else {
			return allProductsName = new HashSet<>();
		}
	}
}
