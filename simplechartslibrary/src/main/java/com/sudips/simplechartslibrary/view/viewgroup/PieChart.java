package com.sudips.simplechartslibrary.view.viewgroup;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sudips.simplechartslibrary.R;
import com.sudips.simplechartslibrary.model.PieChartItemModel;
import com.sudips.simplechartslibrary.view.PieChartView;

import java.util.List;

public class PieChart extends LinearLayout {
    private Context mContext;
    private List<PieChartItemModel> mPieChartItemModelList;

    public PieChart(Context context,List<PieChartItemModel> pieChartItemModelList) {
        super(context);
        mContext = context;
        mPieChartItemModelList = pieChartItemModelList;
        init();
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PieChart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(){
        inflate(getContext(), R.layout.pie_chart_layout, this);
        LinearLayout llPieChartContainer = (LinearLayout)findViewById(R.id.ll_pie_chart_container);
        LinearLayout llPieChartLabelContainer = (LinearLayout)findViewById(R.id.ll_pie_chart_label_container);

        showPieChartLabels(llPieChartLabelContainer);

        PieChartView pieChartView = new PieChartView(mContext,mPieChartItemModelList);
        llPieChartContainer.addView(pieChartView);
    }

    private void showPieChartLabels(LinearLayout labelsLayout){
        for (int i=0;i<mPieChartItemModelList.size();i++){
            View itemLabelView = inflate(getContext(), R.layout.pie_chat_label_item_layout, null );
            LinearLayout llPieSliceColorIndicator = (LinearLayout)itemLabelView.findViewById(R.id.ll_pie_slice_color_indicator);
            TextView tvPieSliceDesc = (TextView)itemLabelView.findViewById(R.id.tv_pie_slice_desc);

            llPieSliceColorIndicator.setBackgroundColor(Color.parseColor(mPieChartItemModelList.get(i).getPieSliceColorCode()));
            String descString = mPieChartItemModelList.get(i).getPieSliceName() + " - " + (int) mPieChartItemModelList.get(i).getPieSliceValue();
            tvPieSliceDesc.setText(descString);
            labelsLayout.addView(itemLabelView);
        }
    }
}
