package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.AboutLogicClassException;
import exception.CommentValidationException;
import exception.FileException;
import exception.LoginErrorException;
import logic.BaseLogic;
import vo.MessageVo;
@WebServlet(urlPatterns = { "/Controller/*" })
public class Controller extends HttpServlet {

	/**
	 *フロントコントローラー
	 *リクエストとレスポンスのやり取りは
	 *全てここを通して行う。
	 */
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		//エンコーディング指定//
	    req.setCharacterEncoding("UTF-8");
	    res.setCharacterEncoding("UTF-8");

	    //uri部分を抜き出す//
	    String logicName = req.getPathInfo().replaceAll("/","");
	    String path = null;
	    String message = null;
		try {
			Class<?> clazz = Class.forName("logic."+logicName);
			BaseLogic logic = (BaseLogic) clazz.newInstance();
			//処理を実行//
		    boolean result = logic.doProcess(req, res);
		    //doProcessが成功するか否か//
		    if (result) {
			    if (util.LogicToJsp.logicToJsp.containsKey(logicName)) {
			    	path = "/WEB-INF/" +util.LogicToJsp.logicToJsp.get(logicName);
			    } else {
			    	message="ロジッククラスの名前が間違えています";
			    	throw new AboutLogicClassException(message);
			    }
		    }
		} catch (AboutLogicClassException|FileException|LoginErrorException e) {
			req.setAttribute("message",e.getMessage());
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			path = "/WEB-INF/exception.jsp";
		} catch (Exception e) {
			req.setAttribute("message","システム内部でエラーが起きました");
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			path = "/WEB-INF/exception.jsp";
		}
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(path);
		rd.forward(req,res);
	}

	protected void doPost(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException{
		this.doGet(req, res);
	}
}
