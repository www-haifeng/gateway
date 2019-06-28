package com.shuzhi.dao;

import com.shuzhi.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * author: Yujq
 * date: 2018/4/5
 */
public interface SysUserRoleRepository extends JpaRepository<SysUserRole,Integer>{

    List<SysUserRole> findByUserId(Integer id);

    void deleteByUserId(Integer id);
}
