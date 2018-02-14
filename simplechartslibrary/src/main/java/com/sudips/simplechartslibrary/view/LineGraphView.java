package com.sudips.simplechartslibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sudips.simplechartslibrary.constant.Constants;
import com.sudips.simplechartslibrary.model.LineGraphItemModel;

import java.util.List;

public class LineGraphView extends View {
    private float mViewHeight;
    private float mViewWidth;
    private List<LineGraphItemModel> mLineGraphItemModelList;
    private Paint mPaint;
    private int mGradingTextColor;
    private int mAxisColor;
    private int mPointColor;
    private int mLineColor;
    float mGraphTextSize = 25f;
    float mGraphMarginX = mViewWidth / 6;
    float mGraphMarginY = mViewHeight / 6;
    float graphAxisStrokeWidth = 5f;

    public void setGradingTextColor(int mGradingTextColor) {
        this.mGradingTextColor = mGradingTextColor;
    }

    public void setAxisColor(int mAxisColor) {
        this.mAxisColor = mAxisColor;
    }

    public void setPointColor(int mPointColor) {
        this.mPointColor = mPointColor;
    }

    public void setLineColor(int mLineColor) {
        this.mLineColor = mLineColor;
    }

    public LineGraphView(Context context, List<LineGraphItemModel> lineGraphItemModelList) {
        super(context);
        mLineGraphItemModelList = lineGraphItemModelList;
        mPaint = new Paint();
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeue-Medium.ttf");
        mPaint.setTypeface(tf);
        mAxisColor = Color.BLACK;
        mGradingTextColor = Color.BLACK;
        mPointColor = Color.RED;
        mLineColor = Color.YELLOW;
    }

    public LineGraphView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LineGraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LineGraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight =h;
        mViewWidth =w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float xMaxValue = getMaxX();
        float yMaxValue = getMaxY();
        float graphColumnOffsetX = mGraphMarginX + graphAxisStrokeWidth;
        float graphColumnOffsetY = mGraphMarginY - graphAxisStrokeWidth;
        float graphXAxisIntersectionLength = mGraphMarginX / 4;
        float graphYAxisIntersectionLength = mGraphMarginY / 4;
        float graphHeight = mViewHeight - graphColumnOffsetY;
        float graphWidth = mViewWidth - graphColumnOffsetX;
        float singleRowHeight = graphHeight / (Constants.TOTAL_NO_OF_GRADINGS  + 1);
        float singleColumnWidth = graphWidth / (Constants.TOTAL_NO_OF_GRADINGS + 1);
        float graphAxisTextGap = 30f;

