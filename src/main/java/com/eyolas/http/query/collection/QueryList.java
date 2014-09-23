package com.eyolas.http.query.collection;

import java.util.List;

/**
 * List of query
 * 
 * @author eyolas
 * @param <E>
 */
public interface QueryList<E> extends List<E> {
    /**
     * Indicate type of queryList
     * @return QueryListType
     */
    QueryListType getQueryListType();
}
