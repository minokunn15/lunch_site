package logic;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NewsDao;
import exception.AboutLogicClassException;
import vo.NewsVo;

/**
 * @author 01014554
 * 記事詳細を取得するためのロジック
 */
public class DetailNews extends BaseLogic{
	//詳細記事を取得//
	private NewsVo getDetailNews(String newsId) throws NamingException, SQLException{
		NewsVo detailNews = new NewsVo();
		NewsDao dao = new NewsDao();
		detailNews = dao.getDetailNews(newsId);
		return detailNews;
	}

	//見た記事の閲覧履歴を保存する//
	private int InsertReadNews(String userId,String newsId) throws SQLException,NamingException {
		int result = 0;
		NewsDao dao = new NewsDao();
		result = dao.insertNewsHistory(userId, newsId);
		return result;
	}

	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException, NamingException, AboutLogicClassException {
		res.setContentType("text/html;charset=UTF-8");
		boolean result = true;
	    //セッションからユーザーID取得//
	    HttpSession session = req.getSession();
	    String userId = (String)session.getAttribute("userId");
	    //リンクからリクエストパラメーターを取得する//
	    String newsId = (String) req.getParameter("newsId");
	    System.out.println(newsId);
	    NewsVo detailNews = this.getDetailNews(newsId);
		if (detailNews.getNewsId()==null || detailNews.getNewsId().equals("")) {
			result = false;
			throw new AboutLogicClassException("この記事は存在しません");
		} else {
			req.setAttribute("detailNews",detailNews);
			int InsertResult = this.InsertReadNews(userId, newsId);
			System.out.println(InsertResult+"件、履歴にインサートしました。");
		}
		return result;
	}
}
