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
import security.CustomLog;
import security.Digest;
import vo.NewsVo;
import vo.UserVo;
import dao.StoresDao;
import exception.LoginErrorException;

/**
 * @author 01014554
 *　ライターさんとしてログインさせるクラス
 */
public class WriterLogin extends BaseLogic{
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

	private UserVo getUserInfo(String mailAddress,String passwd) throws NamingException, SQLException {
		LoginDao dao = new LoginDao();
		UserVo user = dao.getUserInfo(mailAddress, passwd);
		return user;
	}

	/**
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 *
	 */
	public ArrayList<NewsVo> getStoreList() throws NamingException, SQLException {
		StoresDao dao = new StoresDao();
		ArrayList<NewsVo> storeList = dao.getStoreList();
		return storeList;
	}

	@Override
	public boolean doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException, SQLException, NamingException, LoginErrorException {
	    res.setContentType("text/html;charset=UTF-8");
	    boolean result = true;
	    //パラメーターを取得する//
	    String mailAddress = (String)req.getParameter("mailAddress");
	    String passwd = (String)req.getParameter("password");
	    //ハッシュ化したパスワードを取得する//
	    String hashedPasswd = this.getHashedPasswd(passwd);
    	UserVo user = this.getUserInfo(mailAddress, hashedPasswd);
    	//ログインログを出力する//
    	String logname = req.getServletContext().getRealPath("/WEB-INF/log/logfile.log");
    	String status = null;
    	String userId = null;
    	CustomLog log = CustomLog.getInstance();
    	if (logic.Auth.authJudge(user.getUserId())) {
    		HttpSession session = req.getSession();
    		session.setAttribute("userId",user.getUserId());
    		session.setAttribute("nickname",user.getNickName());
    		session.setAttribute("userFlag",user.getUserFlag());
    		//user_flagを取得//
    		int userFlag = user.getUserFlag();
    		if (userFlag == 1) {
    			ArrayList<NewsVo>storeList = this.getStoreList();
    			req.setAttribute("storeList",storeList);
    			status = "success";
    			userId = mailAddress;
    			log.writeLoginLog(logname, userId, status, req);
    		} else {
    			result = false;
    			status = "fail";
    			userId = mailAddress;
    			log.writeLoginLog(logname, userId, status, req);
    			throw new LoginErrorException("あなたはライターさんではありません。");
    		}
    	} else {
    		result = false;
			status = "fail";
			userId = mailAddress;
			log.writeLoginLog(logname, userId, status, req);
			throw new LoginErrorException("ログインできませんでした。メールアドレスかパスワードが間違っています");
    	}
    	return result;
	}
}
