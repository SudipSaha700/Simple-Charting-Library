package com.sudips.simplechartslibrary.model;

public class PieChartItemModel {
    private String pieSliceName;
    private float pieSliceValue;
    private String pieSliceColorCode;

    public String getPieSliceColorCode() {
        return pieSliceColorCode;
    }

    public void setPieSliceColorCode(String pieSliceColorCode) {
        this.pieSliceColorCode = pieSliceColorCode;
    }

    public String getPieSliceName() {
        return pieSliceName;
    }

    public void setPieSliceName(String pieSliceName) {
        this.pieSliceName = pieSliceName;
    }

    public float getPieSliceValue() {
        return pieSliceValue;
    }

    public void setPieSliceValue(float pieSliceValue) {
        this.pieSliceValue = pieSliceValue;
    }
}
