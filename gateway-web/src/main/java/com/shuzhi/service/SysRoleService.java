package com.shuzhi.service;

import com.shuzhi.from.SysRoleFrom;
import com.shuzhi.vo.R;
import org.springframework.data.domain.Pageable;

/**
 * author: Yujq
 * date: 2018/4/5
 */
public interface SysRoleService {

    R saveRole(SysRoleFrom sysRoleFrom);

    R selectRoleList(String name,Pageable pageable);

    R selectRoleDetail(Integer id);

    R updateRole(SysRoleFrom sysRoleFrom);

    R deleteRole(Integer id);
}
