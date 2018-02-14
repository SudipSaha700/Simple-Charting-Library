package com.sudips.simplechartapplication.view.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.sudips.simplechartapplication.R;
import com.sudips.simplechartslibrary.model.BarChartItemModel;
import com.sudips.simplechartslibrary.model.LineGraphItemModel;
import com.sudips.simplechartslibrary.model.PieChartItemModel;
import com.sudips.simplechartslibrary.view.viewgroup.BarChart;
import com.sudips.simplechartslibrary.view.viewgroup.LineGraph;
import com.sudips.simplechartslibrary.view.viewgroup.PieChart;

import java.util.ArrayList;

public class CustomChartActivity extends Activity {

    private RelativeLayout mRlBarChartContainer;
    private ArrayList<BarChartItemModel> barChartItemModelArrayList;
    private ArrayList<PieChartItemModel> mPieChartItemModels;
    private RelativeLayout mRlPieChartContainer;
    private RelativeLayout mRlLineGraphContainer;
    private ArrayList<LineGraphItemModel> lineGraphItemModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        mRlBarChartContainer = (RelativeLayout)findViewById(R.id.rl_bar_chart_container);
        mRlPieChartContainer = (RelativeLayout)findViewById(R.id.rl_pie_chart_container);
        mRlLineGraphContainer = (RelativeLayout)findViewById(R.id.rl_line_graph_container);

        barChartItemModelArrayList = new ArrayList<>();

        BarChartItemModel barChartItemModel1 = new BarChartItemModel();
        barChartItemModel1.setBarName("JAN");
        barChartItemModel1.setBarValue(10000);
        BarChartItemModel barChartItemModel2 = new BarChartItemModel();
        barChartItemModel2.setBarName("FEB");
        barChartItemModel2.setBarValue(20000);
        BarChartItemModel barChartItemModel3 = new BarChartItemModel();
        barChartItemModel3.setBarName("MAR");
        barChartItemModel3.setBarValue(50000);
        BarChartItemModel barChartItemModel4 = new BarChartItemModel();
        barChartItemModel4.setBarName("APR");
        barChartItemModel4.setBarValue(30000);
        BarChartItemModel barChartItemModel5 = new BarChartItemModel();
        barChartItemModel5.setBarName("MAY");
        barChartItemModel5.setBarValue(40000);
        BarChartItemModel barChartItemModel6 = new BarChartItemModel();
        barChartItemModel6.setBarName("JUN");
        barChartItemModel6.setBarValue(80000);

        barChartItemModelArrayList.add(barChartItemModel1);
        barChartItemModelArrayList.add(barChartItemModel2);
        barChartItemModelArrayList.add(barChartItemModel3);
        barChartItemModelArrayList.add(barChartItemModel4);
        barChartItemModelArrayList.add(barChartItemModel5);
        barChartItemModelArrayList.add(barChartItemModel6);

        PieChartItemModel pieChartItemModel1 = new PieChartItemModel();
        pieChartItemModel1.setPieSliceColorCode("#FF0000");
        pieChartItemModel1.setPieSliceName("First slice");
        pieChartItemModel1.setPieSliceValue(100);
        PieChartItemModel pieChartItemModel2 = new PieChartItemModel();
        pieChartItemModel2.setPieSliceColorCode("#00FF00");
        pieChartItemModel2.setPieSliceName("Second slice");
        pieChartItemModel2.setPieSliceValue(150);
        PieChartItemModel pieChartItemModel3 = new PieChartItemModel();
        pieChartItemModel3.setPieSliceColorCode("#FFFF00");
        pieChartItemModel3.setPieSliceName("Third slice");
        pieChartItemModel3.setPieSliceValue(200);
        PieChartItemModel pieChartItemModel4 = new PieChartItemModel();
        pieChartItemModel4.setPieSliceColorCode("#0000FF");
        pieChartItemModel4.setPieSliceName("Forth slice");
        pieChartItemModel4.setPieSliceValue(250);

        mPieChartItemModels = new ArrayList<>();
        mPieChartItemModels.add(pieChartItemModel1);
        mPieChartItemModels.add(pieChartItemModel2);
        mPieChartItemModels.add(pieChartItemModel3);
        mPieChartItemModels.add(pieChartItemModel4);

        lineGraphItemModels = new ArrayList<>();

        LineGraphItemModel lineGraphItemModel1 = new LineGraphItemModel();
        lineGraphItemModel1.setxValue(10);
        lineGraphItemModel1.setyValue(20);
        LineGraphItemModel lineGraphItemModel2 = new LineGraphItemModel();
        lineGraphItemModel2.setxValue(15);
        lineGraphItemModel2.setyValue(30);
        LineGraphItemModel lineGraphItemModel3 = new LineGraphItemModel();
        lineGraphItemModel3.setxValue(25);
        lineGraphItemModel3.setyValue(50);
        LineGraphItemModel lineGraphItemModel4 = new LineGraphItemModel();
        lineGraphItemModel4.setxValue(20);
        lineGraphItemModel4.setyValue(15);

        lineGraphItemModels.add(lineGraphItemModel1);
        lineGraphItemModels.add(lineGraphItemModel2);
        lineGraphItemModels.add(lineGraphItemModel4);
        lineGraphItemModels.add(lineGraphItemModel3);

        BarChart barChart = new BarChart(CustomChartActivity.this, barChartItemModelArrayList,"Months","Gains");
        mRlBarChartContainer.addView(barChart);

        PieChart pieChart = new PieChart(this,mPieChartItemModels);
        mRlPieChartContainer.addView(pieChart);

        LineGraph lineGraph = new LineGraph(this,lineGraphItemModels,"X-Axis","Y-Axis");
        mRlLineGraphContainer.addView(lineGraph);
    }
}
