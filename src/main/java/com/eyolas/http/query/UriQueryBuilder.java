package com.eyolas.http.query;

import com.eyolas.http.query.collection.QueryList;
import com.eyolas.http.query.collection.QueryMap;
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

    public UriQueryBuilder params(Map<String, Object> params) {
        if (null != params && !params.isEmpty()) {
            this.params.putAll(params);
        }

        return this;
    }

    public UriQueryBuilder param(String key, Object value) {
        if (StringUtils.isNotBlank(key)) {
            params.put(key, value);
        }

        return this;
    }

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
        StringBuilder sb = new StringBuilder();
        
        if (queryList.addBracket()) {
            key += "[]";
        }

        for (Object val : queryList) {
            if (null == val) {
                sb.append("");
            }

            if (val instanceof QueryList || val instanceof QueryMap) {
                String q = constructUriParam(key, val);
                if (StringUtils.isNotBlank(q)) {
                    sb.append(q);
                }
            } else {
                if (null == val) {
                    val = "";
                }
                sb.append("&").append(key).append("=").append(String.valueOf(val));
            }
        }
        return sb.toString();
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
