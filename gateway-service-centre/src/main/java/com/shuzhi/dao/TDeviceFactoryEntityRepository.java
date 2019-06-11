package com.shuzhi.dao;


import com.shuzhi.entity.TDeviceFactoryEntity;
import com.shuzhi.entity.TGatewayConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TDeviceFactoryEntityRepository extends JpaRepository<TDeviceFactoryEntity, Long> {
}
