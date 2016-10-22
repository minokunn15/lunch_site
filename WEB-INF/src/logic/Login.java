package logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import exception.LoginErrorException;
import security.CustomLog;
import security.Digest;
import vo.NewsVo;
import vo.UserVo;

/**
 * @author 01014554
 * ログインに関するロジッククラス
 */
public class Login extends BaseLogic{
	/**
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * user情報を返す
	 */
	private UserVo getUserInfo(String mailAddress,String passwd) throws NamingException, SQLException {
		LoginDao dao = new LoginDao();
		UserVo user = dao.getUserInfo(mailAddress, passwd);
		return user;
	}

	/**
	 * @param passwd
	 * @return
	 * パスワードハッシュ化させる。
	 */
	private String getHashedPasswd(String passwd){
		Digest digest = new Digest(Digest.SHA512);
		String hashedPasswd = null;
		hashedPasswd = digest.hex(passwd);
		return hashedPasswd;
	}

	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException, NamingException, LoginErrorException {
	    res.setContentType("text/html;charset=UTF-8");
	    boolean result = true;
	    //パラメーターを取得する//
	    String mailAddress = (String)req.getParameter("mailAddress");
	    String passwd = (String)req.getParameter("password");
	    if (mailAddress==null || mailAddress.equals("")) {
	    	result = false;
	    	throw new LoginErrorException("メールアドレスが空です");
	    }
	    if (passwd==null || passwd.equals("")) {
	    	result = false;
	    	throw new LoginErrorException("パスワードが空です");
	    }
	    //ハッシュ化したパスワードを取得する//
	    String hashedPasswd = this.getHashedPasswd(passwd);
    	UserVo user = this.getUserInfo(mailAddress, hashedPasswd);
    	//ログイン、ログイン失敗のログを取る//
    	String logname = req.getServletContext().getRealPath("/WEB-INF/log/logfile.log");
    	String status = null;
    	String userId = null;
    	CustomLog log = CustomLog.getInstance();
    	if (logic.Auth.authJudge(user.getUserId())) {
    		HttpSession session = req.getSession();
    		//ログイン成功したら新しくセッションをスタートさせる//
    		session.invalidate();
    		session = req.getSession(true);
    		session.setAttribute("userId",user.getUserId());
    		session.setAttribute("nickname",user.getNickName());
    		session.setAttribute("userFlag", user.getUserFlag());
    		//ログイン成功なのでstatus更新//
    		status = "success";
    		userId = mailAddress;
    		log.writeLoginLog(logname, userId, status, req);
    		//IndexLogicから表示する記事を持ってくる//
    		Index indexLogic = new Index();
			ArrayList<NewsVo>suggestNewsList = indexLogic.getSuggestNews(user.getUserId());
			ArrayList<NewsVo>newsList = indexLogic.getNewsList();
			ArrayList<NewsVo>rankNews = indexLogic.getRankingNews();
		    req.setAttribute("suggestList",suggestNewsList);
		    req.setAttribute("newsList",newsList);
		    req.setAttribute("rankNews",rankNews);
    	} else {
    		result = false;
    		status = "fail";
    		userId = mailAddress;
    		log.writeLoginLog(logname, userId, status, req);
    		throw new LoginErrorException("ログインできませんでした。メールアドレスかパスワードが間違っています。");
    	}
    	return result;
	}
}
