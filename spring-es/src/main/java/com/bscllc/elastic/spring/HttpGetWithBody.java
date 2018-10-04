package com.bscllc.elastic.spring;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public class HttpGetWithBody extends HttpEntityEnclosingRequestBase {
    @Override
    public String getMethod() {
        return "GET";
    }
}
