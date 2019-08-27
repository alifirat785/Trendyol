package projectTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import item.Category;
import item.Product;
import item.ProductOfCart;

class ProductTest {

	@Test
	void test1() throws Exception {
		
		Category category1 = Category.getInstance("Bilgisayar Product");
		Product product1 = Product.getInstance("HP 122 Product" , 1250.0, category1);
		try {
			Product product2 = Product.getInstance("HP 122 Product" , 1500.0, category1);
		}catch (Exception e) {
			assertEquals("Product already exist!", e.getMessage());
		}	
		
		assertEquals("HP 122 Product", product1.getTitle());
		assertEquals(Double.valueOf(1250.0), product1.getPrice());
		assertEquals(category1, product1.getCategory());
	}
	
	@Test
	void test2() throws Exception {
		
		Category category1 = Category.getInstance("Elektronik Eþya Product");
		Category category2 = Category.getInstance("Telefon Product");
		
		Product product1 = Product.getInstance("Dell 520 Product" , 1250.0, category1);
	
		product1.setCategory(category2);
		product1.setPrice(2250.0);

		assertEquals(Double.valueOf(2250.0), product1.getPrice());
		assertEquals(category2, product1.getCategory());
	}
	
	@Test
	void test3() throws Exception {
		
		// ProductOfCart tek baþýna kullanýlacak bir sýnýf deðil. 
		Category category1 = Category.getInstance("Masa Üstü Bilgisayarlarý Product");
		
		Product product1 = Product.getInstance("Toshiba 520 Product" , 3250.0, category1);

		ProductOfCart productOfCart = new ProductOfCart(product1,3);
		productOfCart.setQuantity(5);
		
		assertEquals(product1, productOfCart.getProduct());
		assertEquals(Integer.valueOf(5), productOfCart.getQuantity());
	}
}
