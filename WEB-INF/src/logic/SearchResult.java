package logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SearchDao;
import vo.NewsVo;

/**
 * @author 01014554
 * 検索結果を表示するロジックのクラス
 */
public class SearchResult extends BaseLogic{

	private ArrayList<NewsVo> getFreeResult(int start,int end,String keyWord,String category,String area) throws NamingException, SQLException {
		ArrayList<NewsVo>getFreeResultList = new ArrayList<NewsVo>();
		SearchDao dao = new SearchDao();
		getFreeResultList = dao.getFreewordResult(start,end,keyWord, category, area);
		return getFreeResultList;
	}

	private int getNewsCount() throws NamingException, SQLException {
		SearchDao dao = new SearchDao();
		int result = dao.getNewsCoount();
		return result;
	}

	private int getSearchResultCount(String keyWord,String category,String area) throws NamingException, SQLException {
		SearchDao dao = new SearchDao();
		int result = dao.getSearchResultCount(keyWord, category, area);
		return result;
	}

	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException, NamingException {
		res.setContentType("text/html;charset=UTF-8");
		boolean result = true;
		String keyWord = req.getParameter("freeword");
		String category = req.getParameter("cate");
		String area = req.getParameter("area");
		int start = Integer.parseInt(req.getParameter("start"));
		int end = Integer.parseInt(req.getParameter("end"));
		int newsCount = this.getNewsCount();
		int searchCount = this.getSearchResultCount(keyWord, category, area);
		ArrayList<NewsVo>searchResultList = this.getFreeResult(start,end,keyWord, category, area);
		req.setAttribute("searchResultList", searchResultList);
		req.setAttribute("newsCount",newsCount);
		req.setAttribute("searchCount",searchCount);
		req.setAttribute("countByPage", Math.ceil(searchCount/5.0));
		req.setAttribute("freeword", keyWord);
		req.setAttribute("category", category);
		req.setAttribute("area",area);
		req.setAttribute("start",start);
		req.setAttribute("end",end);
		System.out.println(Math.ceil(searchCount/5.0));
		return result;
	}
}