        if(xMaxValue > -1 && yMaxValue > -1){
            mPaint.setTextAlign(Paint.Align.LEFT);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(graphAxisStrokeWidth);
            mPaint.setColor(mAxisColor);

            //Drawing graph axis
            canvas.drawLine(mGraphMarginX,0, mGraphMarginX,(mViewHeight- mGraphMarginY)+graphYAxisIntersectionLength,mPaint);
            canvas.drawLine(mGraphMarginX - graphXAxisIntersectionLength,mViewHeight - mGraphMarginY,mViewWidth,mViewHeight - mGraphMarginY,mPaint);

            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(1);
            mPaint.setTextSize(mGraphTextSize);
            mPaint.setTextAlign(Paint.Align.CENTER);

            generateXAxisGrading(graphHeight,singleColumnWidth,canvas, mGraphMarginX,graphAxisStrokeWidth,graphAxisTextGap, mGraphTextSize);

            mPaint.setTextAlign(Paint.Align.RIGHT);

            generateYAxisGrading(graphColumnOffsetY,singleRowHeight,canvas, mGraphMarginX,graphAxisStrokeWidth,graphAxisTextGap, mGraphTextSize);
            mPaint.setColor(mPointColor);

            for(int i=0;i<mLineGraphItemModelList.size();i++){
                float xVal = mLineGraphItemModelList.get(i).getxValue();
                float xRat = xVal / xMaxValue;
                float xCoordinate = ((graphWidth - singleColumnWidth)*xRat) + graphColumnOffsetX;
                float yVal = mLineGraphItemModelList.get(i).getyValue();
                float yRat = yVal / yMaxValue;
                float yCoordinate = (graphHeight - ((graphHeight - singleRowHeight)*yRat));
                mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(xCoordinate,yCoordinate,(2*graphAxisStrokeWidth),mPaint);
                mPaint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(xCoordinate,yCoordinate,(4*graphAxisStrokeWidth),mPaint);
                mLineGraphItemModelList.get(i).setxCoordinate(xCoordinate);
                mLineGraphItemModelList.get(i).setyCoordinate(yCoordinate);
            }

            mPaint.setStrokeWidth(graphAxisStrokeWidth);
            mPaint.setColor(mLineColor);

            for(int i =0;i<mLineGraphItemModelList.size()-1;i++){
                canvas.drawLine(mLineGraphItemModelList.get(i).getxCoordinate(),
                        mLineGraphItemModelList.get(i).getyCoordinate(),mLineGraphItemModelList.get(i+1).getxCoordinate(),
                        mLineGraphItemModelList.get(i+1).getyCoordinate(),mPaint);
            }
        }else {
            mPaint.setColor(Color.RED);
            canvas.drawText(Constants.ERROR_MSG_NEGATIVE_VALUES_NOT_SUPPORTED, (mViewWidth / 2), mViewHeight/2, mPaint);
        }
        super.onDraw(canvas);
    }

    private void generateYAxisGrading(float graphColumnOffsetY,float singleRowHeight,Canvas canvas,float graphMarginX ,float graphAxisStrokeWidth,float graphAxisTextGap,float graphTextSize){
        float initialGradingHeight = mViewHeight - graphColumnOffsetY;
        float initialGradingValue = getMaxY() / Constants.TOTAL_NO_OF_GRADINGS;
        mPaint.setColor(mGradingTextColor);
        for (int i=0;i<=Constants.TOTAL_NO_OF_GRADINGS;i++){
            canvas.drawText( "" + (int)(i * initialGradingValue) ,
                    (graphMarginX - graphAxisStrokeWidth - graphAxisTextGap),initialGradingHeight + graphTextSize ,mPaint);
            initialGradingHeight = (initialGradingHeight - singleRowHeight);
        }
    }

    private void generateXAxisGrading(float graphHeight,float singleColumnWidth,Canvas canvas,float graphMarginX ,float graphAxisStrokeWidth,float graphAxisTextGap,float graphTextSize){
        float gradingHeight =  graphHeight  + graphAxisTextGap;
        float unitGradingValue = getMaxX() / Constants.TOTAL_NO_OF_GRADINGS;
        float unitGradingWidth = graphMarginX + graphAxisStrokeWidth + singleColumnWidth;
        mPaint.setColor(mGradingTextColor);
        for (int i=1;i<=Constants.TOTAL_NO_OF_GRADINGS;i++){
            canvas.drawText( "" + (int)(i * unitGradingValue) ,unitGradingWidth
                    ,gradingHeight ,mPaint);
            unitGradingWidth = unitGradingWidth + singleColumnWidth;
        }
    }

    private float getMaxX() {
        float maxVal = Integer.MIN_VALUE;
        for(LineGraphItemModel eachLineGraphItemModel : mLineGraphItemModelList){
            if(eachLineGraphItemModel.getxValue()>maxVal) {
                maxVal = eachLineGraphItemModel.getxValue();
            }
        }
        return maxVal + (maxVal/2);
    }

    private float getMaxY() {
        float maxVal = Integer.MIN_VALUE;
        for(LineGraphItemModel eachLineGraphItemModel : mLineGraphItemModelList){
            if(eachLineGraphItemModel.getyValue()>maxVal) {
                maxVal = eachLineGraphItemModel.getyValue();
            }
        }
        return maxVal + (maxVal/2);
    }
}
