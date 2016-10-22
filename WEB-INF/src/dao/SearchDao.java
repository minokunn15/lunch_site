package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import vo.CategoryVo;
import vo.NewsVo;

/**
 * @author 01014554
 * 記事検索に関するクラス
 */
public class SearchDao extends BaseDao{

	/**
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 * 検索を絞りこむときにすべてのカテゴリーを取り出すメソッド
	 */
	public ArrayList<CategoryVo> getAllCategory() throws NamingException, SQLException {
		getConnection();
		ArrayList<CategoryVo>categoryList = new ArrayList<CategoryVo>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select category_id,store_category from category_masters";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				CategoryVo categoryVo = new CategoryVo();
				categoryVo.setCategoryId(rs.getString("category_id"));
				categoryVo.setStoreCategory(rs.getString("store_category"));
				categoryList.add(categoryVo);
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
		return categoryList;
	}

	/**
	 * @param keyWord
	 * @return
	 * @throws NamingException
	 * @throws SQLException
	 * フリーワードで検索して合致した記事を得るメソッド
	 */
	public ArrayList<NewsVo> getFreewordResult(int start,int end,String keyWord,String categoryId,String area) throws NamingException, SQLException {
		getConnection();
		ArrayList<NewsVo>searchFreeResult = new ArrayList<NewsVo>();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select news_id,title,news_img,store_name,store_url from( "
				+ "select news.news_id,title,news_img,store_name,store_url,texts,category_id,prefactures,row_number() over (order by news_id) rnd from (news inner join "
				+ "stores on news.store_id = stores.store_id) left outer join area_masters am "
				+ "on am.area_id = stores.area_id ";
		//フリーワードについて考慮する//
		int freeResult = 0;
		if (!keyWord.equals("")) {
			sql += "where (texts like ? or title like ?)";
			freeResult = 1;
		}

		//カテゴリーIDについて考慮する//
		int categoryResult = 0;
		if (!categoryId.equals("all")&&freeResult==1) {
			sql+= "and category_id = ?";
			categoryResult = 1;
		}
		if (!categoryId.equals("all")&&freeResult==0) {
			sql+= "where category_id = ?";
			categoryResult = 1;
		}

		//エリアについて考慮する//
		int areaResult = 0;
		if (!area.equals("")&&(freeResult==1 || categoryResult==1)) {
			sql+="and (PREFACTURES is null or PREFACTURES like ?) ";
			areaResult = 1;
		}
		if (!area.equals("")&&freeResult==0&&categoryResult==0) {
			sql+="where (PREFACTURES is null or PREFACTURES like ?) ";
			areaResult = 1;
		}

		sql += ") where rnd between ? and ?";

		try {
			psmt = con.prepareStatement(sql);
			int i = 1;
			if (freeResult==1) {
				psmt.setString(1,"%"+keyWord+"%");
				psmt.setString(2,"%"+keyWord+"%");
				i=i+2;
			}
			if (categoryResult==1) {
				psmt.setString(i,categoryId);
				i++;
			}
			if (areaResult==1) {
				psmt.setString(i,"%"+area+"%");
				i++;
			}
			psmt.setInt(i,start);
			psmt.setInt(i+1,end);
			rs = psmt.executeQuery();
			while (rs.next()) {
				NewsVo news = new NewsVo();
				news.setNewsId(rs.getString("news_id"));
				news.setTitle(rs.getString("title"));
				news.setNewsImg(rs.getString("news_img"));
				news.setStoreName(rs.getString("store_name"));
				news.setStoreUrl(rs.getString("store_url"));
				searchFreeResult.add(news);
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
		return searchFreeResult;
	}

	/**
	 * @return
	 * 全ての記事数を取ってくる
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int getNewsCoount() throws NamingException, SQLException {
		getConnection();
		int result = 0;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select count(*)count from news";
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count");
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
		return result;
	}

	/**
	 * @param keyWord
	 * @param categoryId
	 * @param area
	 * @return
	 * 検索結果の記事数を返す
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int getSearchResultCount(String keyWord,String categoryId,String area) throws NamingException, SQLException {
		int result = 0;
		getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "select count(distinct news.news_id)count from ((news inner join "
				+ "stores on news.store_id = stores.store_id) left outer join area_masters am "
				+ "on am.area_id = stores.area_id) ";
		//フリーワードについて考慮する//
		int freeResult = 0;
		if (!keyWord.equals("")) {
			sql += "where (texts like ? or title like ?)";
			freeResult = 1;
		}

		//カテゴリーIDについて考慮する//
		int categoryResult = 0;
		if (!categoryId.equals("all")&&freeResult==1) {
			sql+= "and category_id = ?";
			categoryResult = 1;
		}
		if (!categoryId.equals("all")&&freeResult==0) {
			sql+= "where category_id = ?";
			categoryResult = 1;
		}

		//エリアについて考慮する//
		int areaResult = 0;
		if (!area.equals("")&&(freeResult==1 || categoryResult==1)) {
			sql+="and (PREFACTURES is null or PREFACTURES like ?) ";
			areaResult = 1;
		}
		if (!area.equals("")&&freeResult==0&&categoryResult==0) {
			sql+="where (PREFACTURES is null or PREFACTURES like ?) ";
			areaResult = 1;
		}
		try {
			psmt = con.prepareStatement(sql);
			int i = 1;
			if (freeResult==1) {
				psmt.setString(1,"%"+keyWord+"%");
				psmt.setString(2,"%"+keyWord+"%");
				i=i+2;
			}
			if (categoryResult==1) {
				psmt.setString(i,categoryId);
				i++;
			}
			if (areaResult==1) {
				psmt.setString(i,"%"+area+"%");
				i++;
			}
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count");
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
		return result;
	}
}
