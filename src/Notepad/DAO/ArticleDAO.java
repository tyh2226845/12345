package Notepad.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Notepad.Entites.Article;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ArticleDAO {

	/**
	 * 查询所有文章
	 * 
	 * @param db
	 * @return
	 */
	public  List<Article> GetAllArticles(SQLiteDatabase db) {
		List<Article> aritlces = new ArrayList<Article>();
//		判断数据库是否为空
		if (db.isOpen()) {
			try {
				Cursor cursor = db
						.rawQuery(
								"select id,name,content,date from articles where content ='0'",
								null);
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				while (cursor.moveToNext()) {
					Article entity = new Article();
					entity.setId(cursor.getInt(0));
					entity.setName(cursor.getString(cursor
							.getColumnIndex("name")));
					entity.setContent(cursor.getString(cursor
							.getColumnIndex("content")));
					String strdate = cursor.getString(cursor
							.getColumnIndex("date"));
					try {
						entity.setDate(sdf.parse(strdate));
					} catch (ParseException e) {
						entity.setDate(new Date());
						e.printStackTrace();
					}
					aritlces.add(entity);
				}
			} finally {
//				db.close();
			}
			return aritlces;
		}
		return null;
	}

	public List<Article> GetAllArticles1(SQLiteDatabase db) {
		List<Article> aritlces = new ArrayList<Article>();

		if (db.isOpen()) {
			try {
				Cursor cursor = db
						.rawQuery(
								"select id,name,content,date from articles where content ='1'",
								null);
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				while (cursor.moveToNext()) {
					Article entity = new Article();
					entity.setId(cursor.getInt(0));
					entity.setName(cursor.getString(cursor
							.getColumnIndex("name")));
					entity.setContent(cursor.getString(cursor
							.getColumnIndex("content")));

					String strdate = cursor.getString(cursor
							.getColumnIndex("date"));
					try {
						entity.setDate(sdf.parse(strdate));
					} catch (ParseException e) {
						entity.setDate(new Date());
						e.printStackTrace();
					}
					aritlces.add(entity);
				}
			} finally {
//				db.close();
			}
			return aritlces;
		}
		return null;
	}

	public Article GetMyWay(SQLiteDatabase db, String code) {
		Article entity = null;
		if (db.isOpen()) {
			try {
				Cursor cursor = db
						.rawQuery(
								"select id,name,content,date from articles where name = ?",
								new String[] { code });
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				if (cursor.moveToNext()) {
					entity = new Article();
					entity.setId(cursor.getInt(0));
					entity.setName(cursor.getString(cursor
							.getColumnIndex("name")));
					entity.setContent(cursor.getString(cursor
							.getColumnIndex("content")));

					String strdate = cursor.getString(cursor
							.getColumnIndex("date"));
					try {
						entity.setDate(sdf.parse(strdate));
					} catch (ParseException e) {
						entity.setDate(new Date());
						e.printStackTrace();
					}
				}
			} finally {
//				db.close();
			}
			return entity;
		}
		return entity;
	}

	/**
	 * 查询文章通过编号
	 * 
	 * @param db
	 * @return
	 */
	public Article GetArticleById(SQLiteDatabase db, int articleId) {
		Article entity = null;
		if (db.isOpen()) {
			try {
				Cursor cursor = db.rawQuery(
						"select id,name,content,date from articles where id=?",
						new String[] { articleId + "" });
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				if (cursor.moveToNext()) {
					entity = new Article();
					entity.setId(cursor.getInt(0));
					entity.setName(cursor.getString(cursor
							.getColumnIndex("name")));
					entity.setContent(cursor.getString(cursor
							.getColumnIndex("content")));

					String strdate = cursor.getString(cursor
							.getColumnIndex("date"));
					try {
						entity.setDate(sdf.parse(strdate));
					} catch (ParseException e) {
						entity.setDate(new Date());
						e.printStackTrace();
					}
				}
			} finally {
//				db.close();
			}
			return entity;
		}
		return entity;
	}

	/**
	 * 添加新的文章
	 * 
	 * @param entity
	 */
	public void AddArticle(SQLiteDatabase db, Article entity) {
		if (db.isOpen()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				db.execSQL(
						"insert into articles(name,content,date) values(?,?,?)",
						new Object[] { entity.getName(), entity.getContent(),
								sdf.format(entity.getDate()) });
			} finally {
//				db.close();
			}
		}
	}

	/**
	 * 删除文章通过编号
	 * 
	 * @param entity
	 */
	public void DeleteArticleByKey(SQLiteDatabase db, int id) {
		if (db.isOpen()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				db.execSQL("delete from articles where id=?",
						new Object[] { id });
			} finally {
//				db.close();
			}
		}
	}

	// public void updateMyWay(SQLiteDatabase db, String name) {
	// if (db.isOpen()) {
	// try {
	// db.execSQL(
	// "update articles set content=1,date=getDate() where name=?",
	// new Object[] { name });
	// } finally {
	// db.close();
	// }
	// }
	// }

	/**
	 * 更新文章
	 * 
	 * @param entity
	 */
	public void updateMyWay(SQLiteDatabase db, Article entity, String Code) {
		if (db.isOpen()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				db.execSQL(
						"update articles set content=?,date=? where name=?",
						new Object[] { entity.getContent(),
								sdf.format(entity.getDate()), Code });
			} finally {
//				db.close();
			}
		}
	}

	public void UpdateArticle(SQLiteDatabase db, Article entity) {
		if (db.isOpen()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				db.execSQL(
						"update articles set name=?,content=?,date=? where id=?",
						new Object[] { entity.getName(), entity.getContent(),
								sdf.format(entity.getDate()), entity.getId() });
			} finally {
//				db.close();
			}
		}
	}

	public void UpdateALL(SQLiteDatabase db, Article entity) {
		if (db.isOpen()) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				db.execSQL("update articles set content=? where content = '1'",
						new Object[] { entity.getContent() });
			} finally {
//				db.close();
			}
		}
	}

}
