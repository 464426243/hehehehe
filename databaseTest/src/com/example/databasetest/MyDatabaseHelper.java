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

	// �ڶ������ݿ��ʱ�������Ӱ汾�Ų���Ϊpublic�������Բ��ܸ�ֵ����������
	// ��oncreate��onupgrade������Ϊ�Ѵ�������µ����ݿ⸳�汾��
	// �ֶ���ֵ���������汾��
	// �Ժ���Ŀ�������ר�Ŵ���һ��version table������Щversion

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
				+ "name text��"
				+ "category_id integer)");
		arg0.execSQL("create table Category (id integer autoincrment,"
				+ "category_name text,"
				+ "category_code integer)");
		arg0.execSQL("insert into Book(name,author,pages,price) values (?,?,?,?)",
				new String[]{ "The Da Vinci Code","Dan Brown","454","16.96"});
		//���û����ʷ���ݿ�����ֱ��create����������Ҫд��oncreate��
		mversion=MainActivity.newversion1;
//		java��ʹ��ȫ�ֱ���
//		1������һ��public�࣬����Ҫ�����������Ϊpublic static��
//		2.����������ͨ������.��������������
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

			// ע��ÿ��case����û��break��֤�Ǹ��ǰ�װ���������versionΪ1��������������
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
