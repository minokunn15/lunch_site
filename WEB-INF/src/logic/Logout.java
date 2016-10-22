package logic;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import security.CustomLog;

/**
 * @author 01014554
 * ログアウトに関するクラス
 */
public class Logout extends BaseLogic{

	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException, NamingException {
		res.setContentType("text/html;charset=UTF-8");
		boolean result = true;
	    HttpSession session = req.getSession();
    	String logname = req.getServletContext().getRealPath("/WEB-INF/log/logfile.log");
    	String status = "logout";
    	String userId = (String)session.getAttribute("userId");
    	CustomLog log = CustomLog.getInstance();
    	log.writeLoginLog(logname, userId, status, req);
	    session.removeAttribute("userId");
	    session.removeAttribute("nickname");
	    session.removeAttribute("userFlag");
	    session.setAttribute("userId","000000");
	    session.setAttribute("userFlag",0);
	    return result;
	}
}
