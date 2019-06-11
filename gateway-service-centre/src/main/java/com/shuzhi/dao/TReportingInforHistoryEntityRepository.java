package com.shuzhi.dao;



import com.shuzhi.entity.TReportingInforHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TReportingInforHistoryEntityRepository extends JpaRepository<TReportingInforHistoryEntity, Long> {
}
