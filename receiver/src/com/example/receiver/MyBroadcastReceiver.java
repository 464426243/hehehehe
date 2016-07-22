package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		Toast.makeText(arg0, "received in my broadcastReceiver", Toast.LENGTH_LONG).show();
	abortBroadcast();//×è¶ÏÓÐÐò¹ã²¥
	}

}
