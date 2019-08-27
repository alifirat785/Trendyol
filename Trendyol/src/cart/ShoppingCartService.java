package cart;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import discount.Campaign;
import discount.Coupons;
import enums.DiscountType;
import item.Category;
import item.CategoryService;
import item.Product;
import item.ProductOfCart;

public class ShoppingCartService {

	CategoryService categoryService = new CategoryService();

	public ShoppingCartModel applyCampaignDiscount(List<Campaign> campaignList, ShoppingCartModel shoppingCartModel) {

		Campaign maxAmountOfDiscountCampaign = findMaxAmountOfDiscountCampaign(campaignList, shoppingCartModel);
		ShoppingCartModel shoppingCartModelWithCampaignDiscount = getShoppingCartModelWithCampaignDiscount(
				maxAmountOfDiscountCampaign, shoppingCartModel);
		return shoppingCartModelWithCampaignDiscount;
	}

	public ShoppingCartModel applyCouponDiscount(Coupons coupons, ShoppingCartModel shoppingCartModel) {

		if (shoppingCartModel != null) {
			if (shoppingCartModel.getTotalShoppingCartAmount() >= coupons.getMinPurchase()) {

				if (coupons.getDiscountType() == DiscountType.AMOUNT) {
					shoppingCartModel.setCouponDiscount(coupons.getDiscountRule());
				} else if (coupons.getDiscountType() == DiscountType.RATE) {

					// Eðer kampanya indirimi daha önce uygulanmýþsa. Kupon oranýný doðru hesaplamak
					// için toplam tutardan kampanya oranýný çýkardým.
					// Toplam tutardan kampanya indirimi çýktýðýnda kalan tutara kuponu uyguladým.
					Double discountAmount = 0.0;
					if (shoppingCartModel.getCampaignDiscount() != null) {
						discountAmount = (shoppingCartModel.getTotalShoppingCartAmount()- shoppingCartModel.getCampaignDiscount()) 
											* (coupons.getDiscountRule() / 100);
					}
					shoppingCartModel.setCouponDiscount(discountAmount);
				}
			}
		}

		return shoppingCartModel;
	}

	public void print(ShoppingCartModel shoppingCartModel) {

		Map<Category, List<ProductOfCart>> productOfCartListGroup = shoppingCartModel.getProductOfCartList().stream()
				.collect(Collectors.groupingBy(productOfCart -> productOfCart.getProduct().getCategory()));

		productOfCartListGroup.values().stream().forEach(productOfCartList -> {

			productOfCartList.stream()
					.forEach(productOfCart -> System.out.println("Category Name : "
							+ productOfCart.getProduct().getCategory().getCategoryName() + " Product Name : "
							+ productOfCart.getProduct().getTitle() + " Quantity :" + productOfCart.getQuantity()
							+ " Unit Price :  " + productOfCart.getProduct().getPrice()));
		});

		Double totalDiscount = shoppingCartModel.getCampaignDiscount() + shoppingCartModel.getCouponDiscount();

		System.out.println("Total Price      : " + shoppingCartModel.getTotalShoppingCartAmount());
		System.out.println("Total Discount   : " + totalDiscount);
		System.out.println("Total Amount     : " + shoppingCartModel.getTotalAmountAfterDiscounts());
		System.out.println("Total Delivery   : " + shoppingCartModel.getDeliveryCost());
	}

	private ShoppingCartModel getShoppingCartModelWithCampaignDiscount(Campaign campaign,
			ShoppingCartModel shoppingCartModel) {

		// Bir kategoriye ait kampanya girildiði zaman o kategorinin alt kategorilerine
		// de ayný kampanyayý dahil ettim. Bu nedenle kategorinin alt kategorilerini
		// getiren servis yazdým
		if (campaign != null) {

			// child kategoriler getirildi.
			Set<String> allCategoryNamesBelongToCampaign = categoryService
					.getAllChildCategoryName(campaign.getCategory(), new HashSet<String>());

			// gelen listeye ana kategori eklendi.
			allCategoryNamesBelongToCampaign.add(campaign.getCategory().getCategoryName());

			if (shoppingCartModel != null) {
				if (CollectionUtils.isNotEmpty(shoppingCartModel.getProductOfCartList())) {

					Double totalCampaignDiscount = 0.0;
					for (ProductOfCart productOfCart : shoppingCartModel.getProductOfCartList()) {
						if (productOfCart.getProduct() != null) {
							if (allCategoryNamesBelongToCampaign
									.contains(productOfCart.getProduct().getCategory().getCategoryName())) {

								Product product = productOfCart.getProduct();
								if (campaign.getDiscountType() == DiscountType.AMOUNT) {

									totalCampaignDiscount += (campaign.getDiscountRule() * productOfCart.getQuantity());
								} else if (campaign.getDiscountType() == DiscountType.RATE) {

									Double discountAmount = product.getPrice() * (campaign.getDiscountRule() / 100);
									totalCampaignDiscount += (discountAmount * productOfCart.getQuantity());
								}
							}
						}
					}
					shoppingCartModel.setCampaignDiscount(totalCampaignDiscount);
				}
			}
		}
		return shoppingCartModel;
	}

	private Campaign findMaxAmountOfDiscountCampaign(List<Campaign> campaignList, ShoppingCartModel shoppingCartModel) {
		Double maxAmountOfDiscount = 0.0;
		Campaign maxAmountOfDiscountCampaign = null;

		if (CollectionUtils.isNotEmpty(campaignList)) {
			for (Campaign campaign : campaignList) {

				Double tempAmountOfDiscount = 0.0;
				Integer countOfDiscountingProduct = 0;

				// child kategoriler getirildi.
				Set<String> allCategoryNamesBelongToCampaign = categoryService
						.getAllChildCategoryName(campaign.getCategory(), new HashSet<String>());

				// gelen listeye ana kategori eklendi.
				allCategoryNamesBelongToCampaign.add(campaign.getCategory().getCategoryName());

				if (shoppingCartModel != null) {
					if (CollectionUtils.isNotEmpty(shoppingCartModel.getProductOfCartList())) {
						for (ProductOfCart productOfCart : shoppingCartModel.getProductOfCartList()) {
							if (productOfCart.getProduct() != null) {
								if (allCategoryNamesBelongToCampaign
										.contains(productOfCart.getProduct().getCategory().getCategoryName())) {

									countOfDiscountingProduct += productOfCart.getQuantity();
									Double amountOfDiscount = 0.0;

									if (campaign.getDiscountType() == DiscountType.AMOUNT) {
										amountOfDiscount = campaign.getDiscountRule();
									} else if (campaign.getDiscountType() == DiscountType.RATE) {
										amountOfDiscount = productOfCart.getProduct().getPrice()
												* (campaign.getDiscountRule() / 100);
									}

									tempAmountOfDiscount = tempAmountOfDiscount + amountOfDiscount;
									if (tempAmountOfDiscount > maxAmountOfDiscount
											&& countOfDiscountingProduct >= campaign.getMinItem()) {

										maxAmountOfDiscount = tempAmountOfDiscount;
										maxAmountOfDiscountCampaign = campaign;
									}
								}
							}
						}
					}
				}
			}
		}
		return maxAmountOfDiscountCampaign;
	}
}
