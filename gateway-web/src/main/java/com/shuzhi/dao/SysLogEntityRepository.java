package com.shuzhi.dao;


import com.shuzhi.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SysLogEntityRepository extends JpaRepository<SysLog, Long> {
}
