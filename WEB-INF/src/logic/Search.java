package logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SearchDao;
import vo.CategoryVo;

/**
 * @author 01014554
 * 検索に関するクラス
 */
public class Search extends BaseLogic{

	private ArrayList<CategoryVo> getAllCategory() throws NamingException, SQLException {
		ArrayList<CategoryVo>categoryList = new ArrayList<CategoryVo>();
		SearchDao dao = new SearchDao();
		categoryList = dao.getAllCategory();
		return categoryList;
	}

	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException, NamingException {
		res.setContentType("text/html;charset=UTF-8");
		boolean result = true;
		ArrayList<CategoryVo>categoryList = this.getAllCategory();
		req.setAttribute("categoryList",categoryList);
		return result;
	}
}
