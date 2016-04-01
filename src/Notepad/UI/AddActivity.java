package Notepad.UI;

import java.util.Date;

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
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends BaseActivity {
	EditText txtTitle;
	EditText txtContent;
	String result;
	String str;
	boolean flag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		txtTitle = (EditText) findViewById(R.id.txtTitle);
		txtContent = (EditText) findViewById(R.id.txtContent);
		Button btnSave = (Button) findViewById(R.id.btnSave);
		Button btn1 = (Button) findViewById(R.id.button1);

		if (result == null) {
			result = "";
		}

		btnSave.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!flag) {
					Toast.makeText(AddActivity.this, "内容不正确,请重新扫码！", 1000).show();
					return;
				}
				btnSave_click(v);
			}
		});
		btn1.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {

				result += txtTitle.getText() + "          /" + "\n";
				txtContent.setText(result);
				str = txtContent.getText().toString().trim();
				Log.i("str", str+"");
				txtTitle.setText("");
				flag = true;

			}
		});
	}

	public void btnSave_click(View view) {
		Intent intent;
		String[] part = str.split("/");
		Log.i("part", part.length+""+part[0]);
		// 要添加到数据库中的实体
		for (int i = 0; i < part.length; i++) {
			Article article = new Article();
			article.setName(part[i].trim() + "");
			article.setContent("0");
			article.setDate(new Date());
			// article.setContent(txtContent.getText() + "");
			// 执行添加动作
			SQLiteHelper sqLiteHelper = new SQLiteHelper(
					getApplicationContext());
			SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
			ArticleDAO articleDao = new ArticleDAO();
			articleDao.AddArticle(db, article);
		}
		Toast.makeText(getApplicationContext(), "添加完成", Toast.LENGTH_LONG)
				.show();
		intent = new Intent(this, FirstActivity.class);
		startActivity(intent);
		this.finish();
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
