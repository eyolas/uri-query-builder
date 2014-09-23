package com.eyolas.http.query;

import com.eyolas.http.query.collection.QueryArrayList;
import com.eyolas.http.query.collection.QueryLinkedHashMap;
import com.eyolas.http.query.pojo.ObjectEmpty;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author eyolas
 */
public class UriQueryBuilderTest {

    @Test
    public void simpleTest() {
        UriQueryBuilder builder = new UriQueryBuilder().param("test", "simpleTest");
        Assert.assertEquals("?test=simpleTest", builder.build());
    }

    @Test
    public void simpleTest2() {
        UriQueryBuilder builder = new UriQueryBuilder()
                .param("test", "simpleTest")
                .param("object", new ObjectEmpty());
        Assert.assertEquals("?test=simpleTest&object=ObjectEmpty{}", builder.build());
    }

    @Test
    public void valueEmpty() {
        UriQueryBuilder builder = new UriQueryBuilder()
                .param("test", "simpleTest")
                .param("object", null)
                .param("test2", "lol");
        Assert.assertEquals("?test=simpleTest&object=&test2=lol", builder.build());
    }

    @Test
    public void keyEmpty() {
        UriQueryBuilder builder = new UriQueryBuilder()
                .param("test", "simpleTest")
                .param(null, "test")
                .param("", "lol");
        Assert.assertEquals("?test=simpleTest", builder.build());
    }

    @Test
    public void array() {
        List<String> list = Arrays.asList("test", "lol", "titi");
        UriQueryBuilder builder = new UriQueryBuilder()
                .param("list", list);
        Assert.assertEquals("?list=[test, lol, titi]", builder.build());
    }

    @Test
    public void queryArrayListString() {
        List<String> l = Arrays.asList("asse", "psg", "om");
        QueryArrayList<String> list = new QueryArrayList<>(l);
        UriQueryBuilder builder = new UriQueryBuilder()
                .param("club", list);
        Assert.assertEquals("?club[]=asse&club[]=psg&club[]=om", builder.build());
    }
    
    @Test
    public void queryArrayListStringNoBracket() {
        List<String> l = Arrays.asList("asse", "psg", "om");
        QueryArrayList<String> list = new QueryArrayList<>(l);
        list.setAddBracket(false);
        UriQueryBuilder builder = new UriQueryBuilder()
                .param("club", list);
        Assert.assertEquals("?club=asse&club=psg&club=om", builder.build());
    }

    @Test
    public void queryHashMapString() {
        QueryLinkedHashMap<String, String> map = new QueryLinkedHashMap<>();
        map.put("java", "java");
        map.put("js", "javascript");
        map.put("rb", "ruby");
        map.put("coffee", "coffeescript");
        UriQueryBuilder builder = new UriQueryBuilder()
                .param("language", map);

        Assert.assertEquals("?language[java]=java&language[js]=javascript&language[rb]=ruby&language[coffee]=coffeescript", builder.build());
    }

    @Test
    public void queryHasMapAndList() {
        QueryArrayList<String> list = new QueryArrayList<>(Arrays.asList("asse", "psg", "om"));
        QueryLinkedHashMap<String, String> map = new QueryLinkedHashMap<>();
        map.put("java", "java");
        map.put("js", "javascript");
        map.put("rb", "ruby");
        map.put("coffee", "coffeescript");
        
        UriQueryBuilder builder = new UriQueryBuilder()
                .param("test", "test")
                .param("club", list)
                .param("language", map);

        Assert.assertEquals("?test=test&club[]=asse&club[]=psg&club[]=om&language[java]=java&language[js]=javascript&language[rb]=ruby&language[coffee]=coffeescript", builder.build());
    }
}
