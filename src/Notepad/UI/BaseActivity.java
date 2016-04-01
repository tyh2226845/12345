package Notepad.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}

	Dialog dialog;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case R.id.article_add:
			intent = new Intent(this, AddActivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.article_InOut:
			intent = new Intent(this, InOutActivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.article_home:
			intent = new Intent(this, FirstActivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.article_upload:
			intent = new Intent(this, UploadActivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.article_exit:
			if (dialog == null) {
				Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("提示");
				builder.setMessage("您确定要退出程序吗？");
				builder.setIcon(R.drawable.danger_sign);
				builder.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						System.exit(0);
					}
				});

				builder.setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});

				dialog = builder.create();
			}
			dialog.show();
			break;
		}
		return true;
	}

}
