package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import vo.NewsVo;

/**
 * @author 01014554
 * ライターさんのために登録してある店舗に関するクラス
 */
public class StoresDao extends BaseDao{
	/**
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 * 登録してあるお店のリストを取得する
	 */
	public ArrayList<NewsVo>getStoreList() throws NamingException, SQLException {
		getConnection();
		ArrayList<NewsVo>storeList = new ArrayList<NewsVo>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select store_id,store_name from stores";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				NewsVo store = new NewsVo();
				store.setStoreId(rs.getString("store_id"));
				store.setStoreName(rs.getString("store_name"));
				storeList.add(store);
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
		return storeList;
	}
}
