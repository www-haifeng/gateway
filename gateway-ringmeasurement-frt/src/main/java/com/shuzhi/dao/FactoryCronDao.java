package com.shuzhi.dao;

import com.shuzhi.entity.TDeviceFactoryCronEntity;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @Description: 厂商定时任务dao
 * @Author:     lirb
 * @CreateDate:   2019/7/14 14:11
 * @Version:   1.0
 **/
public interface FactoryCronDao extends JpaRepository<TDeviceFactoryCronEntity, Long> {



}
