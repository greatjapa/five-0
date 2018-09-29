package com.five0;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Statistics {

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private BigDecimal sum;

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private BigDecimal avg;

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private BigDecimal max;

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private BigDecimal min;

    private long count;

    public Statistics() {
        this.sum = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.avg = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.max = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.min = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.count = 0;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "sum=" + sum +
                ", avg=" + avg +
                ", max=" + max +
                ", min=" + min +
                ", count=" + count +
                '}';
    }
}
