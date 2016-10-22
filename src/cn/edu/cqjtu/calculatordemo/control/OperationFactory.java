package cn.edu.cqjtu.calculatordemo.control;

public class OperationFactory {

	public static Operation createOperation(String operate) {
		Operation oper = null;
		switch (operate) {
		case "гл":
			oper = new OperationAdd();
			break;
		case "гн":
			oper = new OperationSub();
			break;
		case "б┴":
			oper = new OperationMul();
			break;
		case "б┬":
			oper = new OperationDiv();
			break;
		}
		return oper;
	}

}
