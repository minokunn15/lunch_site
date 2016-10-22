package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReadsDao;
import dao.SuggestNewsDao;
import vo.NewsVo;

/**
 * @author 01014554
 * メイン画面を表示するロジックを構築するクラス。
 */
public class Index extends BaseLogic{

	//表示するおすすめ記事を取得する//
	/**
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * おすすめ記事を取得する
	 */
	public ArrayList<NewsVo> getSuggestNews(String userId)throws SQLException, NamingException {
		ArrayList<NewsVo>suggestNewsList = new ArrayList<NewsVo>();
		SuggestNewsDao dao = new SuggestNewsDao();
		suggestNewsList = dao.getSuggestNewsByUser(userId);
		return suggestNewsList;
	}

	//表示する記事一覧を取得する。mainpageは上位6件のみ取得//
	/**
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<NewsVo> getNewsList()throws SQLException, NamingException {
		ArrayList<NewsVo>newsList = new ArrayList<NewsVo>();
		ReadsDao dao = new ReadsDao();
		newsList = dao.getAllNews(6);
		return newsList;
	}

	//記事ランキングを取得する//
	/**
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 */
	public ArrayList<NewsVo> getRankingNews()throws SQLException,NamingException {
		ArrayList<NewsVo>rankNews = new ArrayList<NewsVo>();
		ReadsDao dao = new ReadsDao();
		rankNews = dao.getRankingNews();
		return rankNews;
	}

	public boolean doProcess(HttpServletRequest req,HttpServletResponse res) throws SQLException, NamingException {
		res.setContentType("text/html;charset=UTF-8");
		boolean result = true;
	    HttpSession session = req.getSession();
	    String userId = (String) session.getAttribute("userId");
	    ArrayList<NewsVo>suggestNewsList = this.getSuggestNews(userId);
		ArrayList<NewsVo>newsList = this.getNewsList();
		ArrayList<NewsVo>rankNews = this.getRankingNews();
		req.setAttribute("suggestList",suggestNewsList);
		req.setAttribute("newsList",newsList);
		req.setAttribute("rankNews",rankNews);
		return result;
	}
}
