package com.eyolas.http.query.collection;

import java.util.List;

/**
 *
 * @author eyolas
 * @param <E>
 */
public interface QueryList<E> extends List<E> {
    
    /**
     * Indicates whether the list should be indexed
     * @return boolean
     */
    boolean isIndexed();
}
