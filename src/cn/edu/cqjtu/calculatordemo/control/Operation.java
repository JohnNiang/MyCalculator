package cn.edu.cqjtu.calculatordemo.control;

import cn.edu.cqjtu.calculatordemo.exception.MyException;

/**
 * ����������
 * 
 * @author JohnNiang
 *
 */
public abstract class Operation {

	/**
	 * ��һ��������
	 */
	protected double numberA = 0;

	/**
	 * �ڶ���������
	 */
	protected double numberB = 0;

	/**
	 * ��ȡ���
	 * 
	 * @return ��������Ľ��
	 * @throws Exception
	 */
	public abstract double getResult() throws MyException;

	public double getNumberA() {
		return numberA;
	}

	public void setNumberA(double numberA) {
		this.numberA = numberA;
	}

	public double getNumberB() {
		return numberB;
	}

	public void setNumberB(double numberB) {
		this.numberB = numberB;
	}

}
