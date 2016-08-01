package com.example.databasetest;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.AvoidXfermode.Mode;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	private Context mContext;

	public static int mversion = 3;

	// 在定义数据库的时候必须添加版本号并设为public，但绝对不能赋值！！！！！
	// 在oncreate和onupgrade函数中为已创建或更新的数据库赋版本号
	// 手动赋值现在所处版本号
	// 以后项目扩大可以专门创建一个version table保存这些version

	public MyDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL("create table Book(id integer primary key autoincrement,"
				+ "author text,"
				+ "price real,"
				+ "pages integer,"
				+ "name text，"
				+ "category_id integer)");
		arg0.execSQL("create table Category (id integer autoincrment,"
				+ "category_name text,"
				+ "category_code integer)");
		arg0.execSQL("insert into Book(name,author,pages,price) values (?,?,?,?)",
				new String[]{ "The Da Vinci Code","Dan Brown","454","16.96"});
		//如果没有历史数据库则需直接create所以两个表都要写在oncreate中
		mversion=MainActivity.newversion1;
//		java中使用全局变量
//		1。创建一个public类，并将要定义的类声明为public static；
//		2.在其他类中通过类名.变量名访问数据
		Toast.makeText(mContext, "success to create "+getDatabaseName(), Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldversion, int newversion) {
		// TODO Auto-generated method stub
		switch (oldversion) {
		case 1:
			arg0.execSQL("if not exsits Category { ");
			arg0.execSQL("create table Category (id integer autoincrment,"
					+ "category_name text," + "category_code integer)}");
		case 2:
			arg0.execSQL("alter table Book add column category_id integer");

			// 注意每个case后面没有break保证是覆盖安装，即如果是version为1则这两步都得走
		case 3:
			arg0.execSQL("insert into Book(name,author,pages,price) values (?,?,?,?)",
					new String[]{ "The Da Vinci Code","Dan Brown","454","16.96"});
			arg0.execSQL("insert into Book(name,author,pages,price) values(?,?,?,?)",
					new String[]{"The Lost Symbol", "Dan Brown", "510", "19.95"});
		default:
			break;

		}
		Toast.makeText(mContext, "success upgrade", Toast.LENGTH_LONG).show();
		mversion = MainActivity.newversion1;

	}

}
