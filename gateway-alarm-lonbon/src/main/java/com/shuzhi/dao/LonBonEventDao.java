package com.shuzhi.dao;

import com.shuzhi.entity.TLonbonEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by ztt on 2019/6/19
 **/
@Repository
public interface LonBonEventDao extends JpaRepository<TLonbonEventEntity, Long> {
    /**
     * 获取所有未上传的文件
     *
     * @return
     */
    @Query(value = "select DISTINCT session_id,rd_file,atm_num,sender from t_lonbon_event where rd_file != '' and state = 0;", nativeQuery = true)
//    @Query(value = "select * from t_lonbon_event where rd_file != '' and atm_num = 0;", nativeQuery = true)
    List<Map> findAllByRdFileAndAtmNum();

    /**
     * 获取所有未上传的文件的目录
     *
     * @return
     */
    @Query(value = "select substr(rd_file, 0, 9) as dir from t_lonbon_event where rd_file !='' and state = 0 GROUP BY dir ORDER BY dir;", nativeQuery = true)
    List<String> findAllDir();

    /**
     * 修改文件状态
     *
     * @return
     */
    @Modifying
    @Query(value = "UPDATE t_lonbon_event set state = 1,upload_time = ?1 WHERE rd_file = ?2 and state = 0;", nativeQuery = true)
    int updateAtmnum(Timestamp timestamp, String rdfile);

    /**
     * 查询文件上传状态
     *
     * @param rdfile
     * @return
     */
    @Query(value = "SELECT state FROM t_lonbon_event where rd_file = ?1 GROUP BY state;", nativeQuery = true)
    List<Integer> findFileStatus(String rdfile);

}
