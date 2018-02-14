package com.sudips.simplechartslibrary.view.viewgroup;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sudips.simplechartslibrary.R;
import com.sudips.simplechartslibrary.constant.Constants;
import com.sudips.simplechartslibrary.model.BarChartItemModel;
import com.sudips.simplechartslibrary.view.BarChartView;
import com.sudips.simplechartslibrary.view.VerticalTextView;

import java.util.List;

public class BarChart extends RelativeLayout{
    private Context mContext;
    private List<BarChartItemModel> mBarChartItemModelList;
    private String mXAxisTitle;
    private String mYAxisTitle;
    private TextView mTvBarChartXTitle;
    private VerticalTextView mVerticalTextView;
    private BarChartView mBarChartView;

    public BarChart(Context context, List<BarChartItemModel> barChartItemModelList, String xAxisTitle, String yAxisTitle) {
        super(context);
        mContext = context;
        mBarChartItemModelList = barChartItemModelList;
        mXAxisTitle = xAxisTitle;
        mYAxisTitle = yAxisTitle;
        init();
    }

    public BarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public BarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public BarChart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.bar_chart_layout, this);

        LinearLayout llBarChart = (LinearLayout)findViewById(R.id.ll_bar_chart);
        LinearLayout llBarChartYTitle = (LinearLayout) findViewById(R.id.ll_bar_chart_y_title);
        mVerticalTextView = new VerticalTextView(mContext,mYAxisTitle, Constants.DEFAULT_TEXT_SIZE_IN_DP);
        mTvBarChartXTitle = (TextView)findViewById(R.id.tv_bar_chart_x_title);
        LinearLayout llBarChartLabelContainer = (LinearLayout)findViewById(R.id.ll_bar_chart_label_container);

        showBarChartLabels(llBarChartLabelContainer);
        llBarChartYTitle.addView(mVerticalTextView);
        mTvBarChartXTitle.setText(mXAxisTitle);
        Typeface tf = Typeface.createFromAsset(mContext.getAssets(), Constants.FONT_PATH_HELVETICA_MEDIUM);
        mTvBarChartXTitle.setTypeface(tf);

        mBarChartView = new BarChartView(mContext, mBarChartItemModelList);
        llBarChart.addView(mBarChartView);
    }

    private void showBarChartLabels(LinearLayout labelsLayout){
        for (int i=0;i<mBarChartItemModelList.size();i++){
            View itemLabelView = inflate(getContext(), R.layout.line_graph_label_item_layout, null );
            TextView tvLineGraphItemDesc = (TextView)itemLabelView.findViewById(R.id.tv_line_graph_item_desc);

            itemLabelView.findViewById(R.id.tv_line_graph_item_count).setVisibility(View.GONE);
            String descString = mBarChartItemModelList.get(i).getBarName() + " - " + (int) mBarChartItemModelList.get(i).getBarValue();
            tvLineGraphItemDesc.setText(descString);

            labelsLayout.addView(itemLabelView);
        }
    }

    public void setAxisGradingTextColor(int color){
        mBarChartView.setGradingTextColor(color);
    }

    public void setAxisTitleColor(int color){
        mTvBarChartXTitle.setTextColor(color);
        mVerticalTextView.setTextColor(color);
    }

    public void setAxisColor(int color){
        mBarChartView.setAxisColor(color);
    }

    public void setBarBackgroundColor(int color){
        mBarChartView.setBarBackgroundColor(color);
    }

    public void setBarColor(int color){
        mBarChartView.setBarFillColor(color);
    }

    public void setmTitleTextSize(int mTitleTextSize) {
        mVerticalTextView.setmTextSizeInDp(mTitleTextSize);
    }
}
