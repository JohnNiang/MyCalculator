package cn.edu.cqjtu.calculatordemo.view;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import cn.edu.cqjtu.calculatordemo.R;
import cn.edu.cqjtu.calculatordemo.listener.MyButtonListener;
import cn.edu.cqjtu.calculatordemo.listener.MyOnClickListener;
import cn.edu.cqjtu.calculatordemo.listener.MyPopMenuItemClickListener;

public class MyLinearLayout {

	public static final int[] BTN_IDS = { R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5,
			R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_point, R.id.btn_sub, R.id.btn_add, R.id.btn_mul,
			R.id.btn_div, R.id.btn_convert, R.id.btn_back, R.id.btn_c, R.id.btn_ce, R.id.btn_equal };

	private LinearLayout layout = null;

	/**
	 * 用于存储Button
	 */
	@SuppressLint("UseSparseArrays")
	private Map<Integer, Button> buttons = new HashMap<Integer, Button>();

	private TextView tv_preResult;

	private TextView tv_currentResult;

	private Button btn_convert;

	private PopupMenu popupMenu;

	public MyLinearLayout(Activity activity, LinearLayout layout) {
		this.layout = layout;
		tv_preResult = (TextView) layout.findViewById(R.id.tv_preResult);
		tv_currentResult = (TextView) layout.findViewById(R.id.tv_currentResult);
		btn_convert = (Button) layout.findViewById(R.id.btn_convert);
		// 初始化popupMenu
		popupMenu = new PopupMenu(activity, btn_convert);
		tv_preResult.setOnClickListener(new MyOnClickListener(tv_currentResult));
		// 将R.menu.popup_menu添加到popupMenu的Menu上
		activity.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
		MyButtonListener listener = new MyButtonListener(activity, tv_currentResult, tv_preResult, popupMenu);
		for (int btn_id : BTN_IDS) {
			Button btn = (Button) layout.findViewById(btn_id);
			btn.setTextSize(25);
			btn.setOnClickListener(listener);
			btn.setTextColor(activity.getResources().getColor(R.color.btn_text_color));
			buttons.put(btn_id, btn);
		}
	}
}
