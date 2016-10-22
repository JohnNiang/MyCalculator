package cn.edu.cqjtu.calculatordemo.listener;

import android.content.Context;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.cqjtu.calculatordemo.R;
import cn.edu.cqjtu.calculatordemo.util.ConvertUtil;

/**
 * 自定义popmenu的点击事件监听器
 * 
 * @author JohnNiang
 *
 */
public class MyPopMenuItemClickListener implements OnMenuItemClickListener {

	private PopupMenu popupMenu;
	private TextView tv_result;
	private Context context;
	private MyButtonListener buttonListener;

	public MyPopMenuItemClickListener(Context context, PopupMenu popupMenu, TextView tv_reseult,
			MyButtonListener buttonListener) {
		this.context = context;
		this.popupMenu = popupMenu;
		this.tv_result = tv_reseult;
		this.buttonListener = buttonListener;
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		if (tv_result.getText().toString().isEmpty()) {
			return false;
		}
		try {
			switch (item.getItemId()) {
			case R.id.dec2bin:
				tv_result.setText(ConvertUtil.decimal2Binary(tv_result.getText().toString()));
				break;
			case R.id.dec2hex:
				tv_result.setText(ConvertUtil.decimal2Hex(tv_result.getText().toString()));
				break;
			}
			buttonListener.converted = true;
			buttonListener.computed = true;
			popupMenu.dismiss();
			return true;
		} catch (NumberFormatException e) {
			Toast.makeText(context, "该数据不能转换", Toast.LENGTH_SHORT).show();
		}
		return false;
	}

}
