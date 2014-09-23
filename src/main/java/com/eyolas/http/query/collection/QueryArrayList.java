package com.eyolas.http.query.collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author eyolas
 * @param <E>
 */
public class QueryArrayList<E> extends ArrayList<E> implements QueryList<E> {

    /**
     *
     */
    private boolean indexed = false;

    private boolean addBracket = true;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity is
     * negative
     */
    public QueryArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public QueryArrayList() {
    }

    /**
     * Constructs a list containing the elements of the specified collection, in
     * the order they are returned by the collection's iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public QueryArrayList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public boolean isIndexed() {
        return indexed;
    }

    public void setIndexed(boolean indexed) {
        this.indexed = indexed;
    }

    @Override
    public boolean addBracket() {
        if (indexed) {
            return true;
        }
        return addBracket;
    }

    public void setAddBracket(boolean addBracket) {
        this.addBracket = addBracket;
    }

}
