package vo;

import java.util.ArrayList;
import java.util.List;

public class CommentsVo implements java.io.Serializable{

	private static final long serialVersionUID = -4137407898783534871L;
	private int avgValue;
	private int count ;
	private ArrayList<Integer> valueList;
	private int rating;
	private String comment;
	private String nickname;
	private String newsId;


	/**
	 * @param avgValue
	 * @param count
	 * @param fiveCount
	 * @param fourCount
	 * @param threeCount
	 * @param twoCount
	 * @param oneCount
	 */
	public CommentsVo(int avgValue, int count) {
		super();
		this.avgValue = avgValue;
		this.count = count;
	}

	public int getAvgValue() {
		return avgValue;
	}
	public void setAvgValue(int avgValue) {
		this.avgValue = avgValue;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Integer> getValueList() {
		return valueList;
	}
	public void setValueList(ArrayList<Integer> valueList) {
		this.valueList = valueList;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public CommentsVo() {
		super();
	}
}
