package logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FavoriteDao;
import vo.NewsVo;

/**
 * @author 01014554
 * マイページを表示するためのロジッククラス
 */
public class MyPage extends BaseLogic{

	/**
	 * @param userId
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 * お気に入りリストを取得
	 */
	private ArrayList<NewsVo> getFavoriteNews(String userId) throws NamingException, SQLException {
		ArrayList<NewsVo> favoriteList = new ArrayList<NewsVo>();
		FavoriteDao dao = new FavoriteDao();
		favoriteList = dao.getFavoriteNews(userId);
		return favoriteList;
	}

	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException, NamingException {
		res.setContentType("text/html;charset=UTF-8");
		boolean result = true;
	    //ユーザーIDからおすすめ記事全てを取得//
	    HttpSession session = req.getSession();
	    String userId = (String) session.getAttribute("userId");
	    Index logic = new Index();
    	ArrayList<NewsVo> suggestList = logic.getSuggestNews(userId);
		ArrayList<NewsVo> favoriteList = this.getFavoriteNews(userId);
		req.setAttribute("favoriteList",favoriteList);
		req.setAttribute("suggestList",suggestList);
		return result;
	}
}
