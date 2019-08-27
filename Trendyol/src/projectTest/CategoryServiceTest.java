package projectTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import item.Category;
import item.CategoryService;

class CategoryServiceTest {

	@Test
	void test() throws Exception {
		Category category1 = Category.getInstance("Elektronik Eþya Category Service");

		Category category2 = Category.getInstance("Bilgisayar Category Service");
		category1.getChildCategories().add(category2);
		category2.setParentCategory(category1);

		Category category3 = Category.getInstance("Notebook Category Service");
		category2.getChildCategories().add(category3);
		category3.setParentCategory(category2);

		Category category4 = Category.getInstance("Masa Üstü Bilgisayarlarý Category Service");
		category2.getChildCategories().add(category4);
		category4.setParentCategory(category2);

		Category category5 = Category.getInstance("Telefon Category Service");
		category1.getChildCategories().add(category5);
		category5.setParentCategory(category1);

		Category category6 = Category.getInstance("Cep Telefonlarý Category Service");
		category5.getChildCategories().add(category6);
		category6.setParentCategory(category5);

		CategoryService categoryService = new CategoryService();
		Set<String> allChildCategoryName = categoryService.getAllChildCategoryName(category1, new HashSet<>());
		assertEquals(true, allChildCategoryName.contains("Bilgisayar Category Service"));
		assertEquals(true, allChildCategoryName.contains("Notebook Category Service"));
		assertEquals(true, allChildCategoryName.contains("Masa Üstü Bilgisayarlarý Category Service"));
		assertEquals(true, allChildCategoryName.contains("Telefon Category Service"));
		assertEquals(true, allChildCategoryName.contains("Cep Telefonlarý Category Service"));
	}
}
