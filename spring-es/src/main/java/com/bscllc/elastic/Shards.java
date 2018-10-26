package com.bscllc.elastic;

public class Shards {
    //"_shards":{"total":5,"successful":5,"skipped":0,"failed":0}
    private int total;
    private int successful;
    private int skipped;
    private int failed;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSuccessful() {
        return successful;
    }

    public void setSuccessful(int successful) {
        this.successful = successful;
    }

    public int getSkipped() {
        return skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    @Override
    public String toString() {
        return "Shards{" +
                "total=" + total +
                ", successful=" + successful +
                ", skipped=" + skipped +
                ", failed=" + failed +
                '}';
    }
}
