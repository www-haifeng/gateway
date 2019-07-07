package com.shuzhi.service.impl;

import com.shuzhi.dao.SysGatewayConfigRepository;
import com.shuzhi.entity.SysGatewayConfig;
import com.shuzhi.service.SysGatewayConfigService;
import com.shuzhi.utils.RUtil;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName SysGatewayConfigServiceImpl
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/27 11:43
 * @Version 1.0
 * @Description:
 **/
@Service
@Transactional
@Slf4j
public class SysGatewayConfigServiceImpl implements SysGatewayConfigService {

    @Autowired
    SysGatewayConfigRepository sysGatewayConfigRepository;

    @Override
    public R saveConfig(@Valid SysGatewayConfig sysGatewayConfig) {
        @Valid SysGatewayConfig sysGatewayConfig1 = sysGatewayConfigRepository.save(sysGatewayConfig);
        log.info("配置基本信息保存：sysGatewayConfig1 = {}"+sysGatewayConfig1);
        return  RUtil.success();
    }

    @Override
    public R selectConfigList(Integer id) {
        if(id != null){
            Optional<SysGatewayConfig> sysGatewayConfigRepositoryOne = sysGatewayConfigRepository.findById(id);
            log.info("配置基本信息：sysGatewayConfigRepositoryOne = {}"+sysGatewayConfigRepositoryOne);
            return  RUtil.success(sysGatewayConfigRepositoryOne);
        }else {
            List<SysGatewayConfig> sysGatewayConfigRepositoryAll = sysGatewayConfigRepository.findAll();
            log.info("配置基本信息：sysGatewayConfigRepositoryAll = {}"+sysGatewayConfigRepositoryAll);
            return RUtil.success(sysGatewayConfigRepositoryAll);
        }
    }

    @Override
    public R updateConfig(@Valid SysGatewayConfig sysGatewayConfig) {

        @Valid SysGatewayConfig save = sysGatewayConfigRepository.save(sysGatewayConfig);
        log.info("配置更新：save = {}"+ save);
        return  RUtil.success();
    }

    @Override
    public R delectConfig(Integer id) {

        sysGatewayConfigRepository.deleteById(id);
        return RUtil.success();
    }
}
