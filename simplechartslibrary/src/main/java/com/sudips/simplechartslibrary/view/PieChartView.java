package com.sudips.simplechartslibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


import com.sudips.simplechartslibrary.model.PieChartItemModel;

import java.util.ArrayList;
import java.util.List;

public class PieChartView extends View {
    private int mViewHeight;
    private int mViewWidth;
    private List<PieChartItemModel> mPieChartItemModelList;
    private Context mContext;
    private List<Float> pieAngleList;
    private Paint mPaint;
    private RectF mRectF;

    public PieChartView(Context context, List<PieChartItemModel> pieChartItemModelList) {
        super(context);
        mContext = context;
        mPieChartItemModelList = pieChartItemModelList;
        initializePieChartAngleList();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        float left,top,right,bottom;
        if(mViewWidth > mViewHeight){
            top = 0;
            bottom = mViewHeight;
            left = (mViewWidth - mViewHeight)/2;
            right = left + mViewHeight;
        }else {
            left = 0;
            right = mViewWidth;
            top = (mViewHeight - mViewWidth)/2;
            bottom = top + mViewWidth;
        }
        mRectF = new RectF(left,top,right,bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float startAngle = 0;
        for(int i=0;i<mPieChartItemModelList.size();i++){
            mPaint.setColor(Color.parseColor(mPieChartItemModelList.get(i).getPieSliceColorCode()));
            canvas.drawArc(mRectF,startAngle,pieAngleList.get(i),true,mPaint);
            startAngle = startAngle + pieAngleList.get(i);
        }
        super.onDraw(canvas);
    }

    private void initializePieChartAngleList(){
        float totalValue = getPieChartValueTotal();
        pieAngleList = new ArrayList<>();
        for(PieChartItemModel eachPieChartItemModel : mPieChartItemModelList){
            pieAngleList.add((eachPieChartItemModel.getPieSliceValue()/totalValue)*360);
        }
    }

    private float getPieChartValueTotal(){
        float totalValue = 0;
        for (int i=0;i<mPieChartItemModelList.size();i++){
            totalValue = totalValue + mPieChartItemModelList.get(i).getPieSliceValue();
        }
        return totalValue;
    }
}
