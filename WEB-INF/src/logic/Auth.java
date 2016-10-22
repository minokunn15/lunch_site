package logic;

import java.io.IOException;

/**
 * @author 01014554
 * メインページに来る際、ログインユーザーか
 * 非ログインユーザーを判別する必要があるので
 * そのためのクラス
 */

public class Auth {

	/**
	 * @author 01014554
	 *　未ログインユーザーか、既ログインユーザー
	 *　かを判別するクラス。未ログインユーザーの
	 * 未ログイン=false,既ログイン=true
	 *　場合、user_id=000000とするのでここも弾く。
	 */
	//セッションからユーザーIDが取得でき、かつユーザーID=000000でなければtrueを返す//
	public static boolean authJudge(String userId) throws IOException{
		//未ログインか既ログインかの情報分岐//
		if(userId == null || userId.equals("") || userId.equals("000000")){
			return false;
		}else{
			return true;
		}
	}
}
