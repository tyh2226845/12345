package Notepad.UI;

import java.util.List;

import Notepad.DAO.ArticleDAO;
import Notepad.DAO.SQLiteHelper;
import Notepad.Entites.Article;
import Notepad.UI.EditHandler.ViewHolder;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter {

	LayoutInflater inflater;

	/**
	 * 要绑定的文章数据
	 */
	List<Article> articles;

	Context context;

	public ListViewAdapter(List<Article> articles, Context context) {
		this.articles = articles;
		this.context = context;

		// 初始化一个填充对象
		inflater = LayoutInflater.from(context);
	}

	/**
	 * 总数据
	 */
//	适配器更新
	public void onDataChange(List<Article> mArticles) {
		articles = mArticles;
		this.notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return articles.size();
	}

	@Override
	public Object getItem(int position) {
		return articles.get(position);
	}

	@Override
	public long getItemId(int position) {
		return articles.get(position).getId();
	}

	int[] pictures = new int[] { R.drawable.e011, R.drawable.e02,
			R.drawable.e03, R.drawable.e04, R.drawable.e05 }; 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			view = inflater.inflate(R.layout.list_item, parent, false);
		} else {
			view = convertView;
		}
		Article item = null;
		item = articles.get(position);

		TextView title_article = (TextView) view
				.findViewById(R.id.title_article);
		TextView content_article = (TextView) view
				.findViewById(R.id.content_article);
		TextView date_article = (TextView) view.findViewById(R.id.date_article);
		ImageView picture_article = (ImageView) view
				.findViewById(R.id.picture_article);

		ImageView btn_imageEdit = (ImageView) view
				.findViewById(R.id.btn_imageEdit);

		btn_imageEdit
				.setOnClickListener(new EditHandler(item.getId(), context));

		ImageView btn_imageDel = (ImageView) view
				.findViewById(R.id.btn_imageDel);

		btn_imageDel
				.setOnClickListener(new DeleteHandler(item.getId(), context));

		title_article.setText(item.getName());
		content_article.setText(cutString(item.getContent(), 50, "..."));
		date_article.setText(item.getDate().toLocaleString());
		//picture_article.setImageResource(pictures[position % pictures.length]);

		return view;
//		/**
//		 * 使用ViewHolder
//		 */
//		ViewHolder holder = null;
//		if (convertView == null) {
//			convertView = inflater.inflate(R.layout.list_item, null);
//			holder = new ViewHolder();
//			holder.title_article = (TextView) convertView.findViewById(R.id.title_article);
//		}
//		return convertView;
	}

	

	static String cutString(String source, int length, String addString) {

		if (source.length() <= length) {
			return source;
		}

		return source.substring(0, length) + addString;
	}
}

class DeleteHandler implements ImageView.OnClickListener {

	int articleId;
	Context context;
	Dialog dialog = null;

	public DeleteHandler(int articleId, Context context) {
		this.articleId = articleId;
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		if (dialog == null) {
			Builder builder = new AlertDialog.Builder(context);
			builder.setTitle("提示");
			builder.setMessage("您确定要删除吗？");
			builder.setIcon(R.drawable.danger_sign);
			builder.setPositiveButton("确定", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

					// 执行添加动作
					SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
					SQLiteDatabase db = sqLiteHelper.getWritableDatabase();

					ArticleDAO articleDao = new ArticleDAO();
					articleDao.DeleteArticleByKey(db, articleId);
					Toast.makeText(context, "删除成功!", Toast.LENGTH_SHORT).show();

					Intent intent = new Intent(context, FirstActivity.class);
					context.startActivity(intent);
					
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
	}

}

class EditHandler implements ImageView.OnClickListener {

	int articleId;
	Context context;

	public EditHandler(int articleId, Context context) {
		this.articleId = articleId;
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, EditActivity.class);
		intent.putExtra("id", articleId);
		context.startActivity(intent);
	}
	class ViewHolder {
		TextView title_article;
		TextView content_article;
		TextView data_article;
	}

}
