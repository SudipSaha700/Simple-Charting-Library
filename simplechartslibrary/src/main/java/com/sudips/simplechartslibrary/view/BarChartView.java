package com.sudips.simplechartslibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.sudips.simplechartslibrary.R;
import com.sudips.simplechartslibrary.constant.Constants;
import com.sudips.simplechartslibrary.model.BarChartItemModel;

import java.util.List;


public class BarChartView extends View {

    private Paint mPaint;
    private Context mContext;
    private float mViewHeight;
    private float mViewWidth;
    private List<BarChartItemModel> mBarChartItemModelList;
    private int mGradingTextColor;
    private int mAxisColor;
    private int mBarBackgroundColor;
    private int mBarFillColor;
    float mBarGap;
    float mAxisGradingTextSize;
    float mGraphMarginX;
    float mGraphMarginY;
    float mGraphAxisStrokeWidth;
    float mGraphAxisGradingTextGap;

    public void setGradingTextColor(int mGradingTextColor) {
        this.mGradingTextColor = mGradingTextColor;
    }

    public void setAxisColor(int mAxisColor) {
        this.mAxisColor = mAxisColor;
    }

    public void setBarBackgroundColor(int mBarBackgroundColor) {
        this.mBarBackgroundColor = mBarBackgroundColor;
    }

    public void setBarFillColor(int mBarFillColor) {
        this.mBarFillColor = mBarFillColor;
    }

    public BarChartView(Context context) {
        super(context);
    }

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BarChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public BarChartView(Context context, List<BarChartItemModel> barChartItemModelList){
        super(context);
        mContext = context;
        mBarChartItemModelList = barChartItemModelList;
        mPaint = new Paint();
        Typeface tf = Typeface.createFromAsset(context.getAssets(), Constants.FONT_PATH_HELVETICA_MEDIUM);
        mPaint.setTypeface(tf);
        mAxisColor = Color.BLACK;
        mGradingTextColor = Color.BLACK;
        mBarBackgroundColor = Color.WHITE;
        mBarFillColor = Color.BLUE;
    }

