package pack;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

public class StrOpe {
	/**
	 * 32bit整数型変換可否判定メソッド
	 * @param str 判定対象文字列
	 * @return true: 変換可能, false: 変換不可能
	 */
	public static boolean isInteger(final String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 32bit浮動小数点型変換可否判定メソッド
	 * @param str 判定対象文字列
	 * @return true: 変換可能, false: 変換不可能
	 */
	public static boolean isFloat(final String str) {
		try {
			Float.parseFloat(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 整数文字列フォーマットメソッド
	 * @param target 変換対象文字列
	 * @return 変換済文字列
	 */
	public static String numForm(final String target) {
		return isInteger(target) ? new DecimalFormat(",000").format(Integer.parseInt(target)) : target;
	}

	/**
	 * 左揃え整列メソッド
	 * @param target 整列対象文字列
	 * @param width 列幅
	 * @return 整列済文字列
	 */
	public static String leftAlignment(final String target, final int width) {
		if (target != null) {
			int byteDiff = (target.getBytes(StandardCharsets.UTF_8).length - target.length()) / 2;
			return String.format("%-" + (width - byteDiff) + "s", target);
		} else return String.format("%-" + width + "s", "");
	}
}