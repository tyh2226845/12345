package Notepad.UI;

import java.util.Date;
import java.util.List;

import Notepad.DAO.ArticleDAO;
import Notepad.DAO.SQLiteHelper;
import Notepad.Entites.Article;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InOutActivity extends BaseActivity {
	EditText txtTitle;
	EditText txtContent;
	Article article;
	String result;
	String str;
	String code;
	SQLiteDatabase db;
	String returnNum;
	boolean flag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inout);

		this.setTitle("条码检测");
		txtTitle = (EditText) findViewById(R.id.txtTitle);
		txtContent = (EditText) findViewById(R.id.txtContent);
		Button btnSave = (Button) findViewById(R.id.btnSave);
		Button btn1 = (Button) findViewById(R.id.button1);
		if (result == null) {
			result = "";
		}

		// returnNum = article.getName().trim();
		// Toast.makeText(getApplicationContext(), returnNum + "保存成功！",
		// Toast.LENGTH_LONG).show();

		btnSave.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				btnSave_click(v);
			}
		});
		btn1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					result += txtTitle.getText().toString().trim()
							+ "          /" + "\n";
					txtContent.setText(result);
					str = txtContent.getText().toString().trim();
					txtTitle.setText("");
				} catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(getApplicationContext(), e + "保存成功！",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	String nullcode = "";

	public void btnSave_click(View view) {
		Intent intent;

		try {
			String[] part = str.split("/");
			for (int i = 0; i < part.length; i++) {
				SQLiteHelper sqLiteHelper = new SQLiteHelper(
						getApplicationContext());
				db = sqLiteHelper.getReadableDatabase();
				ArticleDAO articleDao = new ArticleDAO();
				article = articleDao.GetMyWay(db, part[i].trim());
				nullcode = part[i].trim();
				code = article.getName();
				article.setContent("1");
				article.setDate(new Date());
				SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
				articleDao.updateMyWay(db, article, code);

			}
			Toast.makeText(getApplicationContext(), "校验成功！", Toast.LENGTH_LONG)
					.show();
			intent = new Intent(this, FirstActivity.class);
			startActivity(intent);
			this.finish();
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), nullcode + "条码不存在！",
					Toast.LENGTH_LONG).show();
			txtContent.setText("");
			result=nullcode="";
		}

	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			backToIndex();
			return false;
		}
		
		return super.onKeyDown(keyCode, event);
	}

	private void backToIndex() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, FirstActivity.class);
		startActivity(intent);
		finish();
	}
}
// // article.setName(txtTitle.getText() + "");
// try {
// article.setName(txtTitle.getText() + "");
// article.setContent(txtContent.getText() + "");
// article.setDate(new Date());
// // 执行更新动作
// SQLiteHelper sqLiteHelper = new SQLiteHelper(getBaseContext());
// SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
// ArticleDAO articleDao = new ArticleDAO();
// articleDao.UpdateArticle(db, article);
// Toast.makeText(getBaseContext(), "保存成功！", Toast.LENGTH_LONG).show();
//
//
// // SQLiteHelper sqLiteHelper = new SQLiteHelper(
// // getApplicationContext());
// // SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
// //
// // ArticleDAO articleDao = new ArticleDAO();
// // articleDao.updateMyWay(db, code);
//
// // article.setName(txtTitle.getText() + "");
// // article.setContent(txtContent.getText() + "");
// //// article.setContent("1");
// // article.setDate(new Date());
// //
// // // 执行更新动作
// // SQLiteHelper sqLiteHelper = new SQLiteHelper(getApplicationContext());
// // SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
// //
// // ArticleDAO articleDao = new ArticleDAO();
// // articleDao.UpdateArticle(db, article);
// //
// // Toast.makeText(getApplicationContext(), "保存成功！",
// Toast.LENGTH_LONG).show();
//
//
// // Toast.makeText(getApplicationContext(), "删除成功!", Toast.LENGTH_SHORT)
// // .show();
// } catch (Exception e) {
// // TODO: handle exception
// Toast.makeText(getApplicationContext(), e+"", Toast.LENGTH_LONG).show();
// }
// //
// // Intent intent = new Intent(getApplicationContext(),
// // FirstActivity.class);
// // getApplicationContext().startActivity(intent);
// } 