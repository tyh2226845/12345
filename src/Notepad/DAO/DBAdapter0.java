package Notepad.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter0 {

	private final static int VERSION = 1;
	private final static String DB_NAME = "myDB.db";
	private Context context;
	DBOpenHelper helper;
	SQLiteDatabase db;
	
	public DBAdapter0(Context context) {
		this.context = context;
	}
	public void open() throws SQLiteException{
		helper = new DBOpenHelper(context, DB_NAME, null, VERSION);
		try {
			db = helper.getWritableDatabase();
		} catch (SQLiteException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			db = helper.getReadableDatabase();
		}
	}
	public void close() {
		if (db!=null) {
			db.close();
			db = null;
		}
	}
	
	private static class DBOpenHelper extends SQLiteOpenHelper {

		public DBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
