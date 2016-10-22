package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.NamingException;

import util.DateUtil;
import vo.NewsVo;

/**
 * @author 01014554
 * おすすめ記事に関するクラス
 */
public class SuggestNewsDao extends BaseDao{
	/**
	 * @author 01014554
	 * ユーザーIDと日付を条件にしておすすめ記事のnews_idを取得する。
	 * @throws NamingException
	 */

	public ArrayList<NewsVo> getSuggestNewsByUser(String userId) throws SQLException, NamingException{
		getConnection();
		ArrayList<NewsVo>suggestNewsList = new ArrayList<NewsVo>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select sn.news_id,title,news_img,texts,stores.store_id,store_name,store_url from( suggestnews sn inner join news "
				+ "on sn.news_id = news.news_id)inner join stores on news.store_id = stores.store_id "
				+ "where sn.user_id = ? and sn.suggest_date = TO_DATE('2016-06-02','yyyy-mm-dd')";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1,userId);
			//本来日付ごとに動的に出したかったのですが、無理でした。一応やろうとしていたコードは残しておきます//
			//今回は適当な日付（2016-06-02)をsuggestテーブルに入れてそれを出します//
/*			java.sql.Date sqlNow = DateUtil.getDate();
			psmt.setDate(2,sqlNow);*/
			rs = psmt.executeQuery();
			//おすすめ記事は一日2件なので、配列長さ2の配列に結果を格納していく//
			while (rs.next()) {
				NewsVo newsVo = new NewsVo();
				newsVo.setNewsId(rs.getString("news_id"));
				newsVo.setTitle(rs.getString("title"));
				newsVo.setNewsImg(rs.getString("news_img"));
				newsVo.setTexts(rs.getString("texts"));
				newsVo.setStoreId(rs.getString("store_id"));
				newsVo.setStoreName(rs.getString("store_name"));
				newsVo.setStoreUrl(rs.getString("store_url"));
				suggestNewsList.add(newsVo);
			}
		} finally {
			if (rs!=null) {
				rs.close();
			}
			if (psmt!=null) {
				psmt.close();
			}
				closeConnection();
			}
		return suggestNewsList;
	}
}
