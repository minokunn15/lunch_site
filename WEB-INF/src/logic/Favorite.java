package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import dao.FavoriteDao;
import json.JsonUtil;
import vo.MessageVo;
import vo.NewsVo;

/**
 * @author 01014554
 * お気に入りに関するクラス
 */
public class Favorite extends BaseLogic{
	/**
	 * @param userId
	 * @param newsId
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 */
	private int insertFavriteResult(String userId,String newsId) throws NamingException, SQLException {
		//念のためuserIdが000000の人を除く//
		FavoriteDao dao = new FavoriteDao();
		int result = 0;
		if (!userId.equals("000000") && !userId.equals("") && userId != null) {
			result = dao.insertFavoriteNews(userId, newsId);
		} else {
			result = 0;
		}
		return result;
	}

	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException, NamingException {
		res.setContentType("application/json;charset=UTF-8");
		boolean result = true;
		//セッションからuserIdを取得する//
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		//リクエストパラメーターを取ってくる//
		String newsIdJson = req.getParameter("requestNewsId");
		NewsVo newsVo = JsonUtil.parse(NewsVo.class,newsIdJson);
		String newsId = newsVo.getNewsId();
		System.out.println(newsId);
		//メッセージを格納するクラスを作り、JSONに変換する//
		MessageVo message = new MessageVo();
		//お気に入りした記事をDBに挿入する//
		try {
			int InsertResult = this.insertFavriteResult(userId, newsId);
			if (InsertResult!=0) {
				message.setMessage("お気に入りに追加しました");
			} else {
				message.setMessage("お気に入り追加できませんでした。既に登録してある記事です");
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			message.setMessage("お気に入り追加できませんでした");
			result = false;
		} finally {
			String json = JsonUtil.convert(message);
			System.out.println(json);
			req.setAttribute("message",json);
			PrintWriter out = res.getWriter();
			out.print(json);
		}
		return result;
	}
}
