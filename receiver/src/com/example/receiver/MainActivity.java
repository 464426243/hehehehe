package com.example.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private IntentFilter filter;
	private NetworkChangeReceiver networkChangeReceiver;
private Button bt1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		filter = new IntentFilter();
		filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		filter.addAction("android.intent.action.SERVICE_STATE");
		// ��Ϊ������״̬�����仯ʱ��ϵͳ����������һ��ֵΪ android.net.conn.CONNECTIVITY_
		// CHANGE �Ĺ㲥��Ҳ����˵���ǵĹ㲥��������Ҫ����ʲô�㲥���������������Ӧ��
		// action �����ˡ�
		networkChangeReceiver = new NetworkChangeReceiver();// ʵ�������BroatcastReceiver
		registerReceiver(networkChangeReceiver, filter);// ע�ᣬ˵��������ʲô��Ϣ
		
		bt1=(Button) findViewById(R.id.Button1);
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent("com.example.receiver.MyBroadcastReceiver");
				//sendBroadcast(intent); //���ͱ�׼�㲥
				sendOrderedBroadcast(intent, null);//��������㲥���ڶ��������ȼ�
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(networkChangeReceiver);//�ǵ���destroyʱȡ��ע��BroatcastReceiver  

	}

	class NetworkChangeReceiver extends BroadcastReceiver {

		@Override
		// ��ʾ�����յ��㲥ʱ���ʲô
		public void onReceive(Context context, Intent arg1) {
			Toast.makeText(MainActivity.this, "123451",
					Toast.LENGTH_SHORT).show();
			// TODO Auto-generated method stub
			ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = connectionManager.getActiveNetworkInfo();
			if (info != null && info.isAvailable())
				Toast.makeText(MainActivity.this, "network is available",
						Toast.LENGTH_LONG).show();
			else
				Toast.makeText(MainActivity.this, "network is not available",
						Toast.LENGTH_LONG).show();

		}
		
	}
}
