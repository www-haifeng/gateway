package com.shuzhi.service.impl;

import com.shuzhi.dao.SysMenuRepository;
import com.shuzhi.entity.SysMenu;
import com.shuzhi.service.SysMenuService;
import com.shuzhi.utils.RUtil;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * @ProjectName: gateway-parent
 * @Package: com.shuzhi.service.impl
 * @ClassName: SysMenuServiceImpl
 * @Author: 陈鑫晖
 * @Date: 2019/7/7 15:56
 */
@Service
@Transactional
@Slf4j
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuRepository sysMenuRepository;

    @Override
    public R selectMenu() {
        //查询所有菜单
        List<SysMenu> menuList = sysMenuRepository.findAll();
        //找到所有一级菜单
        List<SysMenu> mList = new ArrayList<>();
        for (int i = 0; i < menuList.size(); i++) {
            if (menuList.get(i).getPid()==0){
                mList.add(menuList.get(i));
            }
        }
        //为一级菜单设置子菜单准备递归
        for (SysMenu menu:mList) {
            //获取父菜单下所有子菜单调用getChildList
            List<SysMenu> childList = getChildList(String.valueOf(menu.getId()),menuList);
            menu.setChildList(childList);
        }
        return RUtil.success(mList);
    }

    public List<SysMenu> getChildList(String id,List<SysMenu> menuList){
        //构建子菜单
        List<SysMenu> childList = new ArrayList<>();
        //遍历传入的list
        for (SysMenu menu:menuList) {
            //所有菜单的父id与传入的根节点id比较，若相等则为该级菜单的子菜单
            if (String.valueOf(menu.getPid()).equals(id)){
                childList.add(menu);
            }
        }
        //递归
        for (SysMenu m:childList) {
            m.setChildList(getChildList(String.valueOf(m.getId()),menuList));
        }
        if (childList.size() == 0){
            return null;
        }
        return childList;
    }

    /**
     * 添加菜单
     * @param sysMenu
     * @return
     */
    @Override
    public R insertMenu(SysMenu sysMenu) {
        if(sysMenu.getPid()==null){
            sysMenu.setPid(0);
        }
        SysMenu sysMenuSave = sysMenuRepository.save(sysMenu);
        return RUtil.success();
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @Override
    public R deleteMenu(Integer id) {
        sysMenuRepository.deleteById(id);
        return RUtil.success();
    }

    /**
     * 修改菜单
     * @param sysMenu
     * @return
     */
    @Override
    public R updateMenu(SysMenu sysMenu) {

        //根据id查询到需要修改的菜单详细信息
        Optional<SysMenu> menu = sysMenuRepository.findById(sysMenu.getId());
        //判断传来的值为空，或者没有值，则赋值数据库中的数据，防止修改后数据库为空
        if(sysMenu.getName() == null || sysMenu.getName().equals("")){
            sysMenu.setName(menu.get().getName());
        }
        if(sysMenu.getPid()==null){
            sysMenu.setPid(menu.get().getPid());
        }
        if(sysMenu.getUrl() == null || sysMenu.getUrl().equals("")){
            sysMenu.setUrl(menu.get().getUrl());
        }
        sysMenuRepository.save(sysMenu);
        return RUtil.success();
    }


}
