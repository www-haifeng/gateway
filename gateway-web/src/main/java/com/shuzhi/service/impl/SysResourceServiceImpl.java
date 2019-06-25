package com.shuzhi.service.impl;

import com.shuzhi.entity.SysResource;
import com.shuzhi.entity.SysRoleResource;
import com.shuzhi.entity.SysUserRole;
import com.shuzhi.dao.SysResourceRepository;
import com.shuzhi.dao.SysRoleResourceRepository;
import com.shuzhi.dao.SysUserRoleRepository;
import com.shuzhi.service.SysResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author: Yujq
 * date: 2018/4/5
 */
@Service
@Slf4j
@Transactional
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    SysResourceRepository sysResourceRepository;

    @Autowired
    SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    SysRoleResourceRepository sysRoleResourceRepository;

    /**
     * 查询用户权限表示符
     * @return
     */
    @Override
    public Set<String> selectUserPerms(Integer userId) {

        /*获取角色Id*/
        List<Integer> roleIds = new ArrayList<>();
        List<SysUserRole> sysUserRoles = sysUserRoleRepository.findByUserId(userId);
        sysUserRoles.forEach(o->{
            roleIds.add(o.getRoleId());
        });

        /*获取资源Id*/
        List<SysRoleResource> sysRoleResources = sysRoleResourceRepository.findByRoleId(roleIds);
        List<Integer> resourceIds = new ArrayList<>();
        sysRoleResources.forEach(o->{
            resourceIds.add(o.getResourceId());
        });

        /*获取权限标识符*/
        Set<String> prems = new HashSet<>();
        List<SysResource> sysResources = sysResourceRepository.findAll(resourceIds);
//        sysResources.forEach(o->{
//            if(StringUtils.isNoneBlank(o.getPerms())){
//                prems.add(o.getPerms());
//            }
//        });
        //        sysResources.forEach(o->{
//            if(StringUtils.isNoneBlank(o.getPerms())){
//                prems.add(o.getPerms());
//            }
//        });
        for (SysResource s:
                sysResources) {
            if(StringUtils.isNotBlank(s.getPerms())){
                String[] ss=  s.getPerms().split(";");
                for (String perms:ss) {
                    prems.add(perms);
                }
            }
        }

        return prems;
    }
}
