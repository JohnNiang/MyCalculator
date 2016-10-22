package cn.edu.cqjtu.calculatordemo.util;

/**
 * 转换相关的工具类
 * 
 * @author JohnNiang
 *
 */
public class ConvertUtil {

	private ConvertUtil() {
	}

	/**
	 * 获取“=”之前的字符串
	 * 
	 * @param expression
	 *            表达式
	 * @return “=”之前的字符串
	 */
	public static String getStrBeforeEqualSign(String expression) {
		if (expression == null) {
			return null;
		}
		return expression.split("=")[0];
	}

	/**
	 * 十进制装换为二进制
	 * 
	 * @param decimal
	 *            十进制字符串
	 * @return null，表示输入的为空或者转化失败，否则成功
	 */
	public static String decimal2Binary(String decimal) throws NumberFormatException {
		if (decimal == null || decimal.isEmpty()) {
			return null;
		}

		return Long.toBinaryString(Long.parseLong(decimal));
	}

	/**
	 * 二进制转换为十进制
	 * 
	 * @param binary
	 *            二进制
	 * @return null，表示输入的为空或者转化失败，否则成功
	 */
	public static String binary2Decimal(String binary) throws NumberFormatException {
		if (binary == null || binary.isEmpty()) {
			return null;
		}
		return Long.valueOf(binary, 2).toString();
	}

	/**
	 * 十进制转换为十六进制
	 * 
	 * @param decimal
	 *            十进制
	 * @return null，表示输入的为空或者转化失败，否则成功
	 */
	public static String decimal2Hex(String decimal) throws NumberFormatException {
		if (decimal == null || decimal.isEmpty()) {
			return null;
		}
		return Long.toHexString(Long.parseLong(decimal));
		// return Double.toHexString(Double.parseDouble(decimal));
	}

	/**
	 * 十六进制转换为十进制
	 * 
	 * @param hex
	 *            十六进制
	 * @return null，表示输入的为空或者转化失败，否则成功
	 */
	public static String hex2Decimal(String hex) throws NumberFormatException {
		if (hex == null || hex.isEmpty()) {
			return null;
		}
		return Long.valueOf(hex, 10).toString();
	}
}
