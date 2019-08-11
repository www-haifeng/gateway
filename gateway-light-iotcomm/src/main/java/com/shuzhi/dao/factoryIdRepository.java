package com.shuzhi.dao;

import com.shuzhi.entity.TDeviceFactoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @Description: 设备厂商基础信息
 * @Author:     lirb
 * @CreateDate:   2019/8/9 11:39
 * @Version:   1.0
 **/
public interface factoryIdRepository extends JpaRepository<TDeviceFactoryEntity, Long> {

}
