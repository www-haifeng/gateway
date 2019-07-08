package com.shuzhi.service.impl;

import com.shuzhi.dao.SysMsgInfoRepository;
import com.shuzhi.entity.SysMsgInfo;
import com.shuzhi.service.SysMsgInfoService;
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
 * @ClassName: SysMsgInfoServiceImpl
 * @Author: 陈鑫晖
 * @Date: 2019/7/8 17:07
 */
@Service
@Transactional
@Slf4j
public class SysMsgInfoServiceImpl implements SysMsgInfoService {

    @Autowired
    SysMsgInfoRepository sysMsgInfoRepository;

    /**
     * 查询命令列表
     * @return
     */
    @Override
    public R selectMsgInfo() {
        List<SysMsgInfo> sysMsgInfoList = sysMsgInfoRepository.findAll();
        return RUtil.success(sysMsgInfoList);
    }

    /**
     * 新增命令
     * @param sysMsgInfo
     * @return
     */
    @Override
    public R insertSysMsgInfo(SysMsgInfo sysMsgInfo) {
        sysMsgInfoRepository.save(sysMsgInfo);
        return RUtil.success();
    }

    /**
     * 更新命令信息
     * @param sysMsgInfo
     * @return
     */
    @Override
    public R updateSysMsgInfo(SysMsgInfo sysMsgInfo) {

        //查询要修改的记录信息进行判断
        Optional<SysMsgInfo> MsgInfo = sysMsgInfoRepository.findById(sysMsgInfo.getId());

        if (sysMsgInfo.getFactoryId()==null){
            sysMsgInfo.setFactoryId(MsgInfo.get().getFactoryId());
        }
        if (sysMsgInfo.getMsgType()==null){
            sysMsgInfo.setMsgType(MsgInfo.get().getMsgType());
        }
        if (sysMsgInfo.getMsgId() == null || sysMsgInfo.getMsgId().equals("") ){
            sysMsgInfo.setMsgId(MsgInfo.get().getMsgId());
        }
        if (sysMsgInfo.getInterfaceId() == null || sysMsgInfo.getInterfaceId().equals("")){
            sysMsgInfo.setInterfaceId(MsgInfo.get().getInterfaceId());
        }
        if (sysMsgInfo.getRequestType() == null || sysMsgInfo.getRequestType().equals("")){
            sysMsgInfo.setInterfaceId(MsgInfo.get().getInterfaceId());
        }
        if (sysMsgInfo.getDescribe() == null || sysMsgInfo.getDescribe().equals("")){
            sysMsgInfo.setDescribe(MsgInfo.get().getDescribe());
        }
        SysMsgInfo msgInfo = sysMsgInfoRepository.save(sysMsgInfo);
        return RUtil.success();
    }

    /**
     * 根据id删除命令
     * @param id
     * @return
     */
    @Override
    public R deleteSysMsgInfo(Integer id) {
        sysMsgInfoRepository.deleteById(id);
        return RUtil.success();
    }
}
