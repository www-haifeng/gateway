package com.shuzhi.dao;

import com.shuzhi.entity.DeviceInfo;
import com.shuzhi.entity.TDeviceIotcommEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @Description:加载设备基础信息
 * @Author: YHF
 * @date 17:24
 */

public interface DeviceInfoDao extends JpaRepository<TDeviceIotcommEntity, Long> {

    @Query(value = "select new com.shuzhi.entity.DeviceInfo(tdf,tds) from TDeviceIotcommEntity tds , TDeviceFactoryEntity tdf where tds.factoryId = tdf.id ")
    public List<DeviceInfo> findDeviceInfo();

}
