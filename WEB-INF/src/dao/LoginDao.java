package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;



import vo.UserVo;

/**
 * @author 01014554
 * ログイン認証に関するdao
 */
public class LoginDao extends BaseDao{
	/**
	 * @param mailAddress
	 * @param passwd
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * DBからユーザー情報を取得する
	 */
	public UserVo getUserInfo(String mailAddress,String passwd) throws NamingException, SQLException{
		getConnection();
		UserVo user = new UserVo();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select user_id,mail_address,nickname,area,user_flag,user_img from users where mail_address = ? and passwd = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1,mailAddress);
			psmt.setString(2,passwd);
			rs = psmt.executeQuery();
			if (rs.next()) {
				user.setUserId(rs.getString("user_id"));
				user.setMailAdress(rs.getString("mail_address"));
				user.setNickName(rs.getString("nickname"));
				user.setArea(rs.getString("area"));
				user.setUserFlag(rs.getInt("user_flag"));
				user.setUserImg(rs.getString("user_img"));
			}
		} finally {
				if (rs!=null) {
					rs.close();
				}
				if (psmt!=null) {
					psmt.close();
				}
				closeConnection();
			}
		return user;
	}
}

