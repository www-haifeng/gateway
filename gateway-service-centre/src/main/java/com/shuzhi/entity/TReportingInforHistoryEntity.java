package com.shuzhi.entity;

import com.shuzhi.util.StringJsonUserType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;

/**
* @Program:  上报/告警信息 上传历史数据(未成功保存,成功后删除)
* @Description:
* @Author: YuJQ
* @Create: 2019/6/4 13:15
**/
@Data
@Entity
@Table(name = "t_reporting_infor_history")
@TypeDefs({ @TypeDef(name = "StringJsonObject", typeClass = StringJsonUserType.class) })
public class TReportingInforHistoryEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="t_reporting_infor_history_seq")
    @SequenceGenerator(sequenceName="t_reporting_infor_history_seq", name="t_reporting_infor_history_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "msg_type")
    private String msgType;

    @Column(name = "msg_name")
    private String msgName;

    @Column(name = "systype")
    private String systype;

    @Column(name = "sysid")
    private String sysid;

    @Column(name = "connectid")
    private String connectid;

    @Column(name = "timestamp")
    private Date timestamp;

    @Type(type = "StringJsonObject")
    @Column(name = "data")
    private String data;

}
