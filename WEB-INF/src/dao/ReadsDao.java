package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.NamingException;

import util.DateUtil;
import vo.NewsVo;



/**
 * @author 01014554
 * 記事閲覧履歴に関するクラス
 */
public class ReadsDao extends BaseDao{
	/**
	 * @author 01014554
	 * 記事の一覧を取得する。
	 * @throws NamingException
	 */
	public ArrayList<NewsVo> getAllNews(int count) throws SQLException, NamingException{
		getConnection();
		ArrayList<NewsVo>newsList = new ArrayList<NewsVo>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select news_id,title,news_img,texts,store_id from news where rownum <= ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1,count);
			rs = psmt.executeQuery();
			while (rs.next()) {
				NewsVo news = new NewsVo();
				news.setNewsId(rs.getString("news_id"));
				news.setTitle(rs.getString("title"));
				news.setNewsImg(rs.getString("news_img"));
				news.setTexts(rs.getString("texts"));
				news.setStoreId(rs.getString("store_id"));
				newsList.add(news);
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
		return newsList;
	}

	/**
	 * @author 01014554
	 * 記事の閲覧ランキング上位三件を取得する
	 *　閲覧数上位三件(前日から７日間の順位)
	 * @throws NamingException
	 */
	public ArrayList<NewsVo> getRankingNews()throws SQLException, NamingException{
		getConnection();
		ArrayList<NewsVo>rankingNews = new ArrayList<NewsVo>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select news_id,title,news_img,texts,store_id from(select row_number() over (order by count desc) rnd,news.news_id,title,news_img,texts,store_id "
				+ "from(select count(*) count,news_id "
				+ "from readnews where read_date <= ? and read_date >= ? group by news_id)a inner join news "
				+ "on a.news_id = news.news_id) where rnd between 1 and 3";
		try {
			psmt = con.prepareStatement(sql);
			java.sql.Date sqlYesterday = DateUtil.getDate(1);
			java.sql.Date sqlSevenDayBefore = DateUtil.getDate(7);
			psmt.setDate(1,sqlYesterday);
			psmt.setDate(2,sqlSevenDayBefore);
			rs = psmt.executeQuery();
			while (rs.next()) {
				NewsVo rankNews = new NewsVo();
				rankNews.setNewsId(rs.getString("news_id"));
				rankNews.setTitle(rs.getString("title"));
				rankNews.setNewsImg(rs.getString("news_img"));
				rankNews.setTitle(rs.getString("title"));
				rankNews.setStoreId(rs.getString("store_id"));
				rankingNews.add(rankNews);
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
		return rankingNews;
	}
}
