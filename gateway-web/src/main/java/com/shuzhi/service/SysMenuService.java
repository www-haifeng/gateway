package com.shuzhi.service;

import com.shuzhi.entity.SysMenu;
import com.shuzhi.vo.R;

/**
 * @ProjectName: gateway-parent
 * @Package: com.shuzhi.service
 * @ClassName: SysMenuService
 * @Author: 陈鑫晖
 * @Date: 2019/7/7 15:50
 */
public interface SysMenuService {


    /**
     * 查询菜单
     * @return
     */
    R selectMenu();

    /**
     * 添加菜单
     * @param sysMenu
     * @return
     */
    R insertMenu(SysMenu sysMenu);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    R deleteMenu(Integer id);

    /**
     * 修改菜单
     * @param sysMenu
     * @return
     */
    R updateMenu(SysMenu sysMenu);
}
