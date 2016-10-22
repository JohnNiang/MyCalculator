package cn.edu.cqjtu.calculatordemo.util;

import java.text.NumberFormat;

import cn.edu.cqjtu.calculatordemo.control.Operation;
import cn.edu.cqjtu.calculatordemo.control.OperationFactory;
import cn.edu.cqjtu.calculatordemo.exception.MyException;

/**
 * �������빤����
 * 
 * @author JohnNiang
 *
 */
public class InputUtil {

	public static final char OPER_ADD = '��';
	public static final char OPER_SUB = '��';
	public static final char OPER_MUL = '��';
	public static final char OPER_DIV = '��';

	private InputUtil() {
	}

	/**
	 * ����ģ��
	 * 
	 * @param str
	 *            �ַ���
	 * @param callBack
	 *            �ص��ӿ�
	 * @return ����һ���ַ���
	 * @throws NumberFormatException
	 * @throws MyException
	 */
	public static String inputTemplate(String str, CallBack callBack) throws NumberFormatException, MyException {
		if (str == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder(str);

		///////////////////////
		///// ����Ϊ�Զ����� //////
		///////////////////////
		sb = callBack.doExcute(sb, str);

		return sb.toString();
	}

	/**
	 * ��ȡ���������ִ��е�λ�ã�ǰ���ǲ�����ֻ����һλ�����򽫳���
	 * 
	 * @param str
	 *            ���ʽ
	 * @return ���ز�������λ��
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
	 * �ж�һ���ַ��Ƿ�Ϊ����
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
	 * ����С����
	 * 
	 * @param str
	 *            ��Ҫ�жϵ��ַ���
	 * @return ������������ַ���
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
	 * ��������
	 * 
	 * @param str
	 *            ��Ҫ�жϵ��ַ���
	 * @param num
	 *            ����
	 * @return ������������ַ���
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
	 * ���������
	 * 
	 * @param str
	 *            ��Ҫ�жϵ��ַ���
	 * @param operCh
	 *            ������
	 * @return ������������ַ���
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
	 * ������ڷ���
	 * 
	 * @param str
	 *            ��Ҫ�жϵ��ַ���
	 * @return ������������ַ���
	 * @throws Exception
	 *             ��������쳣���ʾ0���ܱ���Ϊ����
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
	 * �����˸��
	 * 
	 * @param str
	 *            ��Ҫ�жϵ��ַ���
	 * @return ������������ַ���
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
	 * �����Ƴ��������ݼ�
	 * 
	 * @param str
	 *            ��Ҫ�жϵ��ַ���
	 * @return ������������ַ���
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
	 * �����Ƴ�һ����������
	 * 
	 * @param str
	 *            ��Ҫ�жϵ��ַ���
	 * @return ������������ַ���
	 * @throws MyException
	 * @throws NumberFormatException
	 */
	public static String inputRemoveNum(String str) throws NumberFormatException, MyException {

		return inputTemplate(str, new CallBack() {

			@Override
			public StringBuilder doExcute(StringBuilder sb, String str) throws NumberFormatException, MyException {
				int index_oper = getOperIndex(str);
				if (index_oper == -1) {
					// ��û�������������ʱ��ֱ�ӽ�����ղ���
					sb.setLength(0);
				} else {
					sb.replace(0, sb.length(), sb.substring(0, index_oper + 1));
				}
				return sb;
			}
		});
	}

	/**
	 * ͨ�����ʽ��������ʽ�Ľ��
	 * 
	 * @param expression
	 *            ���ʽ
	 * @return �������Ϊnull�����ʾ���ʽ���󣬲��ܽ��м��㣬���򷵻ؼ�����
	 * @throws NumberFormatException
	 *             ��������쳣���ʾ���ʽ����
	 * @throws MyException
	 *             ��������쳣���ʾ0���ܱ���Ϊ����
	 */
	public static String computeResult(String expression) throws MyException, NumberFormatException {
		if (!validateExpression(expression)) {
			return null;
		}
		String result = null;
		int idx_oper = getOperIndex(expression);
		// ���ָ�ʽ��
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setGroupingUsed(false);
		numberFormat.setMaximumFractionDigits(10);
		if (idx_oper == -1) {
			// ������������result��
			result = "=" + numberFormat.format(Double.parseDouble(expression));
		} else {
			String numberAStr = expression.substring(0, idx_oper);
			String numberBStr = expression.substring(idx_oper + 1);
			Operation operation = OperationFactory.createOperation("" + expression.charAt(idx_oper));
			// ���ַ���ת��Ϊ����
			operation.setNumberA(Double.parseDouble(numberAStr));
			operation.setNumberB(Double.parseDouble(numberBStr));
			result = "=" + numberFormat.format(operation.getResult());
		}
		return rebuildResult(result);
	}

	/**
	 * ��֤���ʽ�Ƿ���Ч
	 * 
	 * @param expression
	 *            ��Ҫ��֤�ı��ʽ
	 * @return ������ʽ��ȷ���򷵻�true�����򷵻�false
	 */
	public static boolean validateExpression(String expression) {
		// 1. ����ִ�Ϊ�գ�����false
		// 2. ����ִ����һλ��Ϊ���֣��򷵻�false
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
	 * �ع����
	 * 
	 * @param resultStr
	 *            ����Ľṹ
	 * @return �ع���Ľṹ
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
