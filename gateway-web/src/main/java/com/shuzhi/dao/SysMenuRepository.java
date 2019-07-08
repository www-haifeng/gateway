package com.shuzhi.dao;

import com.shuzhi.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ProjectName: gateway-parent
 * @Package: com.shuzhi.dao
 * @ClassName: SysMenuRepository
 * @Author: 陈鑫晖
 * @Date: 2019/7/7 15:47
 */
public interface SysMenuRepository extends JpaRepository<SysMenu,Integer> {
}
