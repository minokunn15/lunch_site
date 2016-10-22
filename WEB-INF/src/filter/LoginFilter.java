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
 * ログインしないと入れない場所に関するfilter
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
	    HttpServletRequest request = (HttpServletRequest) arg0;
	    HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		if (userId == null || userId.equals("") || userId.equals("000000")) {
			System.out.println("ログインしてください");
			response.sendRedirect("../(MR03)login.html");
			return;
		}
		arg2.doFilter(arg0, arg1);
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
