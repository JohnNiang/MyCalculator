package cn.edu.cqjtu.calculatordemo.control;

import java.math.BigDecimal;
import java.math.MathContext;

public class OperationMul extends Operation {

	@Override
	public double getResult() {
		BigDecimal bd1 = new BigDecimal(numberA);
		BigDecimal bd2 = new BigDecimal(numberB);
		return bd1.multiply(bd2, MathContext.DECIMAL128).doubleValue();
	}

}