    /*get the maximum value from the inputted data set*/
    private float getMax() {
        float maxVal = Integer.MIN_VALUE;
        for(BarChartItemModel eachBarChartItemModel : mBarChartItemModelList){
            if(eachBarChartItemModel.getBarValue()>maxVal) {
                maxVal = eachBarChartItemModel.getBarValue();
            }
        }
        return maxVal + (maxVal/2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight =h;
        mViewWidth =w;
        mBarGap = Constants.DEFAULT_BAR_GAP;
        mAxisGradingTextSize = Constants.DEFAULT_AXIS_GRADING_TEXT_SIZE;
        mGraphAxisStrokeWidth = Constants.AXIS_STROKE_WIDTH;
        mGraphAxisGradingTextGap = Constants.DEFAULT_Y_AXIS_GRADING_TEXT_GAP;
        mGraphMarginX = mViewWidth / Constants.GRAPH_MARGIN_FRACTION_VALUE;
        mGraphMarginY = mViewHeight / Constants.GRAPH_MARGIN_FRACTION_VALUE;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBarChartItemModelList.size() <= Constants.MAX_NO_OF_BARS){
            float barMaxValue = getMax();
            float graphColumnOffsetX = mGraphMarginX + mBarGap + mGraphAxisStrokeWidth;
            float graphColumnOffsetY = mGraphMarginY + mGraphAxisStrokeWidth;
            float graphXAxisIntersectionLength = mGraphMarginX / 4;
            float graphYAxisIntersectionLength = mGraphMarginY / 4;
            float totalBarHeight = mViewHeight - graphColumnOffsetY;
            float eachYSectionHeight = totalBarHeight / Constants.DEFAULT_NO_OF_Y_SECTIONS;

            if (barMaxValue>-1) {
                mPaint.setTextAlign(Paint.Align.LEFT);
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(mGraphAxisStrokeWidth);
                mPaint.setColor(mAxisColor);
                //Drawing graph axis
                canvas.drawLine(mGraphMarginX,0, mGraphMarginX,(mViewHeight- mGraphMarginY)+graphYAxisIntersectionLength,mPaint);
                canvas.drawLine(mGraphMarginX - graphXAxisIntersectionLength,mViewHeight - mGraphMarginY,mViewWidth,mViewHeight - mGraphMarginY,mPaint);

                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setStrokeWidth(1);

                float columnWidth = (mViewWidth - graphColumnOffsetX) / Constants.MAX_NO_OF_BARS;
                mPaint.setTextAlign(Paint.Align.RIGHT);
                mPaint.setTextSize(mAxisGradingTextSize);
                mPaint.setColor(mGradingTextColor);

                generateYAxisGrading(graphColumnOffsetY, eachYSectionHeight,canvas, mGraphMarginX, mGraphAxisStrokeWidth, mGraphAxisGradingTextGap, mAxisGradingTextSize);
                for (int i = 0; i < mBarChartItemModelList.size() ; i++) {

                    float val = mBarChartItemModelList.get(i).getBarValue();
                    float rat = val / barMaxValue;
                    float columnHeight = (totalBarHeight - eachYSectionHeight)*rat;

                    mPaint.setStyle(Paint.Style.FILL);
                    mPaint.setTextAlign(Paint.Align.LEFT);
                    //Drawing default Colored Bar
                    canvas.drawRect(getDefaultBar(columnWidth,graphColumnOffsetX, mBarGap,graphColumnOffsetY, eachYSectionHeight,i),mPaint);
                    //Drawing Colored Bar for only non negative values
                    if(columnHeight > -1){
                        canvas.drawRect(getDataBar(columnWidth,graphColumnOffsetX, mBarGap,columnHeight,graphColumnOffsetY,i),mPaint);
                    }

                    mPaint.setColor(mGradingTextColor);
                    mPaint.setTextAlign(Paint.Align.CENTER);
                    mPaint.setTextSize(mAxisGradingTextSize);

                    float columnNameXPosition = (((i * columnWidth) + graphColumnOffsetX) + (((i + 1)*columnWidth) + graphColumnOffsetX - mBarGap)) / 2;
                    //Drawing X-axis gradings
                    canvas.drawText(mBarChartItemModelList.get(i).getBarName(),columnNameXPosition,(mViewHeight - mGraphMarginY + mGraphAxisStrokeWidth) + mGraphAxisGradingTextGap,mPaint);
                }
            }else{
                mPaint.setColor(Color.RED);
                canvas.drawText(Constants.ERROR_MSG_NEGATIVE_VALUES_NOT_SUPPORTED, (mViewWidth / 2), mViewHeight/2, mPaint);
            }
        }else {
            mPaint.setColor(Color.RED);
            canvas.drawText(Constants.ERROR_MSG_MAX_BAR_LIMIT_EXCEEDED, (mViewWidth / 2), mViewHeight/2, mPaint);
        }
        super.onDraw(canvas);
    }

    private Rect getDataBar(float barWidth, float graphColumnOffsetX, float barGap, float barHeight, float graphColumnOffsetY, int position){
        Rect rect = new Rect();
        rect.left =(int)((position * barWidth) + graphColumnOffsetX);
        rect.right =(int)(((position + 1)* barWidth) + graphColumnOffsetX - barGap);
        rect.top =(int) (mViewHeight - (barHeight + graphColumnOffsetY));
        rect.bottom =(int)(mViewHeight - graphColumnOffsetY);
        mPaint.setColor(mBarFillColor);
        return rect;
    }

    private Rect getDefaultBar(float barWidth, float graphColumnOffsetX, float barGap, float graphColumnOffsetY, float eachYSectionHeight, int position){
        Rect rect = new Rect();
        rect.left =(int)((position * barWidth) + graphColumnOffsetX);
        rect.right =(int)(((position + 1)* barWidth) + graphColumnOffsetX - barGap);
        rect.top = (int) eachYSectionHeight;
        rect.bottom =(int)(mViewHeight - graphColumnOffsetY);
        mPaint.setColor(mBarBackgroundColor);
        return rect;
    }

    private void generateYAxisGrading(float graphColumnOffsetY, float eachYSectionHeight, Canvas canvas, float graphMarginX , float graphAxisStrokeWidth, float graphAxisTextGap, float graphTextSize){
        float initialGradingHeight = mViewHeight - graphColumnOffsetY;
        float initialGradingValue = getMax() / (Constants.DEFAULT_NO_OF_Y_SECTIONS - 1);
        mPaint.setColor(mContext.getResources().getColor(R.color.graphAxisGradingTextColor));
        for (int i=0;i<6;i++){
            canvas.drawText( "" + (int)(i * initialGradingValue) ,
                    (graphMarginX - graphAxisStrokeWidth - graphAxisTextGap),initialGradingHeight + graphTextSize + graphAxisStrokeWidth ,mPaint);
            initialGradingHeight = (initialGradingHeight - eachYSectionHeight);
        }
    }

}
