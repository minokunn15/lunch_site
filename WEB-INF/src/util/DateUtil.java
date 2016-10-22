package util;

import java.time.*;

/**
 * @author 01014554
 * dateを扱うクラス
 */
public class DateUtil {

	/**
	 * @return
	 * 今日の日にちをsqldate型に変換するクラス
	 * countは日にちの指定0は今日
	 */
	public static java.sql.Date getDate(int count) {
		LocalDate now = LocalDate.now();
		LocalDate date = now.minusDays(count);
		java.sql.Date sqlNow = java.sql.Date.valueOf(date);
		return sqlNow;
	}
}
