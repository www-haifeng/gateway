package com.shuzhi.service;


import com.shuzhi.entity.TGatewayConfigEntity;
import org.springframework.stereotype.Service;

import java.util.List;



public interface TGatewayConfigService {
     List<TGatewayConfigEntity> findall();
}
