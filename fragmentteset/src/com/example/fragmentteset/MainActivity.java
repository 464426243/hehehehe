package com.example.fragmentteset;

import com.example.fragmentteset.R;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button bt1;
	private Fragment_1 fragment_1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
	fragment_1=(Fragment_1) getFragmentManager().findFragmentById(R.id.fragement1);
		bt1=(Button) findViewById(R.id.button_auto);
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Log.d("yasd", "success");
				Fragment_3 fragment_3=new Fragment_3();//导入fragment碎片
				FragmentManager fragmentManager=getFragmentManager();//构造一个fragmentmanager，manager是动态导入fragment的关键。
				FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();//开启manager的事务
				
				fragmentTransaction.replace(R.id.LinearLayout_1, fragment_3);

				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				
			}
		});
		
		
	}

//	@Override
//	public void onClick(View arg0) {
//		// TODO Auto-generated method stub
//		switch (arg0.getId()) {
//		case R.id.button_auto:
//			//Log.d("yasd", "success");
//			Fragment_3 fragment_3=new Fragment_3();//导入fragment碎片
//			FragmentManager fragmentManager=getFragmentManager();//构造一个fragmentmanager，manager是动态导入fragment的关键。
//			FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();//开启manager的事务
//			fragmentTransaction.remove(fragment_1);
//			fragmentTransaction.replace(R.id.LinearLayout_1, fragment_3);
//			
//			fragmentTransaction.addToBackStack(null);
//			fragmentTransaction.commit();
//			
//			
//			break;
//
//		default:
//			break;
//		}
//	}

}
