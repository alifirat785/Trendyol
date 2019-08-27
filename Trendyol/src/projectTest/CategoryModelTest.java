package projectTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import item.Category;

class CategoryModelTest {

	@Test
	void test1() throws Exception {
		
		Category category1 = Category.getInstance("Elektronik Eþya Category Model");
		try {
			Category category2 = Category.getInstance("Elektronik Eþya Category Model");
		}catch (Exception e) {
			assertEquals("Category already exist!", e.getMessage());
		}		
	}

	@Test
	void test2() throws Exception {
		Category category1 = Category.getInstance("Bilgisayar Category Model");
		
		assertEquals("Bilgisayar Category Model", category1.getCategoryName());
	}

	@Test
	void test3() throws Exception {
		Category category5 = Category.getInstance("Telefon Category Model");

		Category category6 = Category.getInstance("Cep Telefonlarý Category Model");
		category5.getChildCategories().add(category6);
		category6.setParentCategory(category5);
		
		assertEquals("Telefon Category Model", category6.getParentCategory().getCategoryName());
		assertEquals(true, category5.getChildCategories().contains(category6));
	}
	
	@Test
	void test4() throws Exception {
		Category category5 = Category.getInstance("Giyim Category Model");
		List<Category> childCategories = new ArrayList<>();
		
 		Category category6 = Category.getInstance("Erkek Giyim Category Model");
		category6.setParentCategory(category5);
		childCategories.add(category6);
		
		Category category7 = Category.getInstance("Kadýn Giyim Category Model");
		category6.setParentCategory(category5);
		childCategories.add(category7);
		
		category5.setChildCategories(childCategories);
	
		assertEquals(true, category5.getChildCategories().contains(category6));
		assertEquals(true, category5.getChildCategories().contains(category7));
	}
}
