package Notepad.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Notepad.Entites.Article;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	public SQLiteDatabase db;
	private DBOpenHelper helper;
	private Context context;
	
	private static String DB_NAME = "myDB.db";
	private static int DB_VERSION = 1;
	
	public static String DB_TABLE_IN_NAME = "myTable_in";
	public static String DB_TABLE_OUT_NAME = "myTable_out";
	public static String KEY_ID = "id";
	public static String KEY_NAME = "name";
	public static String KEY_CONTENT = "content";
	public static String KEY_DATE = "date";
	public DBAdapter(Context context) {
		this.context = context;
	}
	
	public void close() {
		if (db!=null) {
			db.close();
			db = null;
		}
	}
	public void open() {
		helper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
		try {
			db = helper.getWritableDatabase();
		} catch (SQLiteException e) {
			// TODO Auto-generated catch block
			db = helper.getReadableDatabase();
		}
	}
	
	
//	public void inserts_best(List<String> names) {
//		db.beginTransaction();
//		try {
//			for (String string : names) {
//				ContentValues values = new ContentValues();
//				values.put(KEY_NAME, string);
//				db.insert(DB_TABLE_NAME, null, values);
//				values.clear();
//			}
//			db.setTransactionSuccessful();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			Log.e("error", e.toString());
//			e.printStackTrace();
//		} finally {
//			db.endTransaction();
//		}
		
		
//	}
//	获得待入库条码
	public List<Article> GetAllPrepareInArticles() {
		List<Article> articles = new ArrayList<Article>();
		Cursor cursor = db.rawQuery("select id,name,content,date from myTable_in where content='0'", null);
		int cursorCounts = cursor.getCount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (cursorCounts==0&&!cursor.moveToFirst()) {
			return null;
		}
		for (int i = 0; i < cursorCounts; i++) {
			Article article = new Article();
			int id = cursor.getInt(0);
			String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
			String content = cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
			String date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
			article.setId(id);
			article.setName(name);
			article.setContent(content);
			try {
				article.setDate(sdf.parse(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				article.setDate(new Date());
				e.printStackTrace();
			}
			articles.add(article);
			cursor.moveToNext();
		}
		return articles;
	}
//	获得入库条码
	public List<Article> GetAllInArticles() {
		List<Article> articles = new ArrayList<Article>();
		Cursor cursor = db.rawQuery("select id,name,content,date from myTable_in where content='1'", null);
		int cursorCounts = cursor.getCount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (cursorCounts==0&&!cursor.moveToFirst()) {
			return null;
		}
		for (int i = 0; i < cursorCounts; i++) {
			Article article = new Article();
			int id = cursor.getInt(0);
			String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
			String content = cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
			String date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
			article.setId(id);
			article.setName(name);
			article.setContent(content);
			try {
				article.setDate(sdf.parse(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				article.setDate(new Date());
				e.printStackTrace();
			}
			articles.add(article);
			cursor.moveToNext();
		}
		return articles;
	}
//	获得已上传入库条码
	public List<Article> GetAllInUpdateArticles() {
		List<Article> articles = new ArrayList<Article>();
		Cursor cursor = db.rawQuery("select id,name,content,date from myTable_in where content = '3'", null);
		int cursorCounts = cursor.getCount();
		if (cursorCounts==0&&!cursor.moveToFirst()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (int i = 0; i < cursorCounts; i++) {
			Article article = new Article();
			int id = cursor.getInt(0);
			String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
			String content = cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
			String date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
			article.setId(id);
			article.setName(name);
			article.setContent(content);
			try {
				article.setDate(sdf.parse(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				article.setDate(new Date());
				e.printStackTrace();
			}
			articles.add(article);
		}
		return articles;
		
	}
//	获得待出库条码
	public List<Article> GetAllPrepareOutArticles() {
		List<Article> articles = new ArrayList<Article>();
		Cursor cursor = db.rawQuery("select id,name,content,date from myTable_out where content='0'", null);
		int cursorCounts = cursor.getCount();
		if (cursorCounts==0&&!cursor.moveToFirst()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (int i = 0; i < cursorCounts; i++) {
			Article article = new Article();
			int id = cursor.getInt(0);
			String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
			String content = cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
			String date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
			article.setId(id);
			article.setName(name);
			article.setContent(content);
			try {
				article.setDate(sdf.parse(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				article.setDate(new Date());
				e.printStackTrace();
			}
			articles.add(article);
		}
		return articles;
	}
//	获得出库条码
	public List<Article> GetAllOutArticles() {
		List<Article> articles = new ArrayList<Article>();
		Cursor cursor = db.rawQuery("select id,name,content,date from myTable_out where content='1'", null);
		int cursorCounts = cursor.getCount();
		if (cursorCounts==0&&!cursor.moveToFirst()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (int i = 0; i < cursorCounts; i++) {
			Article article = new Article();
			int id = cursor.getInt(0);
			String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
			String content = cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
			String date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
			article.setId(id);
			article.setName(name);
			article.setContent(content);
			try {
				article.setDate(sdf.parse(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				article.setDate(new Date());
				e.printStackTrace();
			}
			articles.add(article);
		}
		return articles;
		
	}
//	获得已上传出库条码
	public List<Article> GetAllOutUpdateArticles() {
		List<Article> articles = new ArrayList<Article>();
		Cursor cursor = db.rawQuery("select id,name,content,date from myTable_out where content = '3'", null);
		int cursorCounts = cursor.getCount();
		if (cursorCounts==0&&!cursor.moveToFirst()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (int i = 0; i < cursorCounts; i++) {
			Article article = new Article();
			int id = cursor.getInt(0);
			String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
			String content = cursor.getString(cursor.getColumnIndex(KEY_CONTENT));
			String date = cursor.getString(cursor.getColumnIndex(KEY_DATE));
			article.setId(id);
			article.setName(name);
			article.setContent(content);
			try {
				article.setDate(sdf.parse(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				article.setDate(new Date());
				e.printStackTrace();
			}
			articles.add(article);
		}
		return articles;
		
	}
//	添加待入库条码
	public void addPrepareInArticle(String[] names) {
		ContentValues values = new ContentValues();
		db.beginTransaction();
		try {
			for (String string : names) {
				values.put(KEY_NAME, string);
				values.put(KEY_CONTENT, "0");
				db.insert(DB_TABLE_IN_NAME, null, values);
				values.clear();
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
		
	}
//	添加入库条码
	public void addInArticle(String[] names) {
		ContentValues values = new ContentValues();
		db.beginTransaction();
		try {
			for (String string : names) {
				values.put(KEY_CONTENT, "1");
				db.update(DB_TABLE_IN_NAME, values, KEY_NAME+"="+string, null);
				values.clear();
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
		
	}
//	添加待出库条码
	public void addPrepareOutArticle(String[] names) {
		ContentValues values = new ContentValues();
		db.beginTransaction();
		try {
			for (String string : names) {
				values.put(KEY_NAME, string);
				values.put(KEY_CONTENT, "0");
				db.insert(DB_TABLE_OUT_NAME, null, values);
				values.clear();
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
		
	}
//	添加出库条码
	public void addOutArticle(String[] names) {
		ContentValues values = new ContentValues();
		db.beginTransaction();
		try {
			for (String string : names) {
				values.put(KEY_CONTENT, "1");
				db.update(DB_TABLE_OUT_NAME, values, KEY_NAME+"="+string, null);
				values.clear();
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
		
	}
//	校检入库条码
	public void checkInArticle(String[] names) {
		ContentValues values = new ContentValues();
		db.beginTransaction();
		try {
			for (String string : names) {
				if (string!=null) {
					values.put(KEY_CONTENT, "2");
					db.update(DB_TABLE_IN_NAME, values, KEY_NAME+"="+string, null);
				}	
				values.clear();
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}
//	校检出库条码
	public void checkOutArticle(String[] names) {
		ContentValues values = new ContentValues();
		db.beginTransaction();
		try {
			for (String string : names) {
				if (string!=null) {
					values.put(KEY_CONTENT, "2");
					db.update(DB_TABLE_OUT_NAME, values, KEY_NAME+"="+string, null);
				}	
				values.clear();
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}
	
	
	public String[] GetAllInArticles_Names() {
		List<Article> articles = GetAllInArticles();
		String[] in_names = new String[articles.size()];
		for (int i = 0; i < articles.size(); i++) {
			String content = articles.get(i).getContent();
			in_names[i] = content;
		}
		return in_names;
	}
	public String[] validateArticle(String[] names) {
		String[] in_names = GetAllInArticles_Names();
		String[] out_names = new String[in_names.length];
		for (int i = 0; i < names.length; i++) {
			for (int j = 0; j < in_names.length; j++) {
				if (in_names[j].equals(names[j])) {
					out_names[j] = names[j];
					break;
				}
			}
		}
		return out_names;
	}
//	public boolean echo(String[] names,String name) {
//		for (int i = 0; i < names.length; i++) {
//			if (names[0].equals(name)) {
//				return false;
//			}
//		}
//		return true;
//		
//	}
	
	private void ConvertToPeople(Cursor results) {
		// TODO Auto-generated method stub
		int resultCounts = results.getCount();
		if (resultCounts==0&&!results.moveToFirst()) {
			return;
		}
		String[] str = new String[resultCounts];
		for (int i = 0; i < resultCounts; i++) {
			str[i] = results.getString(i);
			results.moveToNext();
		}
	}

	public void deleteForIn(String name) {
		db.delete(DB_TABLE_IN_NAME, KEY_NAME+"="+name, null);
	}
	public void deleteForOut(String name) {
		db.delete(DB_TABLE_OUT_NAME, KEY_NAME+"="+name, null);
	}
	
//	public void updates(List<String> names) {
//		ContentValues values = new ContentValues();
//		for (int i = 0; i < names.size(); i++) {
//			values.put(KEY_NAME, names.get(i).toString());
//			db.update(DB_TABLE_NAME, values, KEY_NAME+"="+names.get(i).toString(), null);
//			values.clear();
//		}
//	}
	private static class DBOpenHelper extends SQLiteOpenHelper {

		public DBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		private static final String CREATE_TABLE_IN = "create table "+ DB_TABLE_IN_NAME +" ("+ KEY_ID +" integer primary key autoincrement, "+
		KEY_NAME +" nvarchar(40) not null, "+ KEY_CONTENT +" text default '', "+ KEY_DATE +" nvarchar(40) not null)";
		private static final String CREATE_TABLE_OUT = "create table "+ DB_TABLE_OUT_NAME +" ("+ KEY_ID +" integer primary key autoincrement, "+
				KEY_NAME +" nvarchar(40) not null, "+ KEY_CONTENT +" text default '', "+ KEY_DATE +" nvarchar(40) not null)";
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(CREATE_TABLE_IN);
			db.execSQL(CREATE_TABLE_OUT);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}

	
		
	}
}
