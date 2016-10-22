package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sun.javafx.runtime.VersionInfo;

/**
 * @author 01014554
 * DBへの接続、切断の処理を行う。
 * 基本的に全てのDAOから継承されるるクラス
 */
abstract public class BaseDao {
	protected Connection con = null;
	//DBに接続
	/**
	 * @throws NamingException
	 * @throws SQLException
	 * コネクション接続するためのメソッド
	 * DBに接続するときに使用
	 */
	public void getConnection() throws NamingException, SQLException{
		final String localname = "java:comp/env/jdbc/tyoushoku";
		DataSource ds = null;
		Context context = null;
		//コンテキストの生成
		context = new InitialContext();
		//コンテキストを検索
		ds = (DataSource)context.lookup(localname);
		//データベースへ接続
		this.con = ds.getConnection();
	}

	//DB切断
	/**
	 * @throws SQLException
	 * DBを切断するときに用いる。
	 * DB接続したら必ずこのメソッドを使う
	 */
	public void closeConnection() throws SQLException{
		if(con!=null){
			con.close();
		}
	}

	//自動コミットをなくすメソッド//
	/**
	 * @throws SQLException
	 * 自動コミットを解除するメソッド
	 * トランザクション管理のときに使う
	 */
	public void falseAutoCommit() throws SQLException {
		con.setAutoCommit(false);
	}

	//ロールバックするメソッド//
	/**
	 * @throws SQLException
	 * トランザクション時に、なにかエラーがおきたら元に戻すメソッド
	 * 基本、catch句で用いる
	 */
	public void rollBack() throws SQLException {
		con.rollback();
	}

	//コミットするメソッド//
	/**
	 * @throws SQLException
	 * 正常に完了したらコミットを行うメソッド
	 */
	public void commit() throws SQLException {
		con.commit();
	}
}
