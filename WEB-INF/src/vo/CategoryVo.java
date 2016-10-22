package vo;

/**
 * @author 01014554
 * 検索情報に用いる、店のジャンルに関するVo
 */
public class CategoryVo {

	private String categoryId;
	private String storeCategory;


	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		if (categoryId!=null&&!categoryId.equals("")) {
			this.categoryId = categoryId;
		}
	}

	public String getStoreCategory() {
		return storeCategory;
	}
	public void setStoreCategory(String storeCategory) {
		this.storeCategory = storeCategory;
	}

}
