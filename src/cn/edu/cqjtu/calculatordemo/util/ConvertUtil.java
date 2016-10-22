package cn.edu.cqjtu.calculatordemo.util;

/**
 * ת����صĹ�����
 * 
 * @author JohnNiang
 *
 */
public class ConvertUtil {

	private ConvertUtil() {
	}

	/**
	 * ��ȡ��=��֮ǰ���ַ���
	 * 
	 * @param expression
	 *            ���ʽ
	 * @return ��=��֮ǰ���ַ���
	 */
	public static String getStrBeforeEqualSign(String expression) {
		if (expression == null) {
			return null;
		}
		return expression.split("=")[0];
	}

	/**
	 * ʮ����װ��Ϊ������
	 * 
	 * @param decimal
	 *            ʮ�����ַ���
	 * @return null����ʾ�����Ϊ�ջ���ת��ʧ�ܣ�����ɹ�
	 */
	public static String decimal2Binary(String decimal) throws NumberFormatException {
		if (decimal == null || decimal.isEmpty()) {
			return null;
		}

		return Long.toBinaryString(Long.parseLong(decimal));
	}

	/**
	 * ������ת��Ϊʮ����
	 * 
	 * @param binary
	 *            ������
	 * @return null����ʾ�����Ϊ�ջ���ת��ʧ�ܣ�����ɹ�
	 */
	public static String binary2Decimal(String binary) throws NumberFormatException {
		if (binary == null || binary.isEmpty()) {
			return null;
		}
		return Long.valueOf(binary, 2).toString();
	}

	/**
	 * ʮ����ת��Ϊʮ������
	 * 
	 * @param decimal
	 *            ʮ����
	 * @return null����ʾ�����Ϊ�ջ���ת��ʧ�ܣ�����ɹ�
	 */
	public static String decimal2Hex(String decimal) throws NumberFormatException {
		if (decimal == null || decimal.isEmpty()) {
			return null;
		}
		return Long.toHexString(Long.parseLong(decimal));
		// return Double.toHexString(Double.parseDouble(decimal));
	}

	/**
	 * ʮ������ת��Ϊʮ����
	 * 
	 * @param hex
	 *            ʮ������
	 * @return null����ʾ�����Ϊ�ջ���ת��ʧ�ܣ�����ɹ�
	 */
	public static String hex2Decimal(String hex) throws NumberFormatException {
		if (hex == null || hex.isEmpty()) {
			return null;
		}
		return Long.valueOf(hex, 10).toString();
	}
}
