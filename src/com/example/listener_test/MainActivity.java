package com.example.listener_test;
/**
 * 
 * 本案例集合触摸屏事件响应 和 状态栏上通知的显示方式
 * 
 * */
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener{

	private Button showNote = null;
	private Button deleteNote = null;
	private final int NOTIFYID_1 = 0x123;
	private final int NOTIFYID_2 = 0x124;
	//对于需要进行计算才能得到的数值，不要在定义时赋值，以免出现问题
	private NotificationManager noteManager = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		showNote = (Button)findViewById(R.id.show);
		deleteNote = (Button)findViewById(R.id.delete);
		LinearLayout l = (LinearLayout)findViewById(R.id.all);
		l.setOnTouchListener(this);
		try {
			noteManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		} catch (Exception e) {
			Toast.makeText(this, "2324", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		showNote.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				
				/**
				 * 
				 * 编辑通知的第一个方式
				 * 
				 * */
				
				Notification note = new Notification();
				note.icon = R.drawable.advise;
				note.tickerText = "显示第一个通知";
				//设置发送时间
				note.when = System.currentTimeMillis();
				//设置默认声音、震动、闪光灯
				//此时需要去添加权限
				note.defaults = Notification.DEFAULT_ALL;
				//设置事件信息
				note.setLatestEventInfo(MainActivity.this, "无题", "day day up", null);
				//通过通知管理器发送通知
				noteManager.notify(NOTIFYID_1,note);
				
				/**
				 * 
				 * 编辑通知的第二个方式
				 * 
				 * */
				Notification note2 = new Notification(R.drawable.advise2,"显示第二个通知",System.currentTimeMillis());
				//打开应用程序后，图标消失
				note2.flags = Notification.FLAG_AUTO_CANCEL;
				Intent intent = new Intent(MainActivity.this,ContentActivity.class);
				PendingIntent p = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
				note2.setLatestEventInfo(MainActivity.this, "通知", "查看详细内容", p);
				noteManager.notify(NOTIFYID_2,note2);
			}
		});
		deleteNote.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//清楚全部的通知
				noteManager.cancelAll();
			}
		});
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Toast.makeText(this, "被触摸; x：" + event.getX() + "   y:" + event.getY(), Toast.LENGTH_SHORT).show();
		return false;
	}

}
