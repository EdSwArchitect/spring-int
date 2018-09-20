package com.bscllc.sbws;

public class Signoff {

    private final long id;
    private final String content;

    public Signoff(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}