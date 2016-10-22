package cn.edu.cqjtu.calculatordemo.util;

import cn.edu.cqjtu.calculatordemo.exception.MyException;

public interface CallBack {
	public StringBuilder doExcute(StringBuilder sb,String str) throws NumberFormatException, MyException;
}
