package com.eyolas.http.query.collection;

/**
 * Type of QueryList
 *
 * @author eyolas
 */
public enum QueryListType {

    /**
     * <p>
     * No bracket and no index.</p>
     *
     * <p>
     * Example:<br>
     * With list : Arrays.asList("asse", "psg", "om"); and key club<br>
     * result: ?club=asse&club=psg&club=om
     * </p>
     */
    NONE,
    /**
     * <p>
     * With bracket.</p>
     *
     * <p>
     * Example:<br>
     * With list : Arrays.asList("asse", "psg", "om"); and key club<br>
     * result: ?club[]=asse&club[]=psg&club[]=om
     * </p>
     */
    BRACKET,
    /**
     * <p>
     * Indexed</p>
     * <p>
     * Example:<br>
     * With list : Arrays.asList("asse", "psg", "om"); and key club<br>
     * result: ?club[0]=asse&club[1]=psg&club[2]=om
     * </p>
     */
    INDEXED;
}
