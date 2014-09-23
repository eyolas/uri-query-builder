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
     *
     * @return boolean
     */
    boolean isIndexed();

    /**
     * <p>Indicates if the builder add  bracket</p>
     * 
     * <p>Example:<br>
     * With list :  Arrays.asList("asse", "psg", "om"); and key club<br>
     * 
     * <ul>
     * <li>result with bracket: ?club[]=asse&club[]=psg&club[]=om</li>
     * <li>result without bracket: ?club=asse&club=psg&club=om</li>
     * </ul>
     *
     * @return boolean
     */
    boolean addBracket();
}
