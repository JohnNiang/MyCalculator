package cn.edu.cqjtu.calculatordemo.control;

import cn.edu.cqjtu.calculatordemo.exception.MyException;

/**
 * 操作抽象类
 * 
 * @author JohnNiang
 *
 */
public abstract class Operation {

	/**
	 * 第一个操作数
	 */
	protected double numberA = 0;

	/**
	 * 第二个操作数
	 */
	protected double numberB = 0;

	/**
	 * 获取结果
	 * 
	 * @return 计算出来的结果
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
