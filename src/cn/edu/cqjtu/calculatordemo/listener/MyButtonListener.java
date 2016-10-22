package cn.edu.cqjtu.calculatordemo.listener;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.cqjtu.calculatordemo.R;
import cn.edu.cqjtu.calculatordemo.exception.MyException;
import cn.edu.cqjtu.calculatordemo.util.InputUtil;
import cn.edu.cqjtu.calculatordemo.view.MyLinearLayout;

/**
 * 自定义按钮
 * 
 * @author JohnNiang
 *
 */
public class MyButtonListener implements OnClickListener {
	/**
	 * 正在输入的部分
	 */
	private StringBuilder inputExpression = null;

	private TextView tv_currentResult = null;

	private TextView tv_preResult = null;

	private Context context = null;

	public boolean computed = false;

	public boolean converted = false;

	private PopupMenu popupMenu;

	public MyButtonListener(Context context, TextView tv_currentResult, TextView tv_preResult, PopupMenu popupMenu) {
		this.context = context;
		this.tv_currentResult = tv_currentResult;
		this.tv_preResult = tv_preResult;
		this.popupMenu = popupMenu;
		// 初始化StringBuilder
		inputExpression = new StringBuilder();
		// 设置popupMenu点击事件
		popupMenu
				.setOnMenuItemClickListener(new MyPopMenuItemClickListener(context, popupMenu, tv_currentResult, this));
	}

	@Override
	public void onClick(View v) {
		inputExpression.setLength(0);
		inputExpression.append(tv_currentResult.getText());

		if (converted) {
			// 如果已经转换成功，则进行清空操作
			inputExpression.setLength(0);
			tv_currentResult.setText("");
			converted = false;
		}

		try {
			if (v.getId() == R.id.btn_point) {
				if (computed) {
					inputExpression.setLength(0);
					computed = false;
				}
				String result = InputUtil.inputPoint(inputExpression.toString());
				inputExpression.setLength(0);
				inputExpression.append(result);
			}

			for (int i = 0; i < 10; i++) {
				if (v.getId() == MyLinearLayout.BTN_IDS[i]) {
					if (computed) {
						inputExpression.setLength(0);
						computed = false;
					}
					String result = InputUtil.inputNumber(inputExpression.toString(), i);
					inputExpression.setLength(0);
					inputExpression.append(result);
					// converted = false;
				}
			}

			if (v.getId() == R.id.btn_add || v.getId() == R.id.btn_sub || v.getId() == R.id.btn_mul
					|| v.getId() == R.id.btn_div) {
				if (inputExpression.toString().isEmpty()) {
					// 如果为空则不进行任何操作
					return;
				}
				char oper = ' ';
				if (v.getId() == R.id.btn_add) {
					oper = InputUtil.OPER_ADD;
				} else if (v.getId() == R.id.btn_sub) {
					oper = InputUtil.OPER_SUB;
				} else if (v.getId() == R.id.btn_mul) {
					oper = InputUtil.OPER_MUL;
				} else {
					oper = InputUtil.OPER_DIV;
				}
				String result = InputUtil.inputOper(inputExpression.toString(), oper);
				System.out.println("result: " + result);
				if (result.contains("=")) {
					// 如果计算出结果
					inputExpression.append(result);
					tv_preResult.setText(inputExpression.toString());
					inputExpression.setLength(0);
					inputExpression.append(result.substring(1));
					inputExpression.append(oper);
				} else {
					// 如果没有计算出结果
					if (!InputUtil.isOper(result.charAt(result.length() - 1))) {
						// 如果最后一位不为操作数
						inputExpression.append(oper);
					}
					inputExpression.setLength(0);
					inputExpression.append(result);
				}
				computed = false;
			}

			if (v.getId() == R.id.btn_equal) {
				String result = null;
				if (inputExpression.toString().isEmpty()) {
					// 如果为空则不进行任何操作
					return;
				}
				result = InputUtil.inputEqual(inputExpression.toString());

				// 如果表达式正确 ，则进行相应的填充字符串的操作
				inputExpression.append(result);
				tv_preResult.setText(inputExpression.toString());
				inputExpression.setLength(0);
				if (result.startsWith(")=")) {
					inputExpression.append(result.substring(2));
				} else if (result.startsWith("=")) {
					inputExpression.append(result.substring(1));
				}
				computed = true;
			}
			if (v.getId() == R.id.btn_convert) {
				popupMenu.show();
			}

			if (v.getId() == R.id.btn_back) {
				String result = InputUtil.inputBack(inputExpression.toString());
				inputExpression.setLength(0);
				inputExpression.append(result);
			}

			if (v.getId() == R.id.btn_c) {
				String result = InputUtil.inputRemoveAll(inputExpression.toString());
				// 清空所有的内容
				tv_preResult.setText("");
				inputExpression.setLength(0);
				inputExpression.append(result);
			}

			if (v.getId() == R.id.btn_ce) {
				String result = InputUtil.inputRemoveNum(inputExpression.toString());
				inputExpression.setLength(0);
				inputExpression.append(result);
			}
			// TODO 限制输入的长度

		} catch (MyException e) {
			Toast.makeText(context, "0 不能作为除数", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (NumberFormatException e) {
			Toast.makeText(context, "表达式错误,请删除操作符或者添加操作数", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (Exception e) {
			Toast.makeText(context, "格式未能转换", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		// 每点击一次更新一次UI
		this.tv_currentResult.setText(inputExpression.toString());
	}
}
