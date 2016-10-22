package vo;
/**
 * 記事に関するVo
 */
public class NewsVo implements java.io.Serializable{
	private static final long serialVersionUID = -4137407898783534871L;
	private String newsId;
	private String title;
	private String newsImg;
	private String texts;
	private String storeId;
	private String storeName;
	private String storeUrl;

	public NewsVo(){
		super();
	}

	/**
	 * @param newsId
	 * @param title
	 * @param newsImg
	 * @param texts
	 * @param store_id
	 */
	public NewsVo(String newsId, String title, String newsImg, String texts, String storeId,String storeName,String storeUrl) {
		super();
		setNewsId(newsId);
		setTitle(title);
		setNewsImg(newsImg);
		setTexts(texts);
		setStoreId(storeId);
		setStoreName(storeName);
		setStoreUrl(storeUrl);
	}

	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		if (newsId!=null&&!newsId.equals("")) {
			this.newsId = newsId;
		} else {
			System.err.println("news_idが空です。");
		}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if (title!=null&&!title.equals("")) {
			this.title = title;
		} else {
			System.err.println("titleが空です。");
		}
	}
	public String getNewsImg() {
		return newsImg;
	}
	public void setNewsImg(String newsImg) {
		if (newsImg!=null&&!newsImg.equals("")) {
			this.newsImg = newsImg;
		} else {
			System.err.println("画像がありません。");
		}
	}
	public String getTexts() {
		return texts;
	}
	public void setTexts(String texts) {
		if (texts!=null&&!texts.equals("")) {
			this.texts = texts;
		} else {
			System.err.println("本文がありません。");
		}
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		if (storeId!=null&&!storeId.equals("")) {
			this.storeId = storeId;
		} else {
			System.err.println("本文がありません。");
		}
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		if (storeName!=null&&!storeName.equals("")) {
			this.storeName = storeName;
		}
	}

	public String getStoreUrl() {
		return storeUrl;
	}

	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl;
	}
}
