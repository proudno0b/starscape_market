package com.market.app;
class FlatItemStatistics implements Comparable<FlatItemStatistics> {
    protected ItemStatistics STAT;
    public FlatItemStatistics(ItemStatistics stat) {
        this.STAT = stat;
    }
     @Override
    public int compareTo(FlatItemStatistics other) {
        if (this.STAT.percentMargin < other.STAT.percentMargin) {
            return -1;
        } else if (this.STAT.percentMargin > other.STAT.percentMargin) {
            return 1;
        } else {
            return 0;
        }

    }
}