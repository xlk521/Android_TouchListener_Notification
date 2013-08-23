package com.example.listener_test;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * 添加触屏事件响应
		 * 此处的设置并没有涉及到页面布局，而是自行定义了一个
		 * 
		 * **/
		LinearLayout l = new LinearLayout(this);
		l.setOnTouchListener(this);
		l.setBackgroundResource(R.drawable.myself);
		
		setContentView(l);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Toast.makeText(this, "被触摸; x：" + event.getX() + "   y:" + event.getY(), Toast.LENGTH_SHORT).show();
		return false;
	}

}
