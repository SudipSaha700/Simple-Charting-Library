package com.sudips.simplechartslibrary.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.sudips.simplechartslibrary.constant.Constants;

public class VerticalTextView extends View {
    private int mTextViewHeight;
    private int mTextViewWidth;
    private Context mContext;
    private String mText;
    private float mTextSize;
    private int mTextSizeInDp;
    private int mTextColor;

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public VerticalTextView(Context context, String text, int textSizeInDp) {
        super(context);
        mContext = context;
        mText = text;
        mTextSizeInDp = textSizeInDp;
        mTextColor = Color.BLACK;
    }

    public VerticalTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public VerticalTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public VerticalTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTextViewHeight = h;
        mTextViewWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        Typeface tf = Typeface.createFromAsset(mContext.getAssets(), Constants.FONT_PATH_HELVETICA_MEDIUM);
        Resources r = getResources();
        mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mTextSizeInDp, r.getDisplayMetrics());

        paint.setTypeface(tf);
        paint.setColor(mTextColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(mTextSize);
        paint.setStyle(Paint.Style.FILL);

        canvas.save();
        canvas.rotate(270f, mTextViewWidth, mTextViewHeight/2);
        canvas.drawText(mText,mTextViewWidth, mTextViewHeight/2, paint);
        canvas.restore();

        super.onDraw(canvas);
    }

    public void setmTextSizeInDp(int mTextSizeInDp) {
        this.mTextSizeInDp = mTextSizeInDp;
    }
}
