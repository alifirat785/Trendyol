package delivery;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import cart.ShoppingCartModel;
import item.Category;
import item.Product;

public class DeliveryCostCalculator {

	public Double calculateFor(ShoppingCartModel cart, DeliveryCostModel costModel) {

		Integer numberOfProduct = getNumberOfProduct(cart);
		Integer numberOfDeliveries = getNumberOfDeliveries(cart);
		
		Double calculation = 0.0;
		if(numberOfProduct == 0 && numberOfDeliveries == 0) {
			cart.setDeliveryCost(calculation);
		}else {
			calculation = (costModel.getCostPerDelivery() * numberOfDeliveries)
					+ (costModel.getCostPerProduct() * numberOfProduct) + costModel.getFixedCost();
			
			cart.setDeliveryCost(calculation);
		}
		return calculation;
	}

	private Integer getNumberOfProduct(ShoppingCartModel cart) {

		Set<Product> productSet = new HashSet<Product>();

		if (cart != null && CollectionUtils.isNotEmpty(cart.getProductOfCartList())) {

			cart.getProductOfCartList().forEach(productOfCard -> {

				if (productOfCard.getProduct() != null) {

					productSet.add(productOfCard.getProduct());
				}

			});
			return productSet.size();
		}

		return 0;
	}

	private Integer getNumberOfDeliveries(ShoppingCartModel cart) {

		Set<Category> categoryNameSet = new HashSet<Category>();

		if (cart != null && CollectionUtils.isNotEmpty(cart.getProductOfCartList())) {

			cart.getProductOfCartList().forEach(productOfCard -> {

				if (productOfCard.getProduct() != null && productOfCard.getProduct().getCategory() != null) {

					categoryNameSet.add(productOfCard.getProduct().getCategory());
				}

			});
			return categoryNameSet.size();
		}

		return 0;
	}
}
