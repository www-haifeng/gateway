package com.shuzhi.controller;

import com.shuzhi.entity.SysMenu;
import com.shuzhi.service.SysMenuService;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: gateway-parent
 * @Package: com.shuzhi.controller
 * @ClassName: SysMenuController
 * @Author: 陈鑫晖
 * @Date: 2019/7/7 16:07
 */

@RestController
@RequestMapping("/sysMenu")
@Slf4j
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    /**
     * 查询菜单
     * @return
     */
    @RequestMapping(value = "/selectMenuList",method = RequestMethod.GET)
    public R selectMenuList(){
        return sysMenuService.selectMenu();
    }

    /**
     * 添加菜单
     * @param sysMenu
     * @return
     */
    @RequestMapping(value = "/insertMenu",method = RequestMethod.POST)
    public R insertMenuOne(@RequestBody SysMenu sysMenu){
        return sysMenuService.insertMenu(sysMenu);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteMenu/{id}",method = RequestMethod.DELETE)
    public R deleteMenuOne(@PathVariable Integer id){
        return sysMenuService.deleteMenu(id);
    }

    /**
     * 修改菜单
     * @param sysMenu
     * @return
     */
    @RequestMapping(value = "/updateMenu",method = RequestMethod.PUT)
    public R updateMenu(@RequestBody SysMenu sysMenu){
        return sysMenuService.updateMenu(sysMenu);
    }
}
