package cn.edu.cqjtu.calculatordemo.control;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;

/**
 * 加法操作类
 * 
 * @author JohnNiang
 *
 */
public class OperationAdd extends Operation {

	@Override
	public double getResult() {
		BigDecimal bd1 = new BigDecimal(numberA);
		BigDecimal bd2 = new BigDecimal(numberB);
		return bd1.add(bd2, MathContext.DECIMAL64).doubleValue();
	}
}
