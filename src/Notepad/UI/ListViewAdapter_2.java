package Notepad.UI;

import java.util.List;

import Notepad.Entites.Article;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter_2 extends BaseAdapter{

	private List<Article> articles;
	private LayoutInflater inflater;
	public ListViewAdapter_2(List<Article> articles,Context context) {
		this.articles = articles;
		inflater = LayoutInflater.from(context);
	}
	
	public void onDataChange(List<Article> articles) {
		this.articles = articles;
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return articles.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return articles.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item2, null);
			holder = new ViewHolder();
			holder.title_article = (TextView) convertView.findViewById(R.id.title_article);
			holder.content_article = (TextView) convertView.findViewById(R.id.content_article);
			holder.data_article = (TextView) convertView.findViewById(R.id.date_article);
			convertView.setTag(holder);
		}
		holder = (ViewHolder) convertView.getTag();
		Article article = articles.get(position);
		holder.title_article.setText(article.getName());
		holder.content_article.setText(cutString(article.getContent(), 50, "..."));
		holder.data_article.setText(article.getDate().toLocaleString());
		return convertView;
	}
	class ViewHolder {
		TextView title_article;
		TextView content_article;
		TextView data_article;
	}
	static String cutString(String source, int length, String addString) {

		if (source.length() <= length) {
			return source;
		}

		return source.substring(0, length) + addString;
	}
	class EditClick implements ImageView.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
		
	}

	
}
