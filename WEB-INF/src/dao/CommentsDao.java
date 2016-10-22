package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


import javax.naming.NamingException;


import vo.CommentsVo;

/**
 * @author 01014554
 * コメント投稿画面に関するDao
 */

public class CommentsDao extends BaseDao{
/*	*//**
	 * @author 01014554
	 * 記事IDから記事のタイトルを取得する
	 * @throws SQLException
	 * @throws NamingException
	 * 引数にnewsId,not null
	 */
	public String getTitle(String newsId) throws NamingException, SQLException{
		getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String title = null;
		String sql = "select title from news where news_id = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1,newsId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				title = rs.getString("title");
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
		return title;
	}

	/**
	 * @param newsId
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 * 記事IDから記事の平均評価と評価数を取得する
	 * 引数にnewsId not null
	 */
	public CommentsVo getAverageValue(String newsId) throws NamingException, SQLException {
		getConnection();
		con.setAutoCommit(false);
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<Integer>valueList = new ArrayList<Integer>();
		CommentsVo comment = new CommentsVo();
		String sql1 = "select round(avg(rating),0)avg,count(*)count from comments where news_id = ?";
		try {
			psmt = con.prepareStatement(sql1);
			psmt.setString(1,newsId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				comment.setAvgValue(rs.getInt("avg"));
				comment.setCount(rs.getInt("count"));
				psmt.close();
				rs.close();
			}
			//それぞれの評価を付けた人の数を取得//
			for (int i=1;i<=5;i++){
				String sql2 = "select count(*)count from comments where news_id = ? and rating = ?";
				psmt = con.prepareStatement(sql2);
				psmt.setString(1,newsId);
				psmt.setInt(2,i);
				rs  = psmt.executeQuery();
				if (rs.next()) {
					valueList.add(rs.getInt("count"));
					psmt.close();
					rs.close();
				}
			}
			comment.setValueList(valueList);
		} finally {
			if (rs!=null) {
				rs.close();
			}
			if (psmt!=null) {
				psmt.close();
			}
			closeConnection();
		}
		return comment;
	}
	/**
	 * @param newsId
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 * 他の人のコメントを表示する
	 */
	public ArrayList<CommentsVo> getUserComment(String newsId) throws NamingException, SQLException{
		getConnection();
		ArrayList<CommentsVo>commentList = new ArrayList<CommentsVo>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select rating,comments,nickname from comments ct inner join users on ct.user_id = users.user_id where news_id = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1,newsId);
			rs = psmt.executeQuery();
			while (rs.next()) {
				CommentsVo comment = new CommentsVo();
				comment.setRating(rs.getInt("rating"));
				comment.setComment(rs.getString("comments"));
				comment.setNickname(rs.getString("nickname"));
				commentList.add(comment);
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
		return commentList;
	}

	/**
	 * @param userId
	 * @param newsId
	 * @param rating
	 * @param comment
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * コメントをしたら、同じ人が同じ記事にコメントしない限り
	 * コメントテーブルに挿入してあげる
	 */
	public int InsertComment(String userId,String newsId,int rating,String comment) throws SQLException,NamingException {
		getConnection();
		falseAutoCommit();
		int result = 0;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		//既にあるかどうか調べる//
		String checkSql = "select user_id,news_id from comments where user_id = ? and news_id = ?";
		try {
			psmt = con.prepareStatement(checkSql);
			psmt.setString(1,userId);
			psmt.setString(2,newsId);
			rs = psmt.executeQuery();
			if (!rs.next()) {
				psmt.close();
				String sql = "insert into comments values(?,?,?,?)";
				psmt = con.prepareStatement(sql);
				psmt.setString(1,userId);
				psmt.setString(2,newsId);
				psmt.setInt(3,rating);
				psmt.setString(4, comment);
				result = psmt.executeUpdate();
				con.commit();
			}
		} catch (SQLException e) {
				con.rollback();
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
}

