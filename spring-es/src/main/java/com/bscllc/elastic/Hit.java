package com.bscllc.elastic;

public class Hit {
    // {"_index":"mybrooklyn","_type":"doc","_id":"2OX_J2YByec2ZKqPeULz","_score":1.0,"_source":{"towards_brooklyn":9,"location":"Brooklyn Bridge","lat":40.70816397,"towards_manhattan":11,"@timestamp":"2017-10-01T07:00:00.000Z","weather_summary":"partly-cloudy-night","temperature":51,"events":null,"Pedestrians":20,"lon":-73.9995087,"precipitation":0.0}
    private String _index;
    private String _type;
    private String _id;
    private double _score;
    private ESBrooklyn _source;

    public String get_index() {
        return _index;
    }

    public void set_index(String _index) {
        this._index = _index;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public double get_score() {
        return _score;
    }

    public void set_score(double _score) {
        this._score = _score;
    }

    public ESBrooklyn get_source() {
        return _source;
    }

    public void set_source(ESBrooklyn _source) {
        this._source = _source;
    }

    @Override
    public String toString() {
        return "Hit{" +
                "_index='" + _index + '\'' +
                ", _type='" + _type + '\'' +
                ", _id='" + _id + '\'' +
                ", _score=" + _score +
                ", _source=" + _source +
                '}';
    }
}
