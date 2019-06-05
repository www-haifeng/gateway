package com.shuzhi.service.impl;

import com.shuzhi.dao.TGatewayConfigEntityRepository;
import com.shuzhi.entity.TGatewayConfigEntity;
import com.shuzhi.service.TGatewayConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @Program: TGatewayConfigServiceImpl
* @Description:
* @Author: YuJQ
* @Create: 2019/6/5 16:36
**/
@Slf4j
@Transactional
public class TGatewayConfigServiceImpl implements TGatewayConfigService {
    @Autowired
    public TGatewayConfigEntityRepository tGatewayConfigEntityRepository;


    public List<TGatewayConfigEntity> findall(){
        return tGatewayConfigEntityRepository.findAll();
    }

}
