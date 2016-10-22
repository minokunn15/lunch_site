package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import logic.Auth;
import logic.Index;

/**
 * @author 01014554
 * 全てのページでセッションにuserIdの値が入っていなければ
 * userId=000000を挿入するフィルタ
 */
public class AuthFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
	    HttpServletRequest request = (HttpServletRequest) arg0;
		HttpSession session = request.getSession();
		//userIdという名前でログインしたユーザーIDの情報を入れておく//
		String userId = (String)session.getAttribute("userId");
		boolean userType = Auth.authJudge(userId);
		//userTypeがfalseならば"000000"を設定する。//
		if(!userType){
			userId = "000000";
		}
		String nickname = (String)session.getAttribute("nickname");
		int userFlag = 0;
		if (userId.equals("000000")) {
			nickname = "名無しさん";
			userFlag = 0;
		} else {
			userFlag = (int)session.getAttribute("userFlag");
		}
	    session.setAttribute("userId",userId);
	    session.setAttribute("nickname",nickname);
	    session.setAttribute("userFlag",userFlag);
	    arg2.doFilter(arg0, arg1);
	    return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
