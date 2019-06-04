package com.shuzhi.dao;

import com.shuzhi.entity.TLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TLogEntityRepository extends JpaRepository<TLogEntity, Long> {
}
