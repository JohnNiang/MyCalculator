package cn.edu.cqjtu.calculatordemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import cn.edu.cqjtu.calculatordemo.view.MyLinearLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		LinearLayout linear_layout = (LinearLayout) findViewById(R.id.linear_layout);
		new MyLinearLayout(this, linear_layout);
	}
}
