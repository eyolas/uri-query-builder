package io.eyolas.http.query.collection;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 *
 * @author eyolas
 * @param <K>
 * @param <V>
 */
public class QueryLinkedHashMap<K extends String, V extends Object> extends LinkedHashMap<K, V> implements QueryMap<K, V>, Serializable {
    private static final long serialVersionUID = 8707489537750324635L;
    
}
