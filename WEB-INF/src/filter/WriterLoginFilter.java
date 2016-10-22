package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 01014554
 * ライターさんしか入れないページに
 * 毎回ライターか否かのチェックを行うクラス
 */
public class WriterLoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
	    HttpServletRequest request = (HttpServletRequest) arg0;
	    HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		//未ログインの人//
		String userId = (String) session.getAttribute("userId");
		//権限が違う人//
		int userFlag = (int) session.getAttribute("userFlag");
		if (userId==null || userFlag==0) {
			System.out.println("あなたは入る権限がないユーザーです");
			response.sendRedirect("../Controller/Index");
			return;
		}
		arg2.doFilter(arg0, arg1);
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
