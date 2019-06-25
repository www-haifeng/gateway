package com.shuzhi.service;

import com.shuzhi.entity.SysResource;

import java.util.Set;

/**
 * author: Yujq
 * date: 2018/4/5
 */
public interface SysResourceService {

    Set<String> selectUserPerms(Integer userId);

}
