package cn.edu.cqjtu.calculatordemo.control;

import java.math.BigDecimal;

import cn.edu.cqjtu.calculatordemo.exception.MyException;

/**
 * ³ý·¨²Ù×÷Àà
 * 
 * @author JohnNiang
 *
 */
public class OperationDiv extends Operation {

	@Override
	public double getResult() throws MyException {
		if (this.numberB == 0) {
			throw new MyException();
		}
		BigDecimal bd1 = new BigDecimal(numberA);
		BigDecimal bd2 = new BigDecimal(numberB);
		return bd1.divide(bd2, 10, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
