package Notepad.UI;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import Notepad.DAO.ArticleDAO;
import Notepad.DAO.SQLiteHelper;
import Notepad.Entites.Article;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.AlteredCharSequence;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class UploadActivity extends BaseActivity {

	ListView listArticles;
	List<Article> articles;
	Article updateart;
	Button btnforupload;
	Button btnback;

	@SuppressWarnings("null")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);

		this.setTitle("条码上传");
		listArticles = (ListView) findViewById(R.id.listforupload);
		btnforupload = (Button) findViewById(R.id.btnforupload);

		if (isConnect(this) == false) {
			new AlertDialog.Builder(this).setTitle("网络错误")
					.setMessage("网络连接失败，请确认网络连接").show();
		}

		SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
		SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
		ArticleDAO articleDao = new ArticleDAO();
		articles = articleDao.GetAllArticles1(db);
		Log.i("Msg", articles.size() + "");
		UploadListViewAdapter adapter = new UploadListViewAdapter(articles,
				this);
		try {
			listArticles.setAdapter(adapter);
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(UploadActivity.this, "2、" + e, Toast.LENGTH_SHORT)
					.show();
		}

		btnforupload.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				setAddDialog();
			}
		});
	}

	int istrue = 0;

	private void setAddDialog() {
		Intent intent;
		Log.i("Thread", "outof Thread");
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (Article article : articles) {
					int i = 0;
					String code = article.getName();
					String state = article.getContent();
					try {
						Log.i("Thread", "into Thread");
						// TODO Auto-generated method stub
						SoapObject rpc = new SoapObject("http://tempuri.org/",
								"insertCode");
						rpc.addProperty("code", code);
						rpc.addProperty("state", state);
						SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
								SoapEnvelope.VER11);
						envelope.dotNet = true;
						envelope.setOutputSoapObject(rpc);
						HttpTransportSE transport = new HttpTransportSE(
								"http://192.168.8.67:8383/Service1.asmx"); 
						try {
							// 调用WebService
							transport.call("http://tempuri.org/insertCode",
									envelope);
						} catch (Exception e) {
							e.printStackTrace();
							Toast.makeText(UploadActivity.this, "1、" + e,
									Toast.LENGTH_SHORT).show();
							istrue = 1;
						}
						SQLiteHelper sqLiteHelper = new SQLiteHelper(
								getApplicationContext());
						ArticleDAO articleDao = new ArticleDAO();
						article.setContent("2");
						article.setDate(new Date());
						SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
						articleDao.updateMyWay(db, article, code);

					} catch (Exception e) {
						// TODO: handle exception
						Toast.makeText(UploadActivity.this, "2、" + e,
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		}).start();
		Toast.makeText(UploadActivity.this, "上传成功", Toast.LENGTH_SHORT)
		.show();
//		if (istrue == 0) {
//			Toast.makeText(UploadActivity.this, "上传成功", Toast.LENGTH_SHORT)
//					.show();
//		} else {
//			Toast.makeText(UploadActivity.this, "上传失败,检查网络通畅或重新上传",
//					Toast.LENGTH_SHORT).show();
//		}
		intent = new Intent(this, FirstActivity.class);
		startActivity(intent);
		this.finish();
	}

	public static boolean isConnect(Context context) {
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("error", e.toString());
		}
		return false;
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
	// @Override
	// public void onBackPressed() {
	// if (listArticles.getVisibility() == View.VISIBLE) {
	// listArticles.setVisibility(View.GONE);
	// } else {
	// UploadActivity.this.finish();
	// }
	// }
}
