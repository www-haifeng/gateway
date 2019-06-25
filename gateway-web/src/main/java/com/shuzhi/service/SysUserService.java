package com.shuzhi.service;

import com.shuzhi.from.SysUserFrom;
import com.shuzhi.entity.SysUser;
import com.shuzhi.vo.R;
import org.springframework.data.domain.Pageable;


/**
 * author: Yujq
 * date: 2018/4/5
 */
public interface SysUserService {

    SysUser findByAccount(String account);

    R saveUser(SysUserFrom sysUserFrom);

    R selectUserList(String name, Pageable pageable);

    R selectUserDetail(Integer id);

    R updateUser(SysUserFrom sysUserFrom);

    R delectUser(Integer id);
}
