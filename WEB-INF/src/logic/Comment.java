package logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentsDao;
import vo.CommentsVo;

/**
 * @author 01014554
 * コメント投稿画面を表示するための
 * 情報を取得するクラス
 */
public class Comment extends BaseLogic{

	//タイトル取得//
	private String getTitle(String newsId) throws NamingException, SQLException {
		String title = null;
		CommentsDao dao = new CommentsDao();
		title = dao.getTitle(newsId);
		return title;
	}

	//過去のratingのスコア系取得//
	private CommentsVo getRating(String newsId) throws NamingException, SQLException {
		CommentsVo commentRating = new CommentsVo();
		CommentsDao dao = new CommentsDao();
		commentRating = dao.getAverageValue(newsId);
		return commentRating;
	}

	//他の人のコメント情報を取得//
	private ArrayList<CommentsVo> getOtherComment(String newsId) throws NamingException, SQLException {
		ArrayList<CommentsVo>otherComment = new ArrayList<CommentsVo>();
		CommentsDao dao = new CommentsDao();
		otherComment = dao.getUserComment(newsId);
		return otherComment;
	}

	//コメントを投稿した後の挿入//
	/**
	 * @param userId
	 * @param newsId
	 * @param rating
	 * @param comment
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 * コメントをDBに挿入するメソッド
	 * 返値は挿入件数（基本1件）
	 */
	public int insertCommentResult(String userId,String newsId,int rating,String comment) throws NamingException, SQLException {
		//念のためuserIdが000000の人を除く//
		CommentsDao dao = new CommentsDao();
		int result = 0;
		if (userId != null && !userId.equals("000000") && !userId.equals("")) {
			result = dao.InsertComment(userId, newsId, rating, comment);
		} else {
			result = 0;
		}
		return result;
	}

	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException, NamingException {
		res.setContentType("text/html;charset=UTF-8");
		//リンクからリクエストパラメーター(newsId)を取得する//
		String newsId = (String) req.getParameter("newsId");
    	String title = this.getTitle(newsId);
    	boolean result = true;
    	CommentsVo commentRating = this.getRating(newsId);
    	ArrayList<CommentsVo> otherComments = this.getOtherComment(newsId);
    	req.setAttribute("title",title);
    	req.setAttribute("commentRating", commentRating);
    	req.setAttribute("otherComments", otherComments);
    	req.setAttribute("newsId", newsId);
    	return result;
	}
}
