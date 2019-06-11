package com.shuzhi.service;

import com.shuzhi.dao.TGatewayConfigEntityRepository;
import com.shuzhi.entity.TDeviceFactoryEntity;
import com.shuzhi.entity.TGatewayConfigEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @Program: TDeviceFactoryService
* @Description:
* @Author: YuJQ
* @Create: 2019/6/5 16:36
**/

public interface TDeviceFactoryService{
    List<TDeviceFactoryEntity> findall();

}
