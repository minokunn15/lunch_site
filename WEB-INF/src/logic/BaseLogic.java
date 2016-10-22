package logic;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseLogic {
	public abstract boolean doProcess(HttpServletRequest req, HttpServletResponse res) throws Exception;
}

