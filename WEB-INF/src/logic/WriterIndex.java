package logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.NewsVo;



/**
 * @author 01014554
 * ライターさんのメイン画面を表示させるクラス
 */
public class WriterIndex extends BaseLogic{

	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException, NamingException {
		boolean result = true;
		WriterLogin logic = new WriterLogin();
		ArrayList<NewsVo>storeList = logic.getStoreList();
		req.setAttribute("storeList",storeList);
		return result;
	}
}
