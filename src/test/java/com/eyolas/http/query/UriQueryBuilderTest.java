package com.eyolas.http.query;

import com.eyolas.http.query.pojo.ObjectEmpty;
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
}
