package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.NamingException;

import exception.FileException;
import oracle.jdbc.OracleConnection.CommitOption;
import util.DateUtil;
import vo.NewsVo;

/**
 * @author 01014554
 * 記事の詳細を取得するdao
 */
public class NewsDao extends BaseDao{

	/**
	 * @param newsId 
	 * @return
	 * @throws NamingException 
	 * @throws SQLException 
	 * 引数がnewsIdでそれに該当する
	 * 記事の詳細情報を取得する
	 */
	public NewsVo getDetailNews(String newsId) throws NamingException, SQLException{
		getConnection();
		NewsVo detailNews = new NewsVo();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select news_id,title,texts,news_img,news.store_id,store_name,store_url from news inner join stores on news.store_id = stores.store_id "
				+ "where news_id = ?";
		try{
			psmt = con.prepareStatement(sql);
			psmt.setString(1,newsId);
			rs = psmt.executeQuery();
			//記事IDはユニークなはずなので1件のみ取得//
			if (rs.next()) {
				detailNews.setNewsId(rs.getString("news_id"));
				detailNews.setTitle(rs.getString("title"));
				detailNews.setTexts(rs.getString("texts"));
				detailNews.setNewsImg(rs.getString("news_img"));
				detailNews.setStoreId(rs.getString("store_id"));
				detailNews.setStoreName(rs.getString("store_name"));
				detailNews.setStoreUrl(rs.getString("store_url"));
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
		return detailNews;
	}


	/**
	 * @param userId
	 * @param newsId
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 * 同トランザクション内で処理するためのメソッド
	 */
	public int insertNewsHistory(String userId,String newsId) throws NamingException, SQLException{
		getConnection();
		falseAutoCommit();
		int result = 0;
		try {
			String histroyId = this.getNewsHistoryId();
			result = this.insertReadNews(histroyId,userId,newsId);
			commit();
		} catch (SQLException e) {
			e.printStackTrace();
			rollBack();
			throw e;
		} finally {
			closeConnection();
		}
		return result;
	}

	/**
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * 閲覧履歴のidを取得するメソッド
	 */
	private String getNewsHistoryId() throws SQLException, NamingException {
		String historyId = null;
		String sql = "select seq_history_id.nextval from dual";
		int historyIdInt = 0;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		psmt = con.prepareStatement(sql);
		rs = psmt.executeQuery();
		if (rs.next()) {
			historyIdInt = rs.getInt("nextval");
		}
		DecimalFormat df = new DecimalFormat("0000000000");
		historyId = df.format(historyIdInt);
		if (rs!=null) {
			rs.close();
		}
		if (psmt!=null) {
			psmt.close();
		}
		return historyId;
	}

	/**
	 * @return
	 * @throws SQLException
	 * 記事の新規IDを取得するメソッド
	 */
	private String getNewNewsId() throws SQLException {
		String newsId = null;
		String sql = "select seq_news_id.nextval from dual";
		int newsIdInt = 0;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		psmt = con.prepareStatement(sql);
		rs = psmt.executeQuery();
		if (rs.next()) {
			newsIdInt = rs.getInt("nextval");
		}
		DecimalFormat df = new DecimalFormat("000000");
		newsId = df.format(newsIdInt);
		if (rs!=null) {
			rs.close();
		}
		if (psmt!=null) {
			psmt.close();
		}
		return newsId;
	}

	/**
	 * @param userId
	 * @param newsId
	 * @return
	 * 閲覧履歴テーブルに閲覧履歴を挿入する。
	 * この際、IDの取得と同トランザクション
	 * @throws NamingException
	 * @throws SQLException
	 */
	private int insertReadNews(String historyId,String userId,String newsId) throws SQLException, NamingException {
		int result = 0;
		String sql = "insert into readnews values (?,?,?,?)";
		PreparedStatement psmt = null;
		psmt = con.prepareStatement(sql);
		psmt.setString(1,historyId);
		java.sql.Date sqlNow = DateUtil.getDate(0);
		psmt.setDate(2, sqlNow);
		psmt.setString(3, userId);
		psmt.setString(4, newsId);
		result = psmt.executeUpdate();
		if (psmt!=null) {
			psmt.close();
		}
		return result;
	}

	/**
	 * @param newsId
	 * @param title
	 * @param newsImg
	 * @param texts
	 * @param storeId
	 * @return
	 * @throws SQLException
	 * 新規記事を挿入するためのメソッド
	 */
	private int insertNewNews(String newsId,String title,String newsImg,String texts,String storeId) throws SQLException {
		int result = 0;
		String sql = "insert into news values (?,?,?,?,?)";
		PreparedStatement psmt = null;
		psmt = con.prepareStatement(sql);
		psmt.setString(1,newsId);
		psmt.setString(2,title);
		psmt.setString(3,newsImg);
		psmt.setString(4,texts);
		psmt.setString(5,storeId);
		result = psmt.executeUpdate();
		if (psmt!=null) {
			psmt.close();
		}
		return result;
	}

	private boolean titleCheck(String title) throws SQLException {
		boolean result = true;
		String sql = "select title from news where title = ?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		psmt = con.prepareStatement(sql);
		psmt.setString(1,title);
		rs = psmt.executeQuery();
		if (rs.next()) {
			result = false;
		}
		if (psmt!=null) {
			psmt.close();
		}
		return result;
	}

	/**
	 * @param title
	 * @param newsImg
	 * @param texts
	 * @param storeId
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 * 実際に新しい記事を挿入するメソッド
	 * @throws FileException
	 */
	public int insertNewNews(String title,String newsImg,String texts,String storeId) throws NamingException, SQLException, FileException{
		getConnection();
		falseAutoCommit();
		int result = 0;
		try {
			String newsId = this.getNewNewsId();
			boolean check = this.titleCheck(title);
			if (check) {
				result = this.insertNewNews(newsId,title,newsImg,texts,storeId);
				commit();
			} else {
				throw new FileException("タイトルが重複しています");
			}
		} catch (SQLException|FileException e) {
			e.printStackTrace();
			rollBack();
			throw e;
		} finally {
			closeConnection();
		}
		return result;
	}
}
