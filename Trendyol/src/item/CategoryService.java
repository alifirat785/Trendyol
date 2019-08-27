package item;

import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

public class CategoryService {

	public Set<String> getAllChildCategoryName(Category category, Set<String> allChildCategoryName){
		
		if(category != null) {
			if(CollectionUtils.isNotEmpty(category.getChildCategories())){
				category.getChildCategories().forEach(childCategory -> {
					allChildCategoryName.add(childCategory.getCategoryName());
					getAllChildCategoryName(childCategory, allChildCategoryName);
				});
			}
		}
		return allChildCategoryName;
	}
}
