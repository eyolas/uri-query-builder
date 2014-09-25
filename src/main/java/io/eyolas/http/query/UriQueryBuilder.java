package io.eyolas.http.query;

import io.eyolas.http.query.collection.QueryList;
import io.eyolas.http.query.collection.QueryListType;
import io.eyolas.http.query.collection.QueryMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * Construct query uri
 * </p>
 *
 *
 * @author eyolas
 */
public class UriQueryBuilder {

    private final Map<String, Object> params = new LinkedHashMap<>();

    public UriQueryBuilder() {
    }

    /**
     * Add all params
     *
     * @param params map of params
     * @return UriQueryBuilder instance
     */
    public UriQueryBuilder params(Map<String, Object> params) {
        if (null != params && !params.isEmpty()) {
            this.params.putAll(params);
        }

        return this;
    }

    /**
     * Add all params
     *
     * @param key key of param
     * @param value value of param
     * @return UriQueryBuilder instance
     */
    public UriQueryBuilder param(String key, Object value) {
        if (StringUtils.isNotBlank(key)) {
            params.put(key, value);
        }

        return this;
    }

    /**
     * Build the query
     *
     * @return uri
     */
    public String build() {
        if (null != params && !params.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                String param = constructUriParam(key, value);
                if (StringUtils.isNotBlank(param)) {
                    sb.append(param);
                }
            }

            return StringUtils.replaceOnce(sb.toString(), "&", "?");
        }

        return "";
    }

    private String constructUriParam(String key, Object value) {
        if (StringUtils.isBlank(key)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (value instanceof QueryList) {
            String q = constructUriParamQueryList(key, (QueryList) value);
            if (StringUtils.isNotBlank(q)) {
                sb.append(q);
            }
        } else if (value instanceof QueryMap) {
            String q = constructUriParamQueryMap(key, (QueryMap<String, Object>) value);
            if (StringUtils.isNotBlank(q)) {
                sb.append(q);
            }
        } else {
            if (null == value) {
                value = "";
            }

            sb.append("&").append(key).append("=").append(String.valueOf(value));
        }

        return sb.toString();
    }

    private String constructUriParamQueryList(String key, QueryList queryList) {
        if (null == queryList || queryList.isEmpty() || null == queryList.getQueryListType()) {
            return null;
        }

        if (QueryListType.SEMICOLON.equals(queryList.getQueryListType())) {
            return "&" + key + "=" + StringUtils.join(queryList, ";");
        } else {
            StringBuilder sb = new StringBuilder();

            if (QueryListType.BRACKET.equals(queryList.getQueryListType())) {
                key += "[]";
            }

            int i = 0;
            for (Object val : queryList) {
                String k = key;

                if (QueryListType.INDEXED.equals(queryList.getQueryListType())) {
                    k += "[" + i + "]";
                }

                if (null == val) {
                    sb.append("");
                }

                if (val instanceof QueryList || val instanceof QueryMap) {
                    String q = constructUriParam(k, val);
                    if (StringUtils.isNotBlank(q)) {
                        sb.append(q);
                    }
                } else {
                    if (null == val) {
                        val = "";
                    }

                    sb.append("&").append(k).append("=").append(String.valueOf(val));
                }
                i++;
            }
            return sb.toString();
        }

    }

    private String constructUriParamQueryMap(String key, QueryMap<String, Object> queryMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : queryMap.entrySet()) {
            String keyMap = entry.getKey();
            if (StringUtils.isBlank(keyMap)) {
                continue;
            }
            String newKey = key + "[" + keyMap + "]";
            Object val = entry.getValue();
            if (val instanceof QueryList || val instanceof QueryMap) {
                String q = constructUriParam(newKey, val);
                if (StringUtils.isNotBlank(q)) {
                    sb.append(q);
                }
            } else {
                if (null == val) {
                    val = "";
                }
                sb.append("&").append(newKey).append("=").append(String.valueOf(val));
            }
        }

        return sb.toString();
    }
}
