package com.study.design.service;

import com.study.design.esquery.EsQueryProcessor;
import com.study.design.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class EsService {

    @Autowired
    private EsQueryProcessor esQueryProcessor;
    
    public Boolean query(String query, Long fetchSize) {
        Stream<Map<String, Object>> mapStream = esQueryProcessor
                .scrollEsStream(query, fetchSize);
        mapStream.forEach(x -> System.out.println(x));
        return true;
    }
}
