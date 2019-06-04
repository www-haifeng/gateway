package com.shuzhi.controller;


import com.shuzhi.dao.TGatewayConfigEntityRepository;
import com.shuzhi.dao.TLogEntityRepository;
import com.shuzhi.entity.TGatewayConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class TestDemo {
    private final static Logger logger = LoggerFactory.getLogger(TestDemo.class);
    @Autowired
    public TGatewayConfigEntityRepository tGatewayConfigEntityRepository;

    @Autowired
    public TLogEntityRepository tLogEntityRepository;
    @RequestMapping("/get")
    public String getAnswersByQuestionId() {

        List<TGatewayConfigEntity> list  = tGatewayConfigEntityRepository.findAll();
        for (TGatewayConfigEntity t:
                list) {
            System.out.println(t);
            logger.info("这个有问题"+t);
        }
        return "hello 服务已经启动";
    }

}
