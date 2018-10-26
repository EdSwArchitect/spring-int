package com.bscllc.elastic;

import java.util.Arrays;

public class Hits {
    // "hits":{"total":7292,"max_score":1.0,"hits":[{"_index":"mybrooklyn","_type":"doc","_id":"2OX_J2YByec2ZKqPeULz","_score":1.0,"_source":{"towards_brooklyn":9,"location":"Brooklyn Bridge","lat":40.70816397,"towards_manhattan":11,"@timestamp":"2017-10-01T07:00:00.000Z","weather_summary":"partly-cloudy-night","temperature":51,"events":null,"Pedestrians":20,"lon":-73.9995087,"precipitation":0.0}}
    private int total;
    private double max_score;
    private Hit[] hits;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getMax_score() {
        return max_score;
    }

    public void setMax_score(double max_score) {
        this.max_score = max_score;
    }

    public Hit[] getHits() {
        return hits;
    }

    public void setHits(Hit[] hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "Hits{" +
                "total=" + total +
                ", max_score=" + max_score +
                ", hits=" + Arrays.toString(hits) +
                '}';
    }
}
