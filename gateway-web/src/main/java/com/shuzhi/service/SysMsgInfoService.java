package com.shuzhi.service;

import com.shuzhi.entity.SysMsgInfo;
import com.shuzhi.vo.R;

/**
 * @ProjectName: gateway-parent
 * @Package: com.shuzhi.service
 * @ClassName: SysMsgInfoService
 * @Author: 陈鑫晖
 * @Date: 2019/7/8 17:07
 */
public interface SysMsgInfoService {

    /**
     * 查询命令列表
     * @return
     */
    R selectMsgInfo();

    /**
     * 新增命令
     * @param sysMsgInfo
     * @return
     */
    R insertSysMsgInfo(SysMsgInfo sysMsgInfo);

    /**
     * 修改命令
     * @param sysMsgInfo
     * @return
     */
    R updateSysMsgInfo(SysMsgInfo sysMsgInfo);

    /**
     * 删除命令
     * @param id
     * @return
     */
    R deleteSysMsgInfo(Integer id);
}
