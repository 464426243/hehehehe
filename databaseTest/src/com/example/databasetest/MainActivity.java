package com.example.databasetest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

	private Button bt_create;
	private Button bt_select;
	private MyDatabaseHelper helper;
	
	public static int newversion1=4; 
	// 设置一个public变量记录当前版本号,以后如果有新需求就在这改版本号
//	public static int Nowversion;
//	
//	public  void saveVersion()
//	{
//		
//	}
//	
//	public void getNowversion()
//	{
//		
//	}
//	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         helper=new MyDatabaseHelper(MainActivity.this, "BookShop.db",null, 3);
        Log.i("yasd", Integer.toString(helper.mversion));
        bt_create=(Button) findViewById(R.id.button_create);
        bt_create.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			SQLiteDatabase db=	helper.getWritableDatabase();
			if(helper.mversion!=newversion1)
				helper.onUpgrade(db, helper.mversion,newversion1 );
			else Toast.makeText(MainActivity.this, "您的数据库已经是最新版本了", Toast.LENGTH_LONG).show();
			}
		});
        
        bt_select=(Button) findViewById(R.id.button_query);
        bt_select.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				SQLiteDatabase db=helper.getWritableDatabase();
				Cursor cursor=db.rawQuery("select * from Book", null);
				//必须用游标才能看到数据库
				if(cursor.moveToFirst())
					do {
						String name=cursor.getString(cursor.getColumnIndex("name"));
						String author=cursor.getString(cursor.getColumnIndex("author"));
						int pages=cursor.getInt(cursor.getColumnIndex("pages"));
						double price=cursor.getDouble(cursor.getColumnIndex("price"));
						Log.i("data", "name " +name);
						Log.i("data", "author " +author);
						Log.i("data", "pages " +pages);
						Log.i("data", "price " +price);
					} while (cursor.moveToNext());
			}
		});
      
    }


    
}
