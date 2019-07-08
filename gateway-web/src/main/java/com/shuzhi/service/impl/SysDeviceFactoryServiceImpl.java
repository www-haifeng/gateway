package com.shuzhi.service.impl;

import com.shuzhi.dao.SysDeviceFactoryRepository;
import com.shuzhi.entity.SysDeviceFactory;
import com.shuzhi.service.SysDeviceFactoryService;
import com.shuzhi.utils.RUtil;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @ProjectName: gateway-parent
 * @Package: com.shuzhi.service.impl
 * @ClassName: SysDeviceFactoryServiceImpl
 * @Author: 陈鑫晖
 * @Date: 2019/7/8 17:07
 */
@Service
@Transactional
@Slf4j
public class SysDeviceFactoryServiceImpl implements SysDeviceFactoryService {


    @Autowired
    SysDeviceFactoryRepository sysDeviceFactoryRepository;

    /**
     * 查询设备列表
     * @return
     */
    @Override
    public R selectSysDeviceFactory() {
        List<SysDeviceFactory> sysDeviceFactoryList = sysDeviceFactoryRepository.findAll();
        return RUtil.success(sysDeviceFactoryList);
    }

    /**
     * 添加设备
     * @param sysDeviceFactory
     * @return
     */
    @Override
    public R insertSysDeviceFactory(SysDeviceFactory sysDeviceFactory) {
        SysDeviceFactory deviceFactory = sysDeviceFactoryRepository.save(sysDeviceFactory);
        return RUtil.success();
    }

    /**
     * 修改设备信息
     * @param sysDeviceFactory
     * @return
     */
    @Override
    public R updateSysDeviceFactory(SysDeviceFactory sysDeviceFactory) {
        //查询要修改的记录信息进行判断
        Optional<SysDeviceFactory> DeviceFactory = sysDeviceFactoryRepository.findById(sysDeviceFactory.getId());

        if (sysDeviceFactory.getType()== null){
            sysDeviceFactory.setType(DeviceFactory.get().getType());
        }
        if(sysDeviceFactory.getSubtype()==null){
            sysDeviceFactory.setSubtype(DeviceFactory.get().getSubtype());
        }
        if(sysDeviceFactory.getTypeName()==null ||sysDeviceFactory.getTypeName().equals("") ){
            sysDeviceFactory.setTypeName(DeviceFactory.get().getTypeName());
        }
        if (sysDeviceFactory.getFactoryName()==null || sysDeviceFactory.getFactoryName().equals("")){
            sysDeviceFactory.setFactoryName(DeviceFactory.get().getFactoryName());
        }
        if (sysDeviceFactory.getServerIp()==null || sysDeviceFactory.getServerIp().equals("")){
            sysDeviceFactory.setServerIp(DeviceFactory.get().getServerIp());
        }
        if (sysDeviceFactory.getServerPort()==null){
            sysDeviceFactory.setServerPort(DeviceFactory.get().getServerPort());
        }
        if (sysDeviceFactory.getDescribe()==null || sysDeviceFactory.getDescribe().equals("")){
            sysDeviceFactory.setDescribe(DeviceFactory.get().getDescribe());
        }
        if (sysDeviceFactory.getMqType()==null || sysDeviceFactory.getMqType().equals("")){
            sysDeviceFactory.setMqType(DeviceFactory.get().getMqType());
        }
        if (sysDeviceFactory.getMqSubtype()==null || sysDeviceFactory.getMqSubtype().equals("")){
            sysDeviceFactory.setMqSubtype(DeviceFactory.get().getMqSubtype());
        }
        SysDeviceFactory deviceFactory = sysDeviceFactoryRepository.save(sysDeviceFactory);
        return RUtil.success();
    }

    /**
     * 删除设备信息
     * @param id
     * @return
     */
    @Override
    public R deleteSysDeviceFactory(Integer id) {
        sysDeviceFactoryRepository.deleteById(id);
        return RUtil.success();
    }
}
