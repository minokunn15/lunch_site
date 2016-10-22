package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import exception.CommentValidationException;
import json.JsonUtil;
import vo.CommentsVo;
import vo.MessageVo;

/**
 * @author 01014554
 * コメントを確定させるクラス(jsonでメッセージ渡す)
 */
public class CommentComplete extends BaseLogic{

	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException, NamingException, CommentValidationException {
		res.setContentType("application/json;charset=UTF-8");
		boolean result = true;
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		//リクエストパラメーターを取ってくる//
		String reqParamJson = req.getParameter("requestParams");
		CommentsVo commentsVo = JsonUtil.parse(CommentsVo.class,reqParamJson);
		String newsId = commentsVo.getNewsId();
		int rating = commentsVo.getRating();
		String comment = commentsVo.getComment();
		//メッセージを格納するクラスを作り、JSONに変換する//
		MessageVo message = new MessageVo();

		try {
			//コメントが空の措置//
			if (comment==null || comment.equals("")) {
				throw new CommentValidationException("コメントがありません");
			}
			//タイトルかコメントが長すぎる時の処置//
			if (comment.length()>=150) {
				throw new CommentValidationException("コメントが長すぎます");
			}
			//ここまできたらinsert//
			Comment logic = new Comment();
			int insertResult = logic.insertCommentResult(userId, newsId, rating, comment);
			if (insertResult!=0) {
				message.setMessage("コメント投稿完了しました");
			} else {
				message.setMessage("コメント投稿に失敗しました。既にコメントしている記事です");
			}
		} catch (CommentValidationException e) {
			e.printStackTrace();
			message.setMessage(e.getMessage());
			//ajaxで知らせてあげたいので本来falseだが今回trueで返す//
			result = true;
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			message.setMessage("コメント投稿に失敗しました。");
			//ajaxで知らせてあげたいので本来falseだが今回trueで返す//
			result = true;
		} finally {
			String json = JsonUtil.convert(message);
			System.out.println(json);
			req.setAttribute("message",json);
		}
		return result;
	}
}
