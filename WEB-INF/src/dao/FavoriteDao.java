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
 * お気に入り記事に関するDao
 */
public class FavoriteDao extends BaseDao{

	/**
	 * @param userId
	 * @param newsId
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 * お気に入りテーブルに登録するメソッド
	 * 同じ記事をお気に入り登録できないように
	 * トランザクション処理で行う
	 */
	public int insertFavoriteNews(String userId,String newsId) throws NamingException, SQLException {
		getConnection();
		falseAutoCommit();
		int result = 0;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		//既にあるかどうか調べる//
		String checkSql = "select user_id,news_id from favorites where user_id = ? and news_id = ?";
		try {
			psmt = con.prepareStatement(checkSql);
			psmt.setString(1,userId);
			psmt.setString(2,newsId);
			rs = psmt.executeQuery();
			if (!rs.next()) {
				psmt.close();
				String sql = "insert into favorites values(?,?,?)";
				psmt = con.prepareStatement(sql);
				psmt.setString(1,userId);
				psmt.setString(2,newsId);
				java.sql.Date sqlNow = DateUtil.getDate(0);
				psmt.setDate(3,sqlNow);
				result = psmt.executeUpdate();
				commit();
			}
		} catch (SQLException e) {
				rollBack();;
				throw e;
	    } finally {
			if (rs!=null) {
				rs.close();
			}
			if (psmt!=null) {
				psmt.close();
			}
			closeConnection();
	    }
		return result;
	}

	/**
	 * @param userId
	 * @return
	 * ユーザーのお気に入り登録した記事を取得する
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<NewsVo> getFavoriteNews(String userId) throws NamingException, SQLException {
		getConnection();
		ArrayList<NewsVo>favoriteList = new ArrayList<NewsVo>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select f.news_id,news_img,title,store_name,store_url from (favorites f inner join news n "
				   + "on f.news_id = n.news_id)inner join stores s on n.store_id = s.store_id "
				   + "where user_id = ? order by add_date desc";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			while (rs.next()) {
				NewsVo newsVo = new NewsVo();
				newsVo.setNewsId(rs.getString("news_id"));
				newsVo.setNewsImg(rs.getString("news_img"));
				newsVo.setTitle(rs.getString("title"));
				newsVo.setStoreName(rs.getString("store_name"));
				newsVo.setStoreUrl(rs.getString("store_url"));
				favoriteList.add(newsVo);
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
		return favoriteList;
	}
}

