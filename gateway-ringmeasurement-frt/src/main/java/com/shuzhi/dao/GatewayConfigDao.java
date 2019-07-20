package com.shuzhi.dao;

import com.shuzhi.entity.TGatewayConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatewayConfigDao extends JpaRepository<TGatewayConfigEntity,Long> {

    TGatewayConfigEntity getByTypeGroupCode(String typeGroupCode);
}
