package util;

import java.util.HashMap;

/**
 * @author 01014554
 * logicとjspを関連付けるクラス
 */
public class LogicToJsp {
    //実行するロジックと遷移させるjspを関連づける//
    public static final HashMap<String,String> logicToJsp = new HashMap<String,String>() {
    	/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		{
    	put("Index","MR01.jsp");
        put("DetailNews","MR02.jsp");
        put("Comment", "MR07.jsp");
        put("Login", "MR01.jsp");
        put("Logout","MR09.jsp");
        put("CommentComplete","json.jsp");
        put("Favorite","json.jsp");
        put("MyPage","MR06.jsp");
        put("Search","MR04.jsp");
        put("SearchResult", "MR05.jsp");
        put("WriterLogin","MRA01.jsp");
        put("AddNewsComfirm","MRA02.jsp");
        put("AddNewsComplete","MRA03.jsp");
        put("WriterIndex","MRA01.jsp");
    	}
    };
}
