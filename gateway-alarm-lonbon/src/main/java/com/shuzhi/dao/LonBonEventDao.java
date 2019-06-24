package com.shuzhi.dao;

import com.shuzhi.entity.TLonbonEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ztt on 2019/6/19
 **/
@Repository
public interface LonBonEventDao extends JpaRepository<TLonbonEventEntity, Long> {
    @Query(value = "select * from t_lonbon_event where rd_file != '' and atm_num = 0;", nativeQuery = true)
    List<TLonbonEventEntity> findAllByRdFileAndAtmNum();
}
