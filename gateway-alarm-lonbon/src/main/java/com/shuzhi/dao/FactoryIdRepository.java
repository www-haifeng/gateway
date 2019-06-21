package com.shuzhi.dao;

import com.shuzhi.entity.TDeviceFactoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactoryIdRepository extends JpaRepository<TDeviceFactoryEntity, Long> {

    TDeviceFactoryEntity findByType(String type);
    TDeviceFactoryEntity findByMqTypeAndMqSubType(String myType,String mqSubtype);
}
