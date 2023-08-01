package com.study.design.esquery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class EsSqlQuery {
    private String query;
    private Long fetchSize;
    private String cursor;

    public EsSqlQuery(String cursor) {
        this.cursor = cursor;
    }

    public EsSqlQuery(String query, Long fetchSize) {
        this.query = query;
        this.fetchSize = fetchSize;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(Long fetchSize) {
        this.fetchSize = fetchSize;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }
}
