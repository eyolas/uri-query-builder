package io.eyolas.http.query;

import io.eyolas.http.query.collection.QueryArrayList;
import io.eyolas.http.query.collection.QueryLinkedHashMap;
import io.eyolas.http.query.collection.QueryListType;
import io.eyolas.http.query.pojo.ObjectEmpty;
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
        QueryArrayList<String> list = new QueryArrayList<String>(l);
        UriQueryBuilder builder = new UriQueryBuilder()
                .param("club", list);
        Assert.assertEquals("?club[]=asse&club[]=psg&club[]=om", builder.build());
    }
    
    @Test
    public void queryArrayListStringIndexed() {
        List<String> l = Arrays.asList("asse", "psg", "om");
        QueryArrayList<String> list = new QueryArrayList<String>(l);
        list.setQueryListType(QueryListType.INDEXED);
        UriQueryBuilder builder = new UriQueryBuilder()
                .param("club", list);
        Assert.assertEquals("?club[0]=asse&club[1]=psg&club[2]=om", builder.build());
    }
    
    @Test
    public void queryArrayListStringNoBracket() {
        List<String> l = Arrays.asList("asse", "psg", "om");
        QueryArrayList<String> list = new QueryArrayList<String>(l);
        list.setQueryListType(QueryListType.NONE);
        UriQueryBuilder builder = new UriQueryBuilder()
                .param("club", list);
        Assert.assertEquals("?club=asse&club=psg&club=om", builder.build());
    }
    
    @Test
    public void queryArrayListStringSemicolon() {
        List<String> l = Arrays.asList("asse", "psg", "om");
        QueryArrayList<String> list = new QueryArrayList<String>(l);
        list.setQueryListType(QueryListType.SEMICOLON);
        UriQueryBuilder builder = new UriQueryBuilder()
                .param("club", list);
        Assert.assertEquals("?club=asse;psg;om", builder.build());
    }

    @Test
    public void queryHashMapString() {
        QueryLinkedHashMap<String, String> map = new QueryLinkedHashMap<String, String>();
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
        QueryArrayList<String> list = new QueryArrayList<String>(Arrays.asList("asse", "psg", "om"));
        QueryLinkedHashMap<String, String> map = new QueryLinkedHashMap<String, String>();
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
