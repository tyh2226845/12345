package Notepad.UI;

import java.util.Date;
import java.util.List;

import Notepad.DAO.ArticleDAO;
import Notepad.DAO.SQLiteHelper;
import Notepad.Entites.Article;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
//import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

public class FirstActivity extends Activity implements OnClickListener {

	private boolean flag = true;
	private ListView listArticles;
	private List<Article> articles;
	private ImageButton ib_indexButton,ib_addButton,ib_inOutButton,ib_uploadButton,ib_exitButton;
	private Dialog dialog;
	private long firstTime = 0;
	private ListViewAdapter_2 adapter;
	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase db;
	private ArticleDAO articleDao;
//	private Handler handler = new Handler(){
//		public void handleMessage(android.os.Message msg) {
//			if (msg.what == 1 ) {
//				articles = (List<Article>) msg.obj;
//				Log.i("handle:", articles.size()+"");
//				adapter.onDataChange(articles);
//			}
//			
//		};
//	};
	@SuppressWarnings("null")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		initView();	
		initData();
//		showList();
	}

	private void showList() {
		if (adapter!=null) {
					// TODO Auto-generated method stub
//							Article article = new Article(1994, "tyh", "0", new Date());
							articles = articleDao.GetAllArticles(db);
							Log.i("showlist:", articles.size()+"");
							adapter.onDataChange(articles);						
//							articles.add(article);
//							Message msg = handler.obtainMessage();
//							msg.obj = articles;
//							msg.what = 1;
//							handler.sendMessage(msg);	
		}
		// TODO Auto-generated method stub
		
	}

	private void initData() {
		// TODO Auto-generated method stub
		ib_addButton.setOnClickListener(this);
		ib_indexButton.setOnClickListener(this);
		ib_inOutButton.setOnClickListener(this);
		ib_uploadButton.setOnClickListener(this);
		ib_exitButton.setOnClickListener(this);
	}

	private void initView() {
		// TODO Auto-generated method stub
		this.setTitle("条码管理");
		ib_indexButton = (ImageButton) findViewById(R.id.ib_index);
		ib_addButton = (ImageButton) findViewById(R.id.ib_add);
		ib_inOutButton = (ImageButton) findViewById(R.id.ib_inOut);
		ib_uploadButton = (ImageButton) findViewById(R.id.ib_upload);
		ib_exitButton = (ImageButton) findViewById(R.id.ib_exit);
		listArticles = (ListView) findViewById(R.id.listArticles);
		sqLiteHelper = new SQLiteHelper(this);
		db = sqLiteHelper.getReadableDatabase();
		articleDao = new ArticleDAO();
		articles = articleDao.GetAllArticles(db);
		adapter = new ListViewAdapter_2(articles, this);
		listArticles.setAdapter(adapter);
		Log.i("Msg", articles.size() + "");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ib_index:
			Intent intent = new Intent(this, FirstActivity.class);
			startActivity(intent);
			break;
		case R.id.ib_add:
			Intent intent2 = new Intent(this, AddActivity.class);
			startActivity(intent2);
//			finish();
			break;
		case R.id.ib_inOut:
			Intent intent3 = new Intent(this, InOutActivity.class);
			startActivity(intent3);
//			finish();
			break;
		case R.id.ib_upload:
			Intent intent4 = new Intent(this, UploadActivity.class);
			startActivity(intent4);
//			finish();
			break;
		case R.id.ib_exit:
			if (dialog == null) {
				AlertDialog.Builder builder = new Builder(FirstActivity.this);
				builder.setTitle("提示");
				builder.setMessage("你确定要退出程序吗？");
				builder.setIcon(R.drawable.danger_sign);
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						System.exit(0);
					}
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				dialog = builder.create();
			}
			dialog.show();
			
			break;
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	private void exit() {
		long secondTime = System.currentTimeMillis();
		if (secondTime-firstTime>2000) {
			Toast.makeText(FirstActivity.this, "再按一次退出程序", 1000).show();
			firstTime = secondTime;
		} else {
			finish();
			System.exit(0);
		}
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		showList();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		db.close();
	}
}
