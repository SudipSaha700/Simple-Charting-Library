package com.sudips.simplechartslibrary.view.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sudips.simplechartslibrary.R;
import com.sudips.simplechartslibrary.model.LineGraphItemModel;
import com.sudips.simplechartslibrary.view.LineGraphView;
import com.sudips.simplechartslibrary.view.VerticalTextView;

import java.util.ArrayList;
import java.util.List;

public class LineGraph extends RelativeLayout {
    private List<LineGraphItemModel> mLineGraphItemModelList = new ArrayList<>();
    private String mXAxisTitle;
    private String mYAxisTitle;
    private Context mContext;
    private LineGraphView mLineGraphView;
    private TextView mTvLineGraphXTitle;
    private VerticalTextView mVerticalTextView;

    public LineGraph(Context context,List<LineGraphItemModel> lineGraphItemModelList,String xAxisTitle,String yAxisTitle) {
        super(context);
        mLineGraphItemModelList = lineGraphItemModelList;
        mXAxisTitle = xAxisTitle;
        mYAxisTitle = yAxisTitle;
        mContext = context;
        init();
    }

    public LineGraph(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LineGraph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public LineGraph(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.line_graph_layout, this);

        LinearLayout llLineGraph = (LinearLayout)findViewById(R.id.ll_line_graph);
        LinearLayout llLineGraphLabelContainer = (LinearLayout)findViewById(R.id.ll_line_graph_label_container);
        LinearLayout llLineGraphYTitle = (LinearLayout) findViewById(R.id.ll_line_graph_y_title);
        mTvLineGraphXTitle = (TextView)findViewById(R.id.tv_line_graph_x_title);

        showLineGraphLabels(llLineGraphLabelContainer);
        mTvLineGraphXTitle.setText(mXAxisTitle);

        mVerticalTextView = new VerticalTextView(mContext,mYAxisTitle,16);
        llLineGraphYTitle.addView(mVerticalTextView);

        mLineGraphView = new LineGraphView(mContext,mLineGraphItemModelList);
        llLineGraph.addView(mLineGraphView);
    }

    private void showLineGraphLabels(LinearLayout labelsLayout){
        for (int i=0;i<mLineGraphItemModelList.size();i++){
            View itemLabelView = inflate(getContext(), R.layout.line_graph_label_item_layout, null );
            TextView tvLineGraphItemCount = (TextView)itemLabelView.findViewById(R.id.tv_line_graph_item_count);
            TextView tvLineGraphItemDesc = (TextView)itemLabelView.findViewById(R.id.tv_line_graph_item_desc);
            tvLineGraphItemCount.setText((i+1)+".");
            String descString = mXAxisTitle + " - "  + (int) mLineGraphItemModelList.get(i).getxValue() + "\n" + mYAxisTitle + " - " + (int) mLineGraphItemModelList.get(i).getyValue();
            tvLineGraphItemDesc.setText(descString);
            labelsLayout.addView(itemLabelView);
        }
    }

    public void setAxisGradingTextColor(int color){
        mLineGraphView.setGradingTextColor(color);
    }

    public void setAxisTitleColor(int color){
        mTvLineGraphXTitle.setTextColor(color);
        mVerticalTextView.setTextColor(color);
    }

    public void setAxisColor(int color){
        mLineGraphView.setAxisColor(color);
    }

    public void setGraphPointColor(int color){
        mLineGraphView.setPointColor(color);
    }

    public void setGraphLineColor(int color){
        mLineGraphView.setLineColor(color);
    }
}
