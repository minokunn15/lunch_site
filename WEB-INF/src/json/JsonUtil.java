package json;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 01014554
 * JSON関連のparseやconvertを行うクラス
 */
public class JsonUtil {

	/**
	 * @param Vo
	 * @param json
	 * @return
	 * jsonから任意のjavaオブジェクト形式に変換する
	 */
	public static <T> T parse(Class<T> Vo, String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return (T) mapper.readValue(json, Vo);
		} catch (IOException e) {
			return null;
		}
	}
	/**
	 * @param Vo
	 * @return
	 * 任意のjavaオブジェクトからjson
	 * 形式に変換する
	 */
	public static String convert(Object Vo) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(Vo);
			return json;
		} catch(JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
