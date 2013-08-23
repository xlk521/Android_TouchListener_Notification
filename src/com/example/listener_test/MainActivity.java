package com.example.listener_test;
/**
 * 
 * ���������ϴ������¼���Ӧ �� ״̬����֪ͨ����ʾ��ʽ
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
	//������Ҫ���м�����ܵõ�����ֵ����Ҫ�ڶ���ʱ��ֵ�������������
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
				 * �༭֪ͨ�ĵ�һ����ʽ
				 * 
				 * */
				
				Notification note = new Notification();
				note.icon = R.drawable.advise;
				note.tickerText = "��ʾ��һ��֪ͨ";
				//���÷���ʱ��
				note.when = System.currentTimeMillis();
				//����Ĭ���������𶯡������
				//��ʱ��Ҫȥ���Ȩ��
				note.defaults = Notification.DEFAULT_ALL;
				//�����¼���Ϣ
				note.setLatestEventInfo(MainActivity.this, "����", "day day up", null);
				//ͨ��֪ͨ����������֪ͨ
				noteManager.notify(NOTIFYID_1,note);
				
				/**
				 * 
				 * �༭֪ͨ�ĵڶ�����ʽ
				 * 
				 * */
				Notification note2 = new Notification(R.drawable.advise2,"��ʾ�ڶ���֪ͨ",System.currentTimeMillis());
				//��Ӧ�ó����ͼ����ʧ
				note2.flags = Notification.FLAG_AUTO_CANCEL;
				Intent intent = new Intent(MainActivity.this,ContentActivity.class);
				PendingIntent p = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
				note2.setLatestEventInfo(MainActivity.this, "֪ͨ", "�鿴��ϸ����", p);
				noteManager.notify(NOTIFYID_2,note2);
			}
		});
		deleteNote.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//���ȫ����֪ͨ
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
		Toast.makeText(this, "������; x��" + event.getX() + "   y:" + event.getY(), Toast.LENGTH_SHORT).show();
		return false;
	}

}
