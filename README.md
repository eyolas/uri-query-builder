[![Build Status][build-image]][build-url]

## Uri query builder

Helper for build the query part of uri

# Usage
Instantiate UriQueryBuilder and add params.
Use method build for build the String.

For each param :
* if the key is null or empty, builder skip the param
* value: 
    * if value is null, set "" to value
    * if value is an instance of QueryList or QueryMap, builder loop on collection
    * else String.valueof


## Simple example
```java

UriQueryBuilder builder = new UriQueryBuilder().param("test", "simpleTest");
Assert.assertEquals("?test=simpleTest", builder.build());

```

## QueryList
QueryList is an representation of query list.

QueryList have 3 type :
* NONE: No bracket and no index. ex : ?club=asse&club=psg&club=om
* BRACKET: With bracket. ex: ?club[]=asse&club[]=psg&club[]=om
* INDEXED: Indexed. ex: ?club[0]=asse&club[1]=psg&club[2]=om

example type INDEXED:
```java
List<String> l = Arrays.asList("asse", "psg", "om");
QueryArrayList<String> list = new QueryArrayList<>(l);
list.setQueryListType(QueryListType.INDEXED);
UriQueryBuilder builder = new UriQueryBuilder()
        .param("club", list);
Assert.assertEquals("?club[0]=asse&club[1]=psg&club[2]=om", builder.build());
```

## QueryMap
QueryMap is an representation of query map.

example:
```java

QueryLinkedHashMap<String, String> map = new QueryLinkedHashMap<>();
map.put("java", "java");
map.put("js", "javascript");
map.put("rb", "ruby");
map.put("coffee", "coffeescript");
UriQueryBuilder builder = new UriQueryBuilder()
        .param("language", map);

Assert.assertEquals("?language[java]=java&language[js]=javascript&language[rb]=ruby&language[coffee]=coffeescript", builder.build());

```

# License

  The [MIT](LICENCE) License

[build-image]: http://img.shields.io/travis/eyolas/uri-query-builder.svg?branch=master&style=flat-square
[build-url]: https://travis-ci.org/eyolas/uri-query-builder