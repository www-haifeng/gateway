package com.shuzhi.dao;

import com.shuzhi.entity.TLonbonEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ztt on 2019/6/19
 **/
public interface LonBonEventDao extends JpaRepository<TLonbonEventEntity, Long> {
}
