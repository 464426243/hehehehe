package com.example.sasad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	String tag="ceshi";
	private Button bt1,bt2;
	Intent intent1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt1=(Button) findViewById(R.id.button1);
		bt2=(Button) findViewById(R.id.button2);
		
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent1=new Intent(MainActivity.this,activity_1.class);
				startActivity(intent1);
			}
		});
		
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent1=new Intent(MainActivity.this,dialog.class);
				startActivity(intent1);
			}
		});
		
		Log.d(tag, "oncreate");
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		super.startActivityForResult(intent, requestCode);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();Log.d(tag, "onstart");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();Log.d(tag, "onresume");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();Log.d(tag, "onpause");
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();Log.d(tag, "onrestart");
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();Log.d(tag, "onstop");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();Log.d(tag, "ondestroy");
	}

}
