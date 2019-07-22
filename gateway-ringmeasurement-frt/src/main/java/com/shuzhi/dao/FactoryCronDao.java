package com.shuzhi.dao;

import com.shuzhi.entity.TDeviceFactoryCronEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @Description: 厂商定时任务dao
 * @Author:     lirb
 * @CreateDate:   2019/7/14 14:11
 * @Version:   1.0
 **/
public interface FactoryCronDao extends JpaRepository<TDeviceFactoryCronEntity, Long> {


    TDeviceFactoryCronEntity getByFactoryName(String factoryName);
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query("update TDeviceFactoryCronEntity  fc set fc.cron =:cron where fc.factoryId =:id")
    void updateCronByFactoryId(@Param("id")int id,@Param("cron") String cron);
}
