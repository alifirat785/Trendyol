package projectTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cart.ShoppingCartModel;
import delivery.DeliveryCostCalculator;
import delivery.DeliveryCostModel;
import item.Category;
import item.Product;

class DeliveryCostCalculatorTest {

	// Sepette 2 farklý ürün 1 farklý kategori var
	@Test
	void test1() throws Exception {

		Category category1 = Category.getInstance("Masa Üstü Bilgisayarlarý Delivery");
		Product product1 = Product.getInstance("Toshiba 520 Delivery", 3250.0, category1);
		Product product2 = Product.getInstance("Toshiba 720 Delivery", 3750.0, category1);
		
		ShoppingCartModel cart = new ShoppingCartModel();
		cart.addItem(product1, 3);
		cart.addItem(product2, 1);
		
		DeliveryCostModel deliveryCostModel = new DeliveryCostModel(2.99, 5.99, 3.99);
		
		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();
		Double deliveryCost = deliveryCostCalculator.calculateFor(cart, deliveryCostModel);
		
		assertEquals(Double.valueOf(16.96), deliveryCost);
	}

	// Sepette hiç ürün yok
	@Test
	void test2() throws Exception {
		
		ShoppingCartModel cart = new ShoppingCartModel();
		
		DeliveryCostModel deliveryCostModel = new DeliveryCostModel(2.99, 5.99, 3.99);
		
		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();
		Double deliveryCost = deliveryCostCalculator.calculateFor(cart, deliveryCostModel);
		
		assertEquals(Double.valueOf(0), deliveryCost);
	}
	
	// Sepette 1 ürün 1 kategori var
	@Test
	void test3() throws Exception {

		Category category1 = Category.getInstance("Masa Üstü Bilgisayarlarý3 Delivery");
		Product product1 = Product.getInstance("Toshiba 5200 Delivery", 3250.0, category1);
	
		ShoppingCartModel cart = new ShoppingCartModel();
		cart.addItem(product1, 1);
		
		DeliveryCostModel deliveryCostModel = new DeliveryCostModel(2.99, 5.99, 3.99);
		
		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();
		Double deliveryCost = deliveryCostCalculator.calculateFor(cart, deliveryCostModel);
		
		assertEquals(Double.valueOf(12.97), deliveryCost);
	}
	
	// Ayný ürün sepete 2 kere eklendi
	@Test
	void test4() throws Exception {

		Category category1 = Category.getInstance("Masa Üstü Bilgisayarlarý4 Delivery");
		Product product1 = Product.getInstance("Toshiba 5201 Delivery", 3250.0, category1);
		
		ShoppingCartModel cart = new ShoppingCartModel();
		cart.addItem(product1, 3);
		cart.addItem(product1, 1);
		
		DeliveryCostModel deliveryCostModel = new DeliveryCostModel(2.99, 5.99, 3.99);
		
		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();
		Double deliveryCost = deliveryCostCalculator.calculateFor(cart, deliveryCostModel);
		
		assertEquals(Double.valueOf(12.97), deliveryCost);
	}
	
	@Test
	void test5() throws Exception {
		
		DeliveryCostModel deliveryCostModel = new DeliveryCostModel(2.99, 5.99, 3.99);
		
		deliveryCostModel.setFixedCost(2.0);
		deliveryCostModel.setCostPerDelivery(5.0);
		deliveryCostModel.setCostPerProduct(3.0);
		
		assertEquals(Double.valueOf(2.0), deliveryCostModel.getFixedCost());
		assertEquals(Double.valueOf(5.0), deliveryCostModel.getCostPerDelivery());
		assertEquals(Double.valueOf(3.0), deliveryCostModel.getCostPerProduct());	
	}
}
