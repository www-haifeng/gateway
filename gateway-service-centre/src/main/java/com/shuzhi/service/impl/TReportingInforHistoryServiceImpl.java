package com.shuzhi.service.impl;

import com.shuzhi.dao.TReportingInforHistoryEntityRepository;
import com.shuzhi.entity.TReportingInforHistoryEntity;
import com.shuzhi.service.TReportingInforHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class TReportingInforHistoryServiceImpl implements TReportingInforHistoryService {
    @Autowired
    public TReportingInforHistoryEntityRepository tReportingInforHistoryEntityRepository;


    @Override
    public void save(TReportingInforHistoryEntity t ) {
        tReportingInforHistoryEntityRepository.save(t);
    }
    @Override
    public void delete(TReportingInforHistoryEntity t ) {
        tReportingInforHistoryEntityRepository.delete(t);
    }
}
