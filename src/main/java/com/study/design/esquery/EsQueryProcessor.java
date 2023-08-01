package com.study.design.esquery;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class EsQueryProcessor {
    //1. 我们要用stream 返回 为了节省内存
    public Stream<Map<String, Object>> scrollEsStream(String query, Long fetchSize) {
        return StreamSupport.stream(Spliterators
                .spliteratorUnknownSize(new ScrollIterator(query, fetchSize), 0), false);
    }


    //2. 我们要 迭代器
    private class ScrollIterator implements Iterator<Map<String, Object>> {
        private String scrollId;
        private List<String> columns;
        Iterator<Map<String, Object>> iterator;
        RestTemplate restTemplate = new RestTemplate(); // 真是项目中使用resttemplate的时候
        //一定是进行过我们的 bean 配置注入的。这里边直接用new关键字是为了访问我们的es 接口。

        //构造函数进行第一次查询，并且初始化我们后续需要使用的 columns 和 iterator 和 scroll
        public ScrollIterator(String query, Long fetchSize) {
            EsSqlResult esSqlResult = restTemplate.postForObject("http://localhost:9200/_sql?format=json",
                    new EsSqlQuery(query, fetchSize), EsSqlResult.class);//第一次访问的结果出来了
            this.scrollId = esSqlResult.getCursor();
            this.columns = esSqlResult.getColumns()
                    .stream().map(x->x.get("name"))
                    .collect(Collectors.toList());
            this.iterator = convert(columns, esSqlResult).iterator();
        }

        // hasNext 根据 是否 scrollId 为null进行后续的 第二次，第三次，，，的访问，直到 scrollId 为null
        @Override
        public boolean hasNext() {
            return iterator.hasNext() || scrollNext();
        }
        private boolean scrollNext() {
            if(iterator == null || this.scrollId == null) {
                return false;
            }
            EsSqlResult esSqlResult = restTemplate.postForObject("http://localhost:9200/_sql?format=json",
                    new EsSqlQuery(this.scrollId), EsSqlResult.class);//第二次访问的结果出来了
            this.scrollId = esSqlResult.getCursor();
            this.iterator = convert(columns, esSqlResult).iterator();
            return iterator.hasNext();
        }

        @Override
        public Map<String, Object> next() {
            return iterator.next();
        }
    }



    //3. 返回结果传统一点 List<map>
    private List<Map<String, Object>> convert(List<String> columns, EsSqlResult esSqlResult) {
        List<Map<String, Object>> results = new ArrayList<>();
        for(List<Object> row : esSqlResult.getRows()) {
            Map<String, Object> map = new HashMap<>();
            for(int i = 0; i < columns.size(); i++) {
                map.put(columns.get(i), row.get(i));
            }
            results.add(map);
        }
        return results;
    }
}
