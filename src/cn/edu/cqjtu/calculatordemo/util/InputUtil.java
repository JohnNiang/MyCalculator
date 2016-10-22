package cn.edu.cqjtu.calculatordemo.util;

import java.text.NumberFormat;

import cn.edu.cqjtu.calculatordemo.control.Operation;
import cn.edu.cqjtu.calculatordemo.control.OperationFactory;
import cn.edu.cqjtu.calculatordemo.exception.MyException;

/**
 * 数据输入工具类
 * 
 * @author JohnNiang
 *
 */
public class InputUtil {

	public static final char OPER_ADD = '＋';
	public static final char OPER_SUB = '－';
	public static final char OPER_MUL = '×';
	public static final char OPER_DIV = '÷';

	private InputUtil() {
	}

	/**
	 * 输入模板
	 * 
	 * @param str
	 *            字符串
	 * @param callBack
	 *            回调接口
	 * @return 返回一个字符串
	 * @throws NumberFormatException
	 * @throws MyException
	 */
	public static String inputTemplate(String str, CallBack callBack) throws NumberFormatException, MyException {
		if (str == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder(str);

		///////////////////////
		///// 以下为自定义区 //////
		///////////////////////
		sb = callBack.doExcute(sb, str);

		return sb.toString();
	}

	/**
	 * 获取操作数在字串中的位置，前提是操作数只能有一位，否则将出错
	 * 
	 * @param str
	 *            表达式
	 * @return 返回操作数的位置
	 */
	public static int getOperIndex(String str) {
		int result = -1;
		if (str == null || str.isEmpty()) {
			return -1;
		}
		int index_add = str.indexOf("" + OPER_ADD);
		int index_sub = str.indexOf("" + OPER_SUB);
		int index_mul = str.indexOf("" + OPER_MUL);
		int index_div = str.indexOf("" + OPER_DIV);
		int sum = index_add + index_sub + index_mul + index_div;
		if (sum > -4) {
			result = sum + 3;
		}
		return result;
	}

	/**
	 * 判断一个字符是否为数字
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isNumber(char ch) {
		if (ch >= '0' && ch <= '9') {
			return true;
		}
		return false;
	}

	public static boolean isOper(char ch) {
		if (ch == OPER_ADD || ch == OPER_SUB || ch == OPER_MUL || ch == OPER_DIV) {
			return true;
		}
		return false;
	}

	/**
	 * 输入小数点
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return 返回修正后的字符串
	 * @throws MyException
	 * @throws NumberFormatException
	 */
	public static String inputPoint(String str) throws NumberFormatException, MyException {
		return inputTemplate(str, new CallBack() {

			@Override
			public StringBuilder doExcute(StringBuilder sb, String str) {
				int operIndex = getOperIndex(str);
				int pointIndex = str.lastIndexOf("" + '.');
				if (str.isEmpty() || '-' == str.charAt(str.length() - 1) || operIndex == str.length() - 1) {
					sb.append('0').append('.');
				} else if (pointIndex <= operIndex) {
					sb.append('.');
				}
				return sb;
			}
		});
	}

	/**
	 * 输入数字
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @param num
	 *            数字
	 * @return 返回修正后的字符串
	 * @throws MyException
	 * @throws NumberFormatException
	 */
	public static String inputNumber(String str, final int num) throws NumberFormatException, MyException {
		return inputTemplate(str, new CallBack() {

			@Override
			public StringBuilder doExcute(StringBuilder sb, String str) {
				sb.append(num);
				return sb;
			}
		});
	}

	/**
	 * 输入运算符
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @param operCh
	 *            操作符
	 * @return 返回修正后的字符串
	 * @throws MyException
	 * @throws NumberFormatException
	 */
	public static String inputOper(String str, final char operCh) throws NumberFormatException, MyException {
		return inputTemplate(str, new CallBack() {

			@Override
			public StringBuilder doExcute(StringBuilder sb, String str) throws NumberFormatException, MyException {
				int operIdx = getOperIndex(str);
				if (str.length() > 0 && isNumber(str.charAt(str.length() - 1)) && operIdx == -1) {
					sb.append(operCh);
				} else if (operIdx == str.length() - 1) {
					sb.setLength(0);
					String replace = str.replace(str.charAt(operIdx), operCh);
					sb.append(replace);
				} else if (operIdx != -1) {
					String result = computeResult(str);
					if (result == null) {
						throw new NumberFormatException();
					}
					sb.setLength(0);
					sb.append(result);
				}
				return sb;
			}
		});
	}

	/**
	 * 输入等于符号
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return 返回修正后的字符串
	 * @throws Exception
	 *             如果出现异常则表示0不能被作为除数
	 */
	public static String inputEqual(String str) throws NumberFormatException, MyException {
		return inputTemplate(str, new CallBack() {

			@Override
			public StringBuilder doExcute(StringBuilder sb, String str) throws NumberFormatException, MyException {
				String result = computeResult(str);
				if (result == null) {
					throw new NumberFormatException();
				}
				sb.setLength(0);
				sb.append(result);
				return sb;
			}
		});
	}

	/**
	 * 输入退格键
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return 返回修正后的字符串
	 * @throws MyException
	 * @throws NumberFormatException
	 */
	public static String inputBack(String str) throws NumberFormatException, MyException {
		return inputTemplate(str, new CallBack() {

			@Override
			public StringBuilder doExcute(StringBuilder sb, String str) throws NumberFormatException, MyException {
				if (sb.length() > 0) {
					sb.setLength(sb.length() - 1);
				}
				return sb;
			}
		});

	}

	/**
	 * 输入移除所有内容键
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return 返回修正后的字符串
	 * @throws MyException
	 * @throws NumberFormatException
	 */
	public static String inputRemoveAll(String str) throws NumberFormatException, MyException {

		return inputTemplate(str, new CallBack() {

			@Override
			public StringBuilder doExcute(StringBuilder sb, String str) throws NumberFormatException, MyException {
				sb.setLength(0);
				return sb;
			}
		});
	}

	/**
	 * 输入移除一个操作数键
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @return 返回修正后的字符串
	 * @throws MyException
	 * @throws NumberFormatException
	 */
	public static String inputRemoveNum(String str) throws NumberFormatException, MyException {

		return inputTemplate(str, new CallBack() {

			@Override
			public StringBuilder doExcute(StringBuilder sb, String str) throws NumberFormatException, MyException {
				int index_oper = getOperIndex(str);
				if (index_oper == -1) {
					// 在没有输入操作符的时候，直接进行清空操作
					sb.setLength(0);
				} else {
					sb.replace(0, sb.length(), sb.substring(0, index_oper + 1));
				}
				return sb;
			}
		});
	}

	/**
	 * 通过表达式计算出表达式的结果
	 * 
	 * @param expression
	 *            表达式
	 * @return 如果返回为null，则表示表达式有误，不能进行计算，否则返回计算结果
	 * @throws NumberFormatException
	 *             如果出现异常则表示表达式错误
	 * @throws MyException
	 *             如果出现异常则表示0不能被作为除数
	 */
	public static String computeResult(String expression) throws MyException, NumberFormatException {
		if (!validateExpression(expression)) {
			return null;
		}
		String result = null;
		int idx_oper = getOperIndex(expression);
		// 数字格式化
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setGroupingUsed(false);
		numberFormat.setMaximumFractionDigits(10);
		if (idx_oper == -1) {
			// 计算结果并放入result中
			result = "=" + numberFormat.format(Double.parseDouble(expression));
		} else {
			String numberAStr = expression.substring(0, idx_oper);
			String numberBStr = expression.substring(idx_oper + 1);
			Operation operation = OperationFactory.createOperation("" + expression.charAt(idx_oper));
			// 将字符串转换为数字
			operation.setNumberA(Double.parseDouble(numberAStr));
			operation.setNumberB(Double.parseDouble(numberBStr));
			result = "=" + numberFormat.format(operation.getResult());
		}
		return rebuildResult(result);
	}

	/**
	 * 验证表达式是否有效
	 * 
	 * @param expression
	 *            需要验证的表达式
	 * @return 如果表达式正确，则返回true，否则返回false
	 */
	public static boolean validateExpression(String expression) {
		// 1. 如果字串为空，返回false
		// 2. 如果字串最后一位不为数字，则返回false
		boolean flag = true;
		if (expression == null || expression.isEmpty()) {
			flag = false;
		} else if (!isNumber(expression.charAt(expression.length() - 1))
				&& expression.charAt(expression.length() - 1) != '.') {
			flag = false;
		}
		return flag;
	}

	/**
	 * 重构结果
	 * 
	 * @param resultStr
	 *            计算的结构
	 * @return 重构后的结构
	 */
	public static String rebuildResult(String resultStr) {
		String result = resultStr;
		String[] numbers = result.split("\\.");
		if (numbers.length <= 1) {
			return resultStr;
		}
		char[] chs = numbers[1].toCharArray();
		for (int i = 0; i < chs.length; i++) {
			if (chs[i] != '0') {
				return resultStr;
			}
		}
		return numbers[0];
	}
}
