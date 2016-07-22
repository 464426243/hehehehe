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
		// 因为当网络状态发生变化时，系统发出的正是一条值为 android.net.conn.CONNECTIVITY_
		// CHANGE 的广播，也就是说我们的广播接收器想要监听什么广播，就在这里添加相应的
		// action 就行了。
		networkChangeReceiver = new NetworkChangeReceiver();// 实例化这个BroatcastReceiver
		registerReceiver(networkChangeReceiver, filter);// 注册，说明它接收什么信息
		
		bt1=(Button) findViewById(R.id.Button1);
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent("com.example.receiver.MyBroadcastReceiver");
				//sendBroadcast(intent); //发送标准广播
				sendOrderedBroadcast(intent, null);//发送有序广播，第二个是优先级
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(networkChangeReceiver);//记得在destroy时取消注册BroatcastReceiver  

	}

	class NetworkChangeReceiver extends BroadcastReceiver {

		@Override
		// 表示当接收到广播时需干什么
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
