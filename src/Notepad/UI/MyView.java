package Notepad.UI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View{

	private int mColor;
	private Bitmap mIconBitmap;
	private String mText;
	private float mTextSize;
	
	private Canvas mCanvas;
	private Bitmap mBitmap;
	private Paint mPaint;
	
	private float mAlpha;
	
	private Rect mIconRect;
	private Rect mTextBound;
	private Paint mTextPaint;
	
	private static final String INSTANCE_STATUS = "instance_status";
	private static final String STATUS_ALPHA = "status_alpha";
	
	public MyView(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}


	public MyView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}
	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);
		mColor = ta.getColor(R.styleable.MyView_color, 0);
		mText = ta.getString(R.styleable.MyView_text);
		mTextSize = ta.getDimension(R.styleable.MyView_textSize, 0);
		BitmapDrawable drawable = (BitmapDrawable) ta.getDrawable(R.styleable.MyView_icon);
		mIconBitmap = drawable.getBitmap();
		ta.recycle();
		
		mTextBound = new Rect();
		mTextPaint = new Paint();
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setColor(0xff555555);
		mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int iconWidth = Math.min(getMeasuredWidth()-getPaddingLeft()-getPaddingRight(), getMeasuredHeight()-getPaddingTop()-getPaddingBottom());
		int left = (getMeasuredWidth()-iconWidth)/2;
		int top = (getMeasuredHeight()-mTextBound.height()-iconWidth)/2;
		mIconRect = new Rect(left, top, left+iconWidth, top+iconWidth);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(mIconBitmap, null, mIconRect, null);
		int alpha = (int) Math.ceil(255*mAlpha);
		setupTargetBitmap(alpha);
		canvas.drawBitmap(mBitmap, 0, 0, null);
		drawSourceText(canvas,alpha);
		drawTargetText(canvas,alpha);
	}


	private void drawTargetText(Canvas canvas, int alpha) {
		// TODO Auto-generated method stub
		mTextPaint.setColor(mColor);
		mTextPaint.setAlpha(alpha);
		int x = (getMeasuredWidth()-mTextBound.width())/2;
		int y = mIconRect.bottom+mTextBound.height();
		canvas.drawText(mText, x, y, mTextPaint);
	}


	private void drawSourceText(Canvas canvas, int alpha) {
		// TODO Auto-generated method stub
		mTextPaint.setColor(0xff333333);
		mTextPaint.setAlpha(255-alpha);
		int x = (getMeasuredWidth()-mTextBound.width())/2;
		int y = mIconRect.bottom+mTextBound.height();
		canvas.drawText(mText, x, y, mTextPaint);
	}


	private void setupTargetBitmap(int alpha) {
		// TODO Auto-generated method stub
		mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		mPaint = new Paint();
		mPaint.setColor(mColor);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setAlpha(alpha);
		mCanvas.drawRect(mIconRect, mPaint);
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		mPaint.setAlpha(255);
		mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);
	}
	
	public void setIconAlpha(float alpha) {
		mAlpha = alpha;
		invalidateView();
	}


	private void invalidateView() {
		// TODO Auto-generated method stub
		if (Looper.getMainLooper()==Looper.myLooper()) {
			invalidate();
		} else {
			postInvalidate();
		}
	}
	
	@Override
	protected Parcelable onSaveInstanceState() {
		// TODO Auto-generated method stub
		Bundle bundle = new Bundle();
		bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
		bundle.putFloat(STATUS_ALPHA, mAlpha);
		return bundle;
	}
	
	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		// TODO Auto-generated method stub
		if (state instanceof Bundle) {
			Bundle bundle = (Bundle) state;
			mAlpha = bundle.getFloat(STATUS_ALPHA);
			super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
			return;
		}
		super.onRestoreInstanceState(state);
	}

}
