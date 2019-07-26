package com.shuzhi.dao;

import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.TMsgInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description: 加载命令基础信息
 * @Author:     lirb
 * @CreateDate:   2019/7/23 13:37
 * @Version:   1.0
 **/
public interface CommandInfoDao extends JpaRepository<TMsgInfoEntity, Long> {

    @Query(value = "select new com.shuzhi.entity.CommandInfo(tdf,tmi) from TMsgInfoEntity tmi , TDeviceFactoryEntity tdf where tmi.factoryId = tdf.id  and tdf.factoryName=:factoryName and tdf.typeName=:typeName ")
    public List<CommandInfo> findcommandInfo(@Param("typeName") String typeName,@Param("factoryName") String factoryName);

}
