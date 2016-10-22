package cn.edu.cqjtu.calculatordemo.listener;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.edu.cqjtu.calculatordemo.R;
import cn.edu.cqjtu.calculatordemo.util.ConvertUtil;

public class MyOnClickListener implements OnClickListener {

	private TextView tv_result;

	public MyOnClickListener(TextView tv_result) {
		this.tv_result = tv_result;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.tv_preResult) {
			TextView preResult = (TextView) v;
			String result = (String) preResult.getText();
			if (result == null || result.isEmpty()) {
				return;
			}
			// 如果不为空则进行截取操作，然后设置到下一个TextView上
			result = ConvertUtil.getStrBeforeEqualSign(result);
			if (result == null) {
				return;
			}
			tv_result.setText(result);
		}
	}
}
