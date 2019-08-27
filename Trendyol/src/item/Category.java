package item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

public class Category {

	private String categoryName;
	private Category parentCategory;
	private List<Category> childCategories;
	private static Set<String> allCategoriesName;

	private Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public static Category getInstance(String categoryName) throws Exception {

		getAllCategoriesName();
		Boolean isAdded = allCategoriesName.add(categoryName);
		if (!isAdded) {
			throw new Exception("Category already exist!");
		} else {
			allCategoriesName.add(categoryName);
			return new Category(categoryName);
		}
	}

	public String getCategoryName() {
		return categoryName;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public List<Category> getChildCategories() {
		if (CollectionUtils.isNotEmpty(childCategories)) {
			return childCategories;
		} else {
			return childCategories = new ArrayList<>();
		}

	}

	public void setChildCategories(List<Category> childCategories) {
		this.childCategories = childCategories;
	}

	public static Set<String> getAllCategoriesName() {
		if (CollectionUtils.isNotEmpty(allCategoriesName)) {
			return allCategoriesName;
		} else {
			return allCategoriesName = new HashSet<>();
		}
	}
}
