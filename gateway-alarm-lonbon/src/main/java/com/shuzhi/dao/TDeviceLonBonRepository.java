package com.shuzhi.dao;

import com.shuzhi.entity.TDeviceLonBonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ztt on 2019/6/24
 **/
public interface TDeviceLonBonRepository extends JpaRepository<TDeviceLonBonEntity, Integer> {

    List<TDeviceLonBonEntity> getAllByDeviceTypeIn(int[] types);
}
