package Notepad.UI;

import java.util.Date;

import Notepad.DAO.ArticleDAO;
import Notepad.DAO.SQLiteHelper;
import Notepad.Entites.Article;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends BaseActivity {
	EditText txtTitle;
	EditText txtContent;
	Article article;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_add);

		this.setTitle("我的日记 - 编辑");

		int articleId = getIntent().getExtras().getInt("id");
		SQLiteHelper helper = new SQLiteHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase(); 
		 ArticleDAO articleDao = new ArticleDAO(); 
		 article = articleDao.GetArticleById(db, articleId);
//			Toast.makeText(getApplicationContext(), articleId+"保存成功！", Toast.LENGTH_LONG)
//			.show();
		 
		txtTitle = (EditText) findViewById(R.id.txtTitle);
		txtContent = (EditText) findViewById(R.id.txtContent);
		Button btnSave = (Button) findViewById(R.id.btnSave);
		
		 txtTitle.setText(article.getName());
		 txtContent.setText(article.getContent());

		btnSave.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				btnSave_click(v);
			}
		});
	}

	public void btnSave_click(View view) {
		try {

			article.setName(txtTitle.getText() + "");
			article.setContent(txtContent.getText() + "");
			article.setDate(new Date());

			// 执行更新动作
			SQLiteHelper sqLiteHelper = new SQLiteHelper(
					getApplicationContext());
			SQLiteDatabase db = sqLiteHelper.getWritableDatabase();

			ArticleDAO articleDao = new ArticleDAO();
			articleDao.UpdateArticle(db, article);

			Toast.makeText(getApplicationContext(), "保存成功！", Toast.LENGTH_LONG)
					.show();

		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG)
					.show();
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
