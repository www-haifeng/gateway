package com.shuzhi.dao;

import com.shuzhi.entity.TGatewayConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 链路信息
 * @Author:     lirb
 * @CreateDate:   2019/8/9 11:39
 * @Version:   1.0
 **/
public interface GatewayConfigDao extends JpaRepository<TGatewayConfigEntity,Long> {

    TGatewayConfigEntity getByTypeGroupCode(String typeGroupCode);
}
